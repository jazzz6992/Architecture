package com.vsevolodvisnevskij.presentation.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ImageChooser {
    private static final int REQUEST_PHOTO = 0;
    private static final int GALERY_REQUEST_CODE = 1;

    public static void startCamera(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            File photo = getCameraFile(activity);
            Uri uri = FileProvider.getUriForFile(activity, "com.vsevolodvisnevskij.presentation.utils.MyFileProvider", photo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            activity.startActivityForResult(intent, REQUEST_PHOTO);
        }
    }

    public static void startGalery(Activity activity) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean granted) {
                if (granted) {
                    chooseGalery(activity);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    private static void chooseGalery(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(intent, GALERY_REQUEST_CODE);
        }
    }

    public static File getCameraFile(Activity activity) {
        File dir = activity.getExternalFilesDir(null);
        if (dir == null) {
            dir = activity.getFilesDir();
        }
        File myDir = new File(dir.getAbsoluteFile() + "/myDir");
        if (!myDir.exists()) {
            myDir.mkdir();
        }
        return new File(myDir.getAbsoluteFile() + "/" + System.currentTimeMillis() + ".jpg");
    }

    public static File getImageFromResult(Activity activity, int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_PHOTO:
                File file = getCameraFile(activity);
                if (file.exists()) {
                    return file;
                } else {
                    return null;
                }
            case GALERY_REQUEST_CODE:
                Uri uri = data.getData();
                Cursor cursor = activity.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    int idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    String f = cursor.getString(idx);
                    cursor.close();
                    return new File(f);
                } else {
                    return null;
                }
        }
        return null;
    }
}
