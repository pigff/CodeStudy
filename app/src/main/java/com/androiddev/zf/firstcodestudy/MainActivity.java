package com.androiddev.zf.firstcodestudy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btn_01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

    }
}
