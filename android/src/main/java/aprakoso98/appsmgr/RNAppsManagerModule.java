package aprakoso98.appsmgr;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.io.ByteArrayOutputStream;

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
            String iconB64 = encodeIcon(icon);
            cb.invoke("data:image/png;base64," + iconB64);
        } catch (PackageManager.NameNotFoundException e){
            Log.d("", pkgName);
        }
    }
    private String encodeIcon (Drawable icon){
        BitmapDrawable bitDw = ((BitmapDrawable) icon);
        Bitmap bitmap = bitDw.getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }
}
