package com.priyanshu.androidapipractice.ui.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.airbnb.lottie.LottieAnimationView
import com.priyanshu.androidapipractice.R

class CustomLoaderDialog: DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.logo_loading,container,false)
        val lottiAnimation = view.findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        lottiAnimation.setAnimation(R.raw.train_loading)
        lottiAnimation.playAnimation()
        return view
    }

    companion object{
        fun newInstance(): CustomLoaderDialog{
            return CustomLoaderDialog()
        }
    }
}