package aprakoso98.appsmgr

import android.app.ActivityManager
import android.content.pm.ApplicationInfo
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import com.facebook.react.bridge.*
import java.io.ByteArrayOutputStream
import android.content.Context.ACTIVITY_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.content.Context.ACTIVITY_SERVICE
import android.content.pm.PackageManager
import java.lang.reflect.AccessibleObject.setAccessible
import android.R.attr.process
import android.content.Context.ACTIVITY_SERVICE
import androidx.core.content.ContextCompat.getSystemService









class Methods {
    companion object {

        fun getIconFromPkgName(pkgName: String, context: ReactApplicationContext):String {
            try {
                val icon = context.packageManager.getApplicationIcon(pkgName)
                val iconB64 = encodeIcon(icon)
                return "data:image/png;base64,$iconB64"
            } catch (e: NotImplementedError){
                return ""
            }
        }

        fun getAppInfo (context: ReactApplicationContext, type: String):WritableNativeArray{
            val list = WritableNativeArray()
            val pm = context.getPackageManager()
            val listApps = pm.getInstalledPackages(0)
            for (pi in listApps) {
                try {
                    var isSystem = false
                    val map = Arguments.createMap()
                    val ai = pm.getApplicationInfo(pi.packageName, 0)
                    val versionCode = pi.versionCode
                    if (ai.flags and ApplicationInfo.FLAG_SYSTEM != 0) {
                        isSystem = true
                    } else {
                        val iconApp = getIconFromPkgName(pi.packageName, context)
                        map.putString("iconApp", iconApp)
                    }
                    map.putBoolean("isSystemApps", isSystem)
                    map.putString("versionName", pi.versionName.toString())
                    map.putString("uid", ai.uid.toString())
                    map.putString("versionCode", versionCode.toString())
                    map.putString("appName", pm.getApplicationLabel(ai).toString())
                    map.putString("packageName", pi.packageName)
                    map.putString("publicSourceDir", ai.publicSourceDir)
                    map.putString("firstInstallTime", pi.firstInstallTime.toString())
                    map.putString("lastUpdateTime", pi.lastUpdateTime.toString())
                    if (type == "NonSystem") {
                        if (!isSystem) {
                            list.pushMap(map)
                        }
                    } else if (type == "System") {
                        if (isSystem) {
                            list.pushMap(map)
                        }
                    } else {
                        list.pushMap(map)
                    }
                } catch (e: NotImplementedError){
                }
            }
            return list
        }

        fun killApp (context: ReactApplicationContext, pkgName: String){
//            val am = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
            val am = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
            val runningProcesses = am.runningAppProcesses

            for (runningProcess in runningProcesses) {
                if (runningProcess.processName.equals(pkgName)) {
                    android.os.Process.sendSignal(runningProcess.pid, android.os.Process.SIGNAL_KILL)
//                    android.os.Process.sendSignal(runningProcess.pid, android.os.Process.SIGNAL_QUIT)
//                    am.killBackgroundProcesses(pkgName)
//                    android.os.Process.killProcess(runningProcess.pid)
                }
            }
//            android.os.Process.killProcess(pUID.toInt());
        }

        fun checkAppIsRunning (pkgName: String, context: ReactApplicationContext):Boolean{
//            val am = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
//            val taskInfo = am.getRunningTasks()
//
//            Toast.makeText(getApplicationContext(), taskInfo.get(0).topActivity.getClassName(), Toast.LENGTH_LONG).show()
//            val componentInfo = taskInfo.get(0).topActivity
//
//            val packageName = componentInfo.getPackageName()
            return true
        }

        private fun encodeIcon(icon: Drawable): String {
            val bitDw = icon as BitmapDrawable
            val bitmap = bitDw.bitmap
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
        }
    }
}