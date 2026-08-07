package com.clipmind.app.service

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent

/**
 * Accessibility Service used as a robust fallback for detecting screenshots.
 * Must be explicitly enabled by the user in system settings.
 *
 * Important: Clearly explain the purpose in the service description
 * and in the Play Store listing to pass review.
 */
class ClipMindAccessibilityService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
        val info = AccessibilityServiceInfo().apply {
            eventTypes = AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED or
                    AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
            flags = AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS
            notificationTimeout = 100
        }
        serviceInfo = info
        Log.d(TAG, "ClipMind Accessibility Service connected")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // TODO: Detect screenshot-related events or UI changes
        // This is a fallback — primary path should be MediaStore observer
    }

    override fun onInterrupt() {
        Log.d(TAG, "ClipMind Accessibility Service interrupted")
    }

    companion object {
        private const val TAG = "ClipMindA11y"
    }
}
