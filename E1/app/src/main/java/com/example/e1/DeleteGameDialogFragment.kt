package com.example.e1

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DeleteGameDialogFragment(val game: Game, val function: (Game) -> Unit) : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d("Delete Game","DeleteGameDialogFragment.onCreateDialog")
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Delete Game: ${game.name}?")
                .setPositiveButton("Yes",
                    DialogInterface.OnClickListener { dialog, id ->
                        Singleton.list.remove(game)
                        function(game)
                    })
                .setNegativeButton("No",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}