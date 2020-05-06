package aprakoso98.appsmgr

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import android.util.Log
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import java.io.ByteArrayOutputStream

class AppsManager  {
    @ReactMethod
    fun getIcon(pkgName: String, cb: Callback) {
        try {
//            val icon = reactContext.packageManager.getApplicationIcon(pkgName)
//            val iconB64 = encodeIcon(icon)
//            cb.invoke("data:image/png;base64,$iconB64")
        } catch (e: PackageManager.NameNotFoundException) {
            Log.d("", pkgName)
        }

    }
}