package com.app.hubert.guide;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;

import com.app.hubert.guide.core.Builder;

/**
 * Created by hubert
 * <p>
 * Created on 2017/7/27.
 */
public class NewbieGuide {

    public static final String TAG = "NewbieGuide";
    /**
     * 新手指引入口
     *
     * @param activity activity
     * @return builder对象，设置参数
     */
    public static Builder with(Activity activity) {
        return new Builder(activity);
    }

    public static Builder with(Fragment fragment) {
        return new Builder(fragment);
    }

    public static Builder with(android.support.v4.app.Fragment v4Fragment) {
        return new Builder(v4Fragment);
    }
}

