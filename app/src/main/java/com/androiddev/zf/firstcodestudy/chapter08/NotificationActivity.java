package com.androiddev.zf.firstcodestudy.chapter08;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.androiddev.zf.firstcodestudy.R;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CHOOSE_PHOTO = 1;
    /**
     * Button
     */
    private Button mNotifyButton01;
    /**
     * Button
     */
    private Button mNotifyButton02;
    private NotificationManager mManager;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        //创建NotificationManager来对通知进行管理
        mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        initView();
    }

    private void initView() {
        mNotifyButton01 = (Button) findViewById(R.id.notify_button_01);
        mNotifyButton01.setOnClickListener(this);
        mNotifyButton02 = (Button) findViewById(R.id.notify_button_02);
        mNotifyButton02.setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.display_img);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notify_button_01:
                // PendingIntent更加倾向于在某个合适的时机去执行某个动作，所以，也可以把PendingIntent简单地理解为延迟执行的Intent
//                Intent intent = new Intent();
//                PendingIntent pi = PendingIntent.getActivity();
                Notification commonNotification = new NotificationCompat.Builder(this)
                        //通知的标题
                        .setContentTitle("This is content title")
                        //通知的内容
                        .setContentText("This is content text")
                        //通知被创建的时间
                        .setWhen(System.currentTimeMillis())
                        //通知的小图标 主要只能使用纯alpha图层的图片进行设置 ?? 不懂啥意思 0-0
                        .setSmallIcon(R.mipmap.ic_launcher)
                        //通知的的大图标，拉下系统状态栏时，就可以看到设置的大图标了
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                        //设置PendingIntent
//                        .setContentIntent(pi);
                        //设置通知声音
//                        .setSound();
                        //设置震动 要申请权限  安全权限
//                        .setVibrate();
                        //设置LED灯闪烁
//                        .setLights();
                        //设置通知的默认效果
//                        .setDefaults(NotificationCompat.DEFAULT_ALL);
                        //构建具体的富文本，如长文字、图片等
//                        .setStyle();
                        //设置通知的重要程度  NotificationCompat.PRIORITY_MIN ---- NotificationCompat.PRIORITY_MAX
//                        .setPriority()
                        .build();
                //notify()接收两个参数，一个是id，要保证为每个通知所指定的id都是不同的。第二个参数是Notification对象，
                Log.d("NotificationActivity", "commonNotification == null:" + (commonNotification == null));
                mManager.notify(0, commonNotification);
                break;
            case R.id.notify_button_02:
                if (ContextCompat.checkSelfPermission(NotificationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(NotificationActivity.this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == CHOOSE_PHOTO) {
            //判断手机系统版本号
            if (Build.VERSION.SDK_INT >= 19) {
                handleImageOnKitKat(data);
            } else {
                handleImageBeforeKitKat(data);
            }
        }
    }

    /**
     * 超麻烦的。 大概因为用的都是自定义的相册所以不知道这有这么麻烦 - -  感觉应该是google官方告诉的获取方式
     *
     * Android系统从4.4版本开始，选取相册中的图片不再返回图片真实的Uri了。而是一个封装过得Uri
     * 因此如果是4，4版本以上的手机就需要对这个Uri进行解析才行。
     *
     * @param data
     */
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是document类型的uri，则通过document id来处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];  // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        selection);

            } else if ("com.android.providers.downloads.document".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://" +
                        "downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                //如果是content类型的Uri，则使用普通方式处理
                imagePath = getImagePath(uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                //如果是file类型的Uri，则使用普通方式处理
                imagePath = uri.getPath();
            }
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri,  String selection) {
        String path = null;
        //通过Uri和selecton来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            mImageView.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }
}
