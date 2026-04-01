package com.king.ultraswiperefresh.app.ext

import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIApplication
import platform.Foundation.NSTimer

actual fun showToast(text: String) {
    val alert = UIAlertController.alertControllerWithTitle(
        title = null,
        message = text,
        preferredStyle = UIAlertControllerStyleAlert
    )
    
    val viewController = UIApplication.sharedApplication.keyWindow?.rootViewController
    viewController?.presentViewController(alert, animated = true, completion = null)
    
    NSTimer.scheduledTimerWithTimeInterval(2.0, true) {
        alert.dismissViewControllerAnimated(true, completion = null)
    }
}
