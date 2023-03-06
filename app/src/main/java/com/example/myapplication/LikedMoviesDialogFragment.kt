package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class LikedMoviesDialogFragment(val likedMoviesList: List<LikedMoviesDataClass>): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var listOfLikedMovies = mutableListOf<String>()
        likedMoviesList.forEach { listOfLikedMovies.add(it.name) }

        return AlertDialog.Builder(requireContext())
            .setPositiveButton("ok") { _, _ -> }
            .setTitle("Liked Movies")
            .setItems(listOfLikedMovies.toTypedArray(), { _, _ ->})
            .create()
    }

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }
}