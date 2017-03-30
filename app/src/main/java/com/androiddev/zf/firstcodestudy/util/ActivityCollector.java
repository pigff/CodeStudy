package com.androiddev.zf.firstcodestudy.util;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greedy on 17/3/30.
 *
 * 随时随地退出程序
 */

public class ActivityCollector {

    public static List<AppCompatActivity> sActivities = new ArrayList<>();

    public static void addActivity(AppCompatActivity appCompatActivity) {
        sActivities.add(appCompatActivity);
    }

    public static void removeActivity(AppCompatActivity appCompatActivity) {
        sActivities.remove(appCompatActivity);
    }

    public static void finishAll() {
        for (AppCompatActivity activity : sActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
            sActivities.remove(activity);
        }
    }
}
