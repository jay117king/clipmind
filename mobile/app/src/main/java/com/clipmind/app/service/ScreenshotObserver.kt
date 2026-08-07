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
 * When a new screenshot is detected, it notifies the callback.
 */
class ScreenshotObserver(
    private val context: Context,
    private val onNewScreenshot: (Uri, String?) -> Unit
) : ContentObserver(Handler(Looper.getMainLooper())) {

    private var lastProcessedUri: String? = null

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
        // MediaStore often fires with a generic URI; we query for the latest image
        checkForNewScreenshot()
    }

    private fun checkForNewScreenshot() {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DATE_ADDED
        )

        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

        try {
            context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                sortOrder
            )?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val idIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                    val nameIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                    val dataIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

                    val id = cursor.getLong(idIndex)
                    val displayName = cursor.getString(nameIndex) ?: ""
                    val dataPath = cursor.getString(dataIndex)

                    val contentUri = Uri.withAppendedPath(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id.toString()
                    )

                    // Avoid processing the same image repeatedly
                    if (contentUri.toString() == lastProcessedUri) return

                    // Heuristic: most screenshots contain "Screenshot" in the name or path
                    val isScreenshot = displayName.contains("Screenshot", ignoreCase = true) ||
                            (dataPath?.contains("Screenshot", ignoreCase = true) == true) ||
                            (dataPath?.contains("screen", ignoreCase = true) == true)

                    if (isScreenshot) {
                        lastProcessedUri = contentUri.toString()
                        Log.d(TAG, "New screenshot detected: $displayName")
                        onNewScreenshot(contentUri, dataPath)
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Permission denied while reading MediaStore", e)
        } catch (e: Exception) {
            Log.e(TAG, "Error checking for screenshots", e)
        }
    }

    companion object {
        private const val TAG = "ScreenshotObserver"
    }
}
