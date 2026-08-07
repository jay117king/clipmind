package com.clipmind.app.service

import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log

/**
 * Observes MediaStore for new images and filters for screenshots.
 * When a new screenshot is detected, it should trigger OCR + storage.
 */
class ScreenshotObserver(
    private val context: Context,
    private val onNewScreenshot: (Uri) -> Unit
) : ContentObserver(Handler(Looper.getMainLooper())) {

    fun start() {
        context.contentResolver.registerContentObserver(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            true,
            this
        )
        Log.d(TAG, "ScreenshotObserver started")
    }

    fun stop() {
        context.contentResolver.unregisterContentObserver(this)
        Log.d(TAG, "ScreenshotObserver stopped")
    }

    override fun onChange(selfChange: Boolean, uri: Uri?) {
        super.onChange(selfChange, uri)
        uri ?: return

        // TODO: Query MediaStore to confirm it is a screenshot
        // (path contains "Screenshot" or is in the Screenshots folder)
        // Then call onNewScreenshot(uri)
        Log.d(TAG, "MediaStore change detected: $uri")
    }

    companion object {
        private const val TAG = "ScreenshotObserver"
    }
}
