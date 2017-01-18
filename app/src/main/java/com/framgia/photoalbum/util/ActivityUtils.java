package com.framgia.photoalbum.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.framgia.photoalbum.data.model.ConstantManager;

/**
 * Created by SANG-TRAN on 17/01/2017.
 */
public class ActivityUtils {
    public static void startEditActivity(Context context, Class<?> cls, Bitmap imageBitmap) {
        Intent i = new Intent(context, cls);
        i.putExtra(ConstantManager.ARGUMENT_PUT_IMAGE_INTENT, imageBitmap);
        context.startActivity(i);
    }

    public static Bitmap getBitmapFromActivity(Activity activity) {
        Bitmap bitmap;
        if (activity.getIntent() == null) return null;
        bitmap = (Bitmap) activity.getIntent()
            .getParcelableExtra(ConstantManager.ARGUMENT_PUT_IMAGE_INTENT);
        if (bitmap == null) return null;
        return bitmap;
    }
}
