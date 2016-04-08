package com.jikai.standard.utils;

import android.util.Log;

import com.jikai.standard.BuildConfig;

/**
 * Created by jikai on 16/3/14.
 */
public class LogUtil {

    public static final String TAG_DEFAULT = "====standard====";
    public static final String TAG_EVENT = "====event====";

    public static void logEv(String str) {
        if (BuildConfig.DEBUG) Log.i(TAG_EVENT, str);
    }
}
