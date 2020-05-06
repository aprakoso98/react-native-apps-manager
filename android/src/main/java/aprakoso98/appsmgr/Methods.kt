package aprakoso98.appsmgr

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import java.io.ByteArrayOutputStream

class Methods {
    companion object {
        fun encodeIcon(icon: Drawable): String {
            val bitDw = icon as BitmapDrawable
            val bitmap = bitDw.bitmap
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
        }
    }
}