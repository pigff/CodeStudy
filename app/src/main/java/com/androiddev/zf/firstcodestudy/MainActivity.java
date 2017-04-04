package com.androiddev.zf.firstcodestudy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 隐式跳转
     */
    private Button mBtn01;
    /**
     * 隐式跳转
     */
    private Button mBtn02;
    /**
     * 隐式跳转
     */
    private Button mBtn03;
    /**
     * 隐式跳转
     */
    private Button mBtn04;
    /**
     * 隐式跳转
     */
    private Button mBtn05;
    /**
     * 隐式跳转
     */
    private Button mBtn06;
    /**
     * 隐式跳转
     */
    private Button mBtn07;
    /**
     * 隐式跳转
     */
    private Button mBtn08;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mBtn01 = (Button) findViewById(R.id.btn_01);
        mBtn01.setOnClickListener(this);
        mBtn02 = (Button) findViewById(R.id.btn_02);
        mBtn02.setOnClickListener(this);
        mBtn03 = (Button) findViewById(R.id.btn_03);
        mBtn03.setOnClickListener(this);
        mBtn04 = (Button) findViewById(R.id.btn_04);
        mBtn04.setOnClickListener(this);
        mBtn05 = (Button) findViewById(R.id.btn_05);
        mBtn05.setOnClickListener(this);
        mBtn06 = (Button) findViewById(R.id.btn_06);
        mBtn06.setOnClickListener(this);
        mBtn07 = (Button) findViewById(R.id.btn_07);
        mBtn07.setOnClickListener(this);
        mBtn08 = (Button) findViewById(R.id.btn_08);
        mBtn08.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_01:
                //隐式启动Activity
//                Intent intent = new Intent("ACTION_START");
//                startActivity(intent);

                //通过隐式Intent启动其他程序的活动     -- -- 感觉这个比较有用
                /**
                 * setData()
                 * 接收一个Uri对象，主要用于指定当前Intent正在操作的数据，而这些数据通常都是以字符串的形式传入到Uri.parse()方法中解析产生的。
                 */
                Intent intent = new Intent();
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
                break;
            case R.id.btn_02:
                break;
            case R.id.btn_03:
                break;
            case R.id.btn_04:
                break;
            case R.id.btn_05:
                break;
            case R.id.btn_06:
                break;
            case R.id.btn_07:
                break;
            case R.id.btn_08:
                break;
        }
    }
}
