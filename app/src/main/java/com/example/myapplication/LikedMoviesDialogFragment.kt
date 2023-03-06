package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class LikedMoviesDialogFragment(val likedMoviesList: List<LikedMoviesDataClass>): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("Showing the dialog Liked Movies $likedMoviesList")
            .setPositiveButton("ok") { _,_ -> }
            .create()

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }
}