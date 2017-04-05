package com.androiddev.zf.firstcodestudy.chapter02;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androiddev.zf.firstcodestudy.R;

/**
 * 三种方式可以得到SharedPreferences对象
 * 1.Context类中的getSharedPreferences()方法
 * 第一个参数用于指定SharedPreferences文件的名称，如果指定的文件不存在则会创建一个，
 * SharedPreferences文件都是存放在/data/data/<package name>/shared_prefs/目录下的。
 * 第二个参数用于指定操作模式，目前只有MODE_PRIVATE这一种模式可选，它是默认的操作模式，和直接传入0效果是相同的。
 * 表示只有当前的应用程序才可以对这个SharedPreferences文件进行读写。其他几种操作模式均已经被飞起。
 * 2.Activity类中的getPreferences()方法
 * 只接受一个操作模式参数，因为使用这个方法时会自动将当前活动的类名作为SharedPreferences的文件名。
 * 3.PreferenceManager类中的getDefaultSharedPreferences()方法
 * 这个一个静态方法，它接收一个Context参数，并自动使用当前应用程序的包名作为前缀来命名SharedPreferences文件。
 */
public class SharedPreferencesActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Button
     */
    private Button mBtnSpSave;
    /**
     * Button
     */
    private Button mBtnSpGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        initView();
    }

    private void initView() {
        mBtnSpSave = (Button) findViewById(R.id.btn_sp_save);
        mBtnSpSave.setOnClickListener(this);
        mBtnSpGet = (Button) findViewById(R.id.btn_sp_get);
        mBtnSpGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sp_save:
//                SharedPreferences sharedPreferences = getSharedPreferences("code_sp", MODE_PRIVATE);
//                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SharedPreferencesActivity.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("author", "guolin");
                editor.apply();
                break;
            case R.id.btn_sp_get:
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(SharedPreferencesActivity.this);
                String name = sp.getString("author", "null");
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
