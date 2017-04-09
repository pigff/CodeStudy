package com.androiddev.zf.firstcodestudy.chapter07;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * Exported表示是否允许外部程序访问内容提供器
 * Enable表示是否启用这个内容提供器
 *
 *
 */
public class MyContentProvider extends ContentProvider {
    /**
     * 还需要UriMatcher,匹配内容URI  例如
     *
     */

//    private static UriMatcher sUriMatcher;
//    static {
//        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//        sUriMatcher.addURI("","", 0);
//    }
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * 是所有内容提供器都必须提供的一个方法，用于获取uri对象所对应的MIME类型。一个内容URI所对应的MIME字符串主要由
     * 3部分组成
     * 1、必须以vhd开头
     * 2、如果内容URI以路径结尾，则后接android.cursor.dir/,如果内容URI以id结尾，则后接android.cusor.item/。
     * 3、最后接上vnd.<authority>.<path>
     *
     * 借助UriMatch匹配数据类型
     * switch(sUriMatcher.match(uri)) {
     *     case :
     *      break;
     * }
     * @param uri
     * @return
     */
    @Override
    public String getType(Uri uri) {
        // Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * 在这里创建一个SQLiteDatabaseHelper的实例
     * context通过getContext()来获取
     * 然后借住Helper来对表进去增删改查
     * @return
     */
    @Override
    public boolean onCreate() {
//         Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        //  Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
