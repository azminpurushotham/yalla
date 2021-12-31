package com.cherry.yalla.utility

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.Dialog
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.ExifInterface
import android.media.ThumbnailUtils
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.Window
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.cherry.yalla.R
import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Class for holding common methods
 */
object Util {

    val osVersion: String
        get() = Build.VERSION.RELEASE

    fun getAppVersionName(context: Context): String? {
        var versionName: String? = null
        try {
            versionName = context.packageManager.getPackageInfo(context.packageName, 0).versionName
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return versionName
    }

    fun getAppVersionCode(context: Context): Int {
        var versionCode = 0
        try {
            versionCode = context.packageManager.getPackageInfo(context.packageName, 0).versionCode
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return versionCode
    }

    fun isInternet(context: Context): Boolean {
        val zConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val zNetworkInfo = zConnectivityManager.activeNetworkInfo
        return zNetworkInfo != null && zNetworkInfo.isConnectedOrConnecting
    }

    fun hideKeyboard(context: Activity) {
        try {
            val inputManager = context
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            inputManager.hideSoftInputFromWindow(context.currentFocus!!
                    .windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun generateMD5Hash(key: String): String {
        var m: MessageDigest? = null
        try {
            m = MessageDigest.getInstance("MD5")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        m!!.update(key.toByteArray(), 0, key.length)
        return BigInteger(1, m.digest()).toString(16)
    }

    fun generateEpochTime(): String? {
        var epochtime: String? = null
        try {
            epochtime = (System.currentTimeMillis() / 1000).toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return epochtime
    }

    fun isValidEmail(email: String): Boolean {
        val emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val matcher: Matcher?
        val pattern = Pattern.compile(emailPattern)
        matcher = pattern.matcher(email)
        return matcher?.matches() ?: false
    }

    fun capitalizeString(string: String?): String {
        if (string == null) {
            throw NullPointerException("String to capitalize cannot be null")
        }
        val chars = string.toLowerCase().toCharArray()
        var found = false
        for (i in chars.indices) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i])
                found = true
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') {
                found = false
            }
        }
        return String(chars)
    }

    fun getImageFilePath(uri: Uri?, context: Context): String? {
        var filePath: String? = null
        Log.d("", "URI = " + uri!!)
        if (uri != null && "content" == uri.scheme) {
            val cursor = context.contentResolver.query(uri, arrayOf(MediaStore.Images.ImageColumns.DATA), null, null, null)
            cursor!!.moveToFirst()
            filePath = cursor.getString(0)
            cursor.close()
        } else {
            filePath = uri.path
        }
        Log.d("", "Chosen path = " + filePath!!)
        return filePath
    }

    fun getFileName(uri: Uri, context: Context): String {
        val file = File(getFilePath(uri, context)!!)
        return file.name
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun getFilePath(uri: Uri?, context: Context): String? {
        val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]

                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }
                // TODO handle non-primary volumes
            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id))
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])
                return getDataColumn(context, contentUri, selection, selectionArgs)
            }// MediaProvider
            // DownloadsProvider
        } else if ("content".equals(uri?.scheme!!, ignoreCase = true)) {
            return getDataColumn(context, uri, null, null)
        } else if ("file".equals(uri?.scheme!!, ignoreCase = true)) {
            return uri.path
        }// File
        // MediaStore (and general)
        return null
    }

    fun getDataColumn(context: Context, uri: Uri?, selection: String?,
                      selectionArgs: Array<String>?): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)
        try {
            cursor = context.contentResolver.query(uri!!, projection, selection, selectionArgs, null)
            if (cursor != null && cursor.moveToFirst()) {
                val column_index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(column_index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    fun isExternalStorageDocument(uri: Uri?): Boolean {
        return "com.android.externalstorage.documents" == uri!!.authority
    }

    fun isDownloadsDocument(uri: Uri?): Boolean {
        return "com.android.providers.downloads.documents" == uri!!.authority
    }

    fun isMediaDocument(uri: Uri?): Boolean {
        return "com.android.providers.media.documents" == uri!!.authority
    }

    fun calculateInSampleSize(
            options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2
            while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    fun decodeSampledBitmap(imageFileName: String?, reqWidth: Int, reqHeight: Int): Bitmap {
        // First decode with inJustDecodeBounds=true to check dimensions
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(imageFileName, options)

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(imageFileName, options)
    }

    /**
     * Method for getting the orientation of image
     * @param context
     * @param imageUri
     * @param imagePath
     * @return
     */
    fun getCameraPhotoOrientation(context: Context, imagePath: String?): Int {
        var rotate = 0
        try {
            context.contentResolver.notifyChange(Uri.fromFile(File(imagePath)), null)
            val imageFile = File(imagePath)
            val exif = ExifInterface(
                    imageFile.absolutePath)
            val orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL)
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
                ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
                ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return rotate
    }

    fun getVideoThumb(filePath:String): Bitmap {
        val file = File(filePath)
        return ThumbnailUtils.createVideoThumbnail(file.getAbsolutePath(), MediaStore.Video.Thumbnails.MINI_KIND)!!
    }

    fun getOutputProfileMediaFile(folderName: String): File? {
        val mediaStorageDir = File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                folderName)
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("County apps", "failed to create directory")
                return null
            }
        }
        val mediaFile: File
        mediaFile = File(mediaStorageDir.path + File.separator + "profile_pic" + ".jpg")
        return mediaFile
    }

    fun getOutputMediaFile(appName: String): File? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(Date())
        val mediaStorageDir = File(
                Environment.getExternalStorageDirectory(),
                appName + File.separator + "Photos");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null
            }
        }
        val mediaFile: File
        mediaFile = File(mediaStorageDir.path+ File.separator
                + "IMG_" + timeStamp + ".jpg")
        return mediaFile
    }

    fun getOutputVideoMediaFile(appName: String): File? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(Date())
        val mediaStorageDir = File(
                Environment
                        .getExternalStorageDirectory(),
                appName + File.separator + "Videos");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null
            }
        }
        val mediaFile: File
        mediaFile = File(mediaStorageDir.path+ File.separator
                + "VID_" + timeStamp + ".mp4")
        return mediaFile
    }

    fun getTodaysDate(format: String): String {
        val sdf = SimpleDateFormat(format)
        val date = Date()
        return sdf.format(date)
    }

    fun getDataFolder(context: Context): File? {
        var dataDir: File? = null
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            dataDir = File(Environment.getExternalStorageDirectory(), "AIIT")
            if (!dataDir.isDirectory) {
                dataDir.mkdirs()
            }
        }
        if (!dataDir!!.isDirectory) {
            dataDir = context.filesDir
        }
        return dataDir
    }

    fun showProgressBar(mContext: Context, message: String): Dialog {
        val progress = Dialog(mContext)
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progress.setContentView(R.layout.progress_dialog)
        val TVmessage = progress.findViewById<View>(R.id.TVmessage) as TextView
        TVmessage.setText(message)
        progress.setCancelable(true)

        progress.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return progress
    }

    fun showRetryDialog(mContext: Context, onRetry: OnRetryButtonClick): Dialog {
        val progress = Dialog(mContext)
        val onRetryListener = onRetry
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progress.setContentView(R.layout.retry_dialog)
        val btRetry = progress.findViewById<View>(R.id.btRetry) as Button
        btRetry.setOnClickListener(View.OnClickListener { onRetryListener.onRetryClick() })
        progress.setCancelable(true)
        progress.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return progress
    }

    fun avoidDoubleClicks(view: View) {
        val DELAY_IN_MS: Long = 900
        if (!view.isClickable) {
            return
        }
        view.isClickable = false
        view.postDelayed({ view.isClickable = true }, DELAY_IN_MS)
    }

    interface OnRetryButtonClick {
        public fun onRetryClick();
    }


    //Animations////
     fun fadeOutAndHideImage(img: TextView) {
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator()
        fadeOut.duration = 1000

        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation) {
                img.setVisibility(View.GONE)
            }

            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationStart(animation: Animation) {}
        })

        img.startAnimation(fadeOut)
    }

    fun checkPermission(activity: Activity?): Boolean {
        val result =
            ContextCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        val result1 =
            ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
        val result2 = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(activity: Activity?, permission: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(activity!!, permission, requestCode)
    }
}
