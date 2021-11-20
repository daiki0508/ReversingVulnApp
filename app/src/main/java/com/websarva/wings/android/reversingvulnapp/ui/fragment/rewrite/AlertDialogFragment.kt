package com.websarva.wings.android.reversingvulnapp.ui.fragment.rewrite

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.websarva.wings.android.reversingvulnapp.R
import com.websarva.wings.android.reversingvulnapp.ui.MainActivity
import java.lang.Exception
import java.lang.IllegalStateException

class AlertDialogFragment(private val flag: Boolean): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            // flagがtrueがSuccessルート
            val builder = AlertDialog.Builder(it)
                .setTitle(if (flag){
                    "Wow!"
                }else {
                    "Oh..."
                })
                .setMessage(if (flag){
                    "Please tap the OK button as it is."

                }else{
                    "fake{hogehogeFugaFuga}"
                })
                .setPositiveButton("OK"){_, _ ->
                    if (flag){
                        parentFragmentManager.setFragmentResult(DialogBundle.RewriteDialog.name, Bundle())
                    }else{
                        with(Intent(it, MainActivity::class.java)){
                            it.finish()
                            startActivity(this)
                            it.overridePendingTransition(R.anim.fragment_pop_enter_anim, R.anim.fragment_pop_exit_anim)
                        }
                    }
                }
            builder.create()
        }
        this.isCancelable = false

        return dialog ?: throw IllegalStateException("activity is null.")
    }
}