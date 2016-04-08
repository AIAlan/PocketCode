package com.geekai.library.support;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

/**
 * Created by jikai on 16/3/2.
 *  蒲公英检查更新
 */
public class PGYCheckUpdate {

    public static void checkUpdate(final Activity activity) {
        PgyUpdateManager.register(activity, new UpdateManagerListener() {
            @Override
            public void onNoUpdateAvailable() {
                // TODO: 16/4/3
            }

            @Override
            public void onUpdateAvailable(String result) {
                // 将新版本信息封装到AppBean中
                final AppBean appBean = getAppBeanFromString(result);
                new AlertDialog.Builder(activity)
                        .setTitle("更新")
                        .setMessage(appBean.getReleaseNote())
                        .setNegativeButton(
                                "确定",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        startDownloadTask(
                                                activity,
                                                appBean.getDownloadURL());
                                    }
                                }).show();
            }
        });
    }

}
