package com.androiddev.zf.firstcodestudy.chapter12;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.androiddev.zf.firstcodestudy.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * CoordinatorLayout可以说是一个加强版的FrameLayout  在普通情况下的作用和FrameLayout基本一致
 * <p>
 * AppBarLayout实际上是一个垂直方向的LinearLayout,它在内部做了很多滚动事件的封装，并应用了一些Material Design的设计理念
 */
public class MaterialActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    /**
     * DrawerLayout控件是由support-v4库提供的。DrawerLayout中放置了两个直接子控件，第一个子控件是FrameLayout，
     * 用于作为主屏幕中显示的内容。 第二个子控件是要作为滑动菜单中显示的内容。
     * 但是第二个子控件有一点需要注意，layout_gravity这个属性是必须指定的，因为我们需要告诉DrawerLayout滑动菜单是在
     * 屏幕的左边还是右边，指定left表示滑动菜单的左边，指定right便是滑动菜单的右边。
     * 这里指定了start，表示会根据系统语言进行判断，如果系统语言是从左往右的，比如英语，汉语，滑动菜单就在左边 否则-----
     */
    private DrawerLayout mDrawerLayout;
    /**
     * navigationview需要一个菜单项和一个头布局
     * 菜单项需要在menu标签中嵌套一个group标签 然后将group的checkableBehavior属性指定为single。group表示一组
     * checkableBehavior指定为single表示组中的所有菜单项只能单选
     * <p>
     * headerlayout就是一个layout.xml文件
     * 然后用app:menu、app:headerLayout属性设置它们
     */
    private NavigationView mNavView;
    private FloatingActionButton mFab;
    private RecyclerView mRvMaterial;
    private List<Scene> mScenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        initData();
        initView();
    }

    private void initData() {
        mScenes = new ArrayList<>();
        mScenes.add(new Scene("风景1", R.drawable.scene1));
        mScenes.add(new Scene("风景2", R.drawable.scene2));
        mScenes.add(new Scene("风景3", R.drawable.scene3));
        mScenes.add(new Scene("风景4", R.drawable.scene4));
        mScenes.add(new Scene("风景1", R.drawable.scene1));
        mScenes.add(new Scene("风景2", R.drawable.scene2));
        mScenes.add(new Scene("风景3", R.drawable.scene3));
        mScenes.add(new Scene("风景4", R.drawable.scene4));
        mScenes.add(new Scene("风景1", R.drawable.scene1));
        mScenes.add(new Scene("风景2", R.drawable.scene2));
        mScenes.add(new Scene("风景3", R.drawable.scene3));
        mScenes.add(new Scene("风景4", R.drawable.scene4));
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.category);
            actionBar.setTitle("Material Design实战");
        }

        mNavView = (NavigationView) findViewById(R.id.nav_view);
        //设置选中的项
        mNavView.setCheckedItem(R.id.nav_call);
        //设置菜单项的点击事件
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return true;
            }
        });
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(this);
        MaterialAdapter adapter = new MaterialAdapter(R.layout.recycler_card_item, mScenes);
        mRvMaterial = (RecyclerView) findViewById(R.id.rv_material);
        mRvMaterial.setHasFixedSize(true);
        mRvMaterial.setLayoutManager(new GridLayoutManager(this, 2));
        mRvMaterial.setAdapter(adapter);
        mRvMaterial.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MaterialActivity.this, DetailedActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String arg0) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String arg0) {
                return false;
            }
        });
        return true;
    }

    /**
     * 菜单的item标签来定义action按钮，android:id用于指定按钮的id，android:icon用于指定按钮的图标，
     * android:title用于指定按钮的文字
     * <p>
     * 接着使用app:showAsAction来指定按钮的显示位置，之所以这里再次使用app命名空间，是为了能够兼容低版本的系统。
     * showAsAction主要有以下几种值可以选:always表示永远显示在Toolbar中。如果屏幕空间不过则不显示；ifRoom表示
     * 屏幕空间足够的情况下显示在Toolbar中，不够的话就显示在菜单当中；never则表示永远显示在菜单当中。注意，Toolbar
     * 当中的action按钮只会显示图标，菜单中的action按钮只会显示文字
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //openDrawer传入一个Gravity参数，这里要保证和XML中定义的一致
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "clicked", Snackbar.LENGTH_SHORT)
                        //设置一个点击事件
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MaterialActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;
        }
    }

    private class MaterialAdapter extends BaseQuickAdapter<Scene, BaseViewHolder> {

        public MaterialAdapter(int layoutResId, List<Scene> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Scene item) {
            helper.setText(R.id.scene_name, item.getName())
                    .setImageResource(R.id.scene_image, item.getImageId())
                    .addOnClickListener(R.id.card_group);
        }
    }
}
