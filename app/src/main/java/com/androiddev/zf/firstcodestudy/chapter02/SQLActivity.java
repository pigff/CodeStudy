package com.androiddev.zf.firstcodestudy.chapter02;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.androiddev.zf.firstcodestudy.R;

/**
 * getReadableDatabase()和getWritableDatabase()。这两个方法都可以创建或打开一个现有数据库
 * (如果数据库已存在则直接打开，否则创建一个新的数据库),并返回一个可对数据进行读写操作的对象。
 * 不同的是，当数据库不可写入的时候(如磁盘已满),getReadalbeDatabase()方法返回的对象将以只读的方式去打开数据库,
 * 而getWritableDatabase()方法则将出现异常。
 * <p>
 * SQLiteOpenHelper中有两个构造方法可供重写，一般使用参数少的那一个。
 * 第一个参数是Context，第二个是数据库名字，第三个是允许我们在查询数据的时候返回一个自定义的null，一般传null
 * 第四个参数是当前数据库的版本号。 构建出SQLiteOpenHelper的实例之后，再调用它的getReadableDatabase()或getWriteableDatabase()
 * 方法就能够创建数据库了。
 * 数据库文件会存放在/data/data/<package name>/databases/目录下。此时，重写onCreate()方法也会得到执行，所以通常会在这里去处理一些创建表的逻辑
 * <p>
 * 更新数据库
 * 重写onUpgrade方法(一般就是先把表删了， 再重建)  版本后需增加
 */
public class SQLActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 创建表
     */
    private Button mBtnSqlCreate;
    /**
     * 增加
     */
    private Button mBtnSqlAdd;
    /**
     * 删除
     */
    private Button mBtnSqlDel;
    /**
     * 更改
     */
    private Button mBtnSqlUpdate;
    /**
     * 查找
     */
    private Button mBtnSqlQuery;
    private MyDatabaseHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        mHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        initView();
    }

    private void initView() {
        mBtnSqlCreate = (Button) findViewById(R.id.btn_sql_create);
        mBtnSqlCreate.setOnClickListener(this);
        mBtnSqlAdd = (Button) findViewById(R.id.btn_sql_add);
        mBtnSqlAdd.setOnClickListener(this);
        mBtnSqlDel = (Button) findViewById(R.id.btn_sql_del);
        mBtnSqlDel.setOnClickListener(this);
        mBtnSqlUpdate = (Button) findViewById(R.id.btn_sql_update);
        mBtnSqlUpdate.setOnClickListener(this);
        mBtnSqlQuery = (Button) findViewById(R.id.btn_sql_query);
        mBtnSqlQuery.setOnClickListener(this);
    }

    /**
     * query()方法参数              对应SQL部分                   描述
     * table                     from table_name            指定查询的表名
     * columns                select column1, colunmn       指定查询的列名
     * selection              where column = value          指定where的约束条件
     * selectionArgs          -                             为where重的占位符提供具体的值
     * groupBy                group by column               指定需要group by的列
     * having                 having column = value         对group by后的结果进一步约束
     * orderBy                order by column1, column2     指定查询结果的排序方式
     * @param v
     */
    @Override
    public void onClick(View v) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = mHelper.getWritableDatabase();
        switch (v.getId()) {
            case R.id.btn_sql_create:
                mHelper.getWritableDatabase();
                break;
            case R.id.btn_sql_add:
                values.put("name", "The Da Vinci code");
                values.put("author", "Dan");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values);
                values.clear();

                values.put("name", "The lost Symbol");
                values.put("author", "Dans");
                values.put("pages", 300);
                values.put("price", 19.96);
                db.insert("Book", null, values);
                break;
            case R.id.btn_sql_del:
                db.delete("Book", null, null);
                break;
            case R.id.btn_sql_update:
                values.put("price", 10.22);
                db.update("Book", values, "name = ?", new String[] {"The lost Symbol"});
                break;
            case R.id.btn_sql_query:
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("SQLActivity", name);
                        Log.d("SQLActivity", author);
                        Log.d("SQLActivity", "pages:" + pages);
                        Log.d("SQLActivity", "price:" + price);
                    } while (cursor.moveToNext());
                }
                break;
        }
    }
}
