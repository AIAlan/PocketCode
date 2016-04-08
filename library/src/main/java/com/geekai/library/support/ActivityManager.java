package com.geekai.library.support;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * Created by jikai on 16/2/24.
 *  判断 app 是否处于前台
 */
public class ActivityManager {

    private static WeakReference<Activity> sCurrentActivityWeakRef;

    public static Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef.get();
        }
        return currentActivity;
    }

    public static void setCurrentActivity(Activity activity) {
        sCurrentActivityWeakRef = new WeakReference<Activity>(activity);
    }

    public static boolean isForeground() {
        return getCurrentActivity() != null;
    }
}