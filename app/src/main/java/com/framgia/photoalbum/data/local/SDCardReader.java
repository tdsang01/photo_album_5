package com.framgia.photoalbum.data.local;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.framgia.photoalbum.data.model.ImageInfo;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by trungnguyens93gmail.com on 1/13/17.
 */
public class SDCardReader {
    public static ArrayList<ImageInfo> getAllImageFile(Activity activity) {
        ArrayList<ImageInfo> list = new ArrayList<>();
        int columnData, columnName;
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.TITLE };
        Cursor cursor = activity.getContentResolver().query(uri, projection, null,
            null, null);
        if (cursor == null) return null;
        if (cursor.moveToFirst()) {
            columnData = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            columnName = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.TITLE);
            while (!cursor.isAfterLast()) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setPath(cursor.getString(columnData));
                imageInfo.setName(cursor.getString(columnName));
                list.add(imageInfo);
                cursor.moveToNext();
            }
        }
        return list;
    }
}
