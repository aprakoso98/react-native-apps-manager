package aprakoso98.appsmgr;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
public class RNAppsManagerModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNAppsManagerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNAppsManager";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }
    @ReactMethod
    public void getIcon(String pkgName, Callback cb){
        try {
            Drawable icon = reactContext.getPackageManager().getApplicationIcon(pkgName);
            String iconB64 = Methods.Companion.encodeIcon(icon);
            cb.invoke("data:image/png;base64," + iconB64);
        } catch (PackageManager.NameNotFoundException e){
            Log.d("", pkgName);
        }
    }
}
