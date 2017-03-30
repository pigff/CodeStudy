package com.androiddev.zf.firstcodestudy.chapter01;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.androiddev.zf.firstcodestudy.BaseActivity;
import com.androiddev.zf.firstcodestudy.R;

/**
 * Created by greedy on 17/3/30.
 * 隐式跳转：
 * 在Manifests中指定<action>和<category> 当其中的内容能够同时匹配上Intent中指定的action和category时。这个活动就能启动
 * android.intent.category.DEFAULT是一种默认的category
 *
 * <data>标签
 * android:scheme  用于指定数据的协议部分，如http部分
 * android:host   用于指定数据的主机名部分， 如www.baidu.com部分
 * android:port   用于指定数据的端口部分，一般紧随在主机名之后。
 * android:path     用于指定主机名和端口之后的部分，如一段网址中跟在域名之后的内容。
 * android:mimeType     用于指定可以处理的数据类型，允许使用通配符的方式进行指定
 */

public class HideActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide);
    }
}
