package com.androiddev.zf.firstcodestudy.chapter12;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.androiddev.zf.firstcodestudy.R;

/**
 * fitSystemWindows这个属性要在imageview和其所有父布局上设置
 *
 * 透明状态栏: 创建个v21的包  ---- 在style 和 style-v21 上都写上有相同名字的style 然后将其应用到对应的activity上
 */
public class DetailedActivity extends AppCompatActivity {

    private ImageView mIvScene;
    private Toolbar mDetaildToolbar;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private AppBarLayout mAppbar;
    private TextView mDetaildContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        initView();
    }

    private void initView() {
        mIvScene = (ImageView) findViewById(R.id.iv_scene);
        mDetaildToolbar = (Toolbar) findViewById(R.id.detaild_toolbar);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mAppbar = (AppBarLayout) findViewById(R.id.appbar);
        mDetaildContent = (TextView) findViewById(R.id.detaild_content);
        setSupportActionBar(mDetaildToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mCollapsingToolbar.setTitle("风景");
        mIvScene.setImageResource(R.drawable.scene1);
        mDetaildContent.setText(getContent());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getContent() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            stringBuilder.append("风景真的很漂亮啊");
        }
        return stringBuilder.toString();
    }
}
