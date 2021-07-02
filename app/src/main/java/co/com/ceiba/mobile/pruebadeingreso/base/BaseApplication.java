package co.com.ceiba.mobile.pruebadeingreso.base;

import android.app.Application;
import android.util.Log;

import co.com.ceiba.mobile.pruebadeingreso.R;

public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";

    public BaseApplication() { }

    private static Application instance;
    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
    }

    public static void init(Application application) {
        instance = application;
        if (instance == null) {
            Log.e(TAG, "init  instance == null ！！！");
        }
    }

    public String getAppName() {
        return getResources().getString(R.string.app_name);
    }

}