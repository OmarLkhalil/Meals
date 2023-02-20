package com.example.domain.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment


fun View.show() {
    animate().cancel()

    visibility = View.VISIBLE
    animate()
        .alpha(1.0f)
        .setListener(null)
        .start()
}

fun View.hide(invisible: Boolean = false) {
    animate().cancel()

    animate()
        .alpha(0.0f)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                visibility = if (invisible) View.INVISIBLE else View.GONE
            }
        })
        .start()
}

inline fun <T : View> T.showIf(condition: () -> Boolean) {
    if (condition()) {
        show()
    } else {
        hide()
    }
}

fun showKeyboard(context: Context, editText: EditText){
    if (editText.requestFocus()){
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
    }

}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

