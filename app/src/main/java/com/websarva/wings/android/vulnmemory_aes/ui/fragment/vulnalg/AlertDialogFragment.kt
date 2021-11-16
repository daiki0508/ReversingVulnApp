package com.websarva.wings.android.vulnmemory_aes.ui.fragment.vulnalg

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class AlertDialogFragment(private val flag: Boolean): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
                .setTitle(if (flag){
                    "Success!!"
                }else {
                    "Nope..."
                })
                .setMessage(if (flag){
                    "Congratulations.\nBy the way, did you get the flag?"
                }else {
                    "Unfortunately, please try again."
                })
                .setPositiveButton("OK"){_, _ ->

                }
            builder.create()
        }
        this.isCancelable = false

        return dialog ?: throw IllegalStateException("activity is null.")
    }
}