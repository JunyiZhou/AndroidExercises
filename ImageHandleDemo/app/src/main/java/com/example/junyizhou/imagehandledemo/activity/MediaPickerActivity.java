package com.example.junyizhou.imagehandledemo.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.junyizhou.imagehandledemo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by JunyiZhou on 2016/2/3.
 */
public class MediaPickerActivity extends Activity implements DialogInterface.OnCancelListener {

    public static void saveImageToGallery(Context context, String fileName) {
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    fileName, fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(fileName)));
    }

    public static void CreateNewPhoto(String filePath, View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap); // 新建画布
        canvas.drawColor(Color.WHITE);
        view.draw(canvas);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fileOutputStream);
            fileOutputStream.close();
            fileOutputStream = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        bitmap.recycle();
        bitmap = null;
    }

    public static String getTempFilePath(Context context) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        File cacheDir = context.getExternalCacheDir();

        if (cacheDir == null) {
            return null;
        }

        if (!cacheDir.exists()) {
            if (cacheDir.mkdirs()) {

            }
        }
        return cacheDir.getAbsolutePath() + File.separator + sdf.format(date) + ".png";
    }

    public static final String MEDIA_URI = "MEDIA_URI";

    public static final String MEDIA_TYPE = "MEDIA_TYPE";
    public static final String MEDIA_IMAGE = "image/*";
    public static final String MEDIA_VIDEO = "video/*";

    public static final Uri URI_IMAGE = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    public static final Uri URI_VIDEO = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

    public static final int REQUEST_MEDIA_PICKER_ACTIVITY = 10;

    private final int REQUEST_CODE_FROM_GALLERY = 1;
    private final int REQUEST_CODE_FROM_CAMERA = 2;

    private String mTempFilePath;
    private AlertDialog mAlertDialog;

    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_picker);

        type = getIntent().getStringExtra(MEDIA_TYPE);

        if (savedInstanceState != null) {
            mTempFilePath = savedInstanceState.getString("mTempFilePath");
        } else {
            mTempFilePath = getTempFilePath(this);
            if (mTempFilePath == null) {
                Toast toast = Toast.makeText(MediaPickerActivity.this, "存储空间不足，请清理后重试…", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                finish();
            }
        }

        switch (type) {
            case MEDIA_IMAGE:
                mAlertDialog = new AlertDialog.Builder(this)
                        .setOnCancelListener(this)
                        .setTitle(R.string.dialog_title_choose_image_source)
                        .setItems(R.array.dialog_item_choose_image_source, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mTempFilePath)));

                                    if (checkIntent(MediaPickerActivity.this, intent)) {
                                        startActivityForResult(intent, REQUEST_CODE_FROM_CAMERA);
                                    } else {
                                        Toast toast = Toast.makeText(MediaPickerActivity.this, "未检测到可用的相机程序", Toast.LENGTH_SHORT);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();
                                        finish();
                                    }

                                } else {
                                    dialog.dismiss();
                                    Intent intent = getAvailableIntent(URI_IMAGE, MEDIA_IMAGE);
                                    if (intent != null) {
                                        startActivityForResult(intent, REQUEST_CODE_FROM_GALLERY);
                                    } else {
                                        Toast toast = Toast.makeText(MediaPickerActivity.this, "未检测到可用的媒体库程序", Toast.LENGTH_SHORT);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();
                                        finish();
                                    }
                                }
                            }
                        }).show();
                break;

            case MEDIA_VIDEO:
                Intent intent = getAvailableIntent(URI_VIDEO, MEDIA_VIDEO);
                if (intent != null) {
                    startActivityForResult(intent, REQUEST_CODE_FROM_GALLERY);
                } else {
                    Toast toast = Toast.makeText(MediaPickerActivity.this, "未检测到可用的媒体库程序", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    finish();
                }
                break;

            default:
                Toast toast = Toast.makeText(MediaPickerActivity.this, "当前仅支持图片和视频两种媒体类型", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                finish();
                break;
        }

    }

    public Intent getAvailableIntent(Uri data, String type) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Intent documentIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            documentIntent.setDataAndType(data, type);
            documentIntent.addCategory(Intent.CATEGORY_OPENABLE);
            if (checkIntent(this, documentIntent)) {
                return documentIntent;
            }
        } else {
            Intent contentIntent = new Intent(Intent.ACTION_GET_CONTENT);
            contentIntent.setDataAndType(data, type);
            contentIntent.addCategory(Intent.CATEGORY_OPENABLE);
            if (checkIntent(this, contentIntent)) {
                return contentIntent;
            }
        }

        return null;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAlertDialog != null) {
            mAlertDialog.dismiss();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("mTempFilePath", mTempFilePath);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String mediaURI = null;
            switch (requestCode) {
                case REQUEST_CODE_FROM_CAMERA:
                    mediaURI = Uri.fromFile(new File(mTempFilePath)).toString();
                    break;
                case REQUEST_CODE_FROM_GALLERY:
                    mediaURI = data.getData().toString();
                    break;
            }

            Intent intent = new Intent();
            intent.putExtra(MEDIA_URI, mediaURI);
            MediaPickerActivity.this.setResult(RESULT_OK, intent);
            MediaPickerActivity.this.finish();
        } else {
            finish();
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public static boolean checkIntent(Context context, Intent intent) {
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}
