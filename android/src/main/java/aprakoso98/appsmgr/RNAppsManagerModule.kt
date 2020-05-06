package aprakoso98.appsmgr

import com.facebook.react.bridge.*

class RNAppsManagerModule(private val context: ReactApplicationContext) : ReactContextBaseJavaModule(context) {

    override fun getName(): String {
        return "RNAppsManager"
    }

    @ReactMethod
    fun getIconFromPkgName(pkgName: String, promise: Promise) {
        try {
            val icon = Methods.getIconFromPkgName(pkgName, context)
            promise.resolve(icon)
        } catch (e: NotImplementedError){
            promise.reject(e)
        }
    }

    @ReactMethod
    fun getInformationApp(type: String = "", promise: Promise){
        try {
            val list = Methods.getAppInfo(context, type)
            promise.resolve(list)
        } catch (e: NotImplementedError){
            promise.reject(e)
        }
    }

    @ReactMethod
    fun killRunningProcess(pkgName: String, promise: Promise){
        try{
            Methods.killApp(context, pkgName)
            promise.resolve(true)
        } catch (e: NotImplementedError){
            promise.reject(e)
        }
    }
}
