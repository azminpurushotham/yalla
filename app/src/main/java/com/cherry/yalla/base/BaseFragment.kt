package com.application.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.cherry.yalla.R
import com.cherry.yalla.utility.Util

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment <VB:ViewBinding>(private val inflate: Inflate<VB>) : Fragment(){

    interface OnRetryButtonClick {
        public fun onRetryClick();
    }

    protected abstract fun getRetryListener(): OnRetryButtonClick

    private var _binding: VB? = null
    val binding get() = _binding!!
    var fragmentSwitchListener: BaseActivity.OnFragmentSwitchListener? = null

    var mProgressDialog: Dialog? = null
    var onRetryListener: BaseActivity.OnRetryButtonClick? = null
    var mContext: Context? = null
//    @Inject
//    lateinit var mySharedPreferences: MySharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        mProgressDialog = Util.showProgressBar(requireContext(), getString(R.string.loading))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        try {
            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.windowToken, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

//        if (context != null) {
//            if (context is BaseActivity) {
//                try {
//                    fragmentSwitchListener = context as BaseActivity.OnFragmentSwitchListener?
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//
//            }
//        }
    }

    protected open fun stopAnim() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog!!.isShowing()) {
                    mProgressDialog!!.dismiss()
                }
            }
        } catch (e: IllegalArgumentException) {
            Log.e("error: ", e.localizedMessage)
        }
    }

    protected open fun startAnim() {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = Util.showProgressBar(requireContext(), getString(R.string.loading))
            } else {
                if (!mProgressDialog!!.isShowing()) {
                    mProgressDialog!!.show()
                }
            }
        } catch (e: WindowManager.BadTokenException) {
            e.printStackTrace()
        }
    }

    fun isAddedFragment():Boolean{
        return activity!=null && isAdded
    }

    companion object {
        fun hideKeyboard(context: Activity?) {
            try {
                val inputManager = context!!
                        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

                inputManager.hideSoftInputFromWindow(
                    context.currentFocus!!
                        .windowToken, InputMethodManager.HIDE_NOT_ALWAYS
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    open fun moveToNext(intent: Intent, isFinish: Boolean) {
        if (isFinish) {
            startActivity(intent)
//            Bungee.fade(requireContext())
            requireActivity().finish()
        } else {
            startActivity(intent)
//            Bungee.fade(requireContext())
        }
    }

}
