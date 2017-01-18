package com.framgia.photoalbum.ui.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.framgia.photoalbum.R;
import com.framgia.photoalbum.data.model.ConstantManager;
import com.framgia.photoalbum.util.ActivityUtils;

public class EditLightImageActivity extends AppCompatActivity implements
    SeekBar.OnSeekBarChangeListener {
    private ImageView mImageEditLight;
    private SeekBar mSeekbarChangeLight;
    private Bitmap mImageBitmapSrc, mImageBitmapEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_light_image);
        setTitle(R.string.activity_title_edit_image_light);
        initViews();
    }

    public void initViews() {
        setUpActionBar();
        mImageEditLight = (ImageView) findViewById(R.id.image_edit_light);
        mSeekbarChangeLight = (SeekBar) findViewById(R.id.seekbar_light);
        mImageBitmapSrc = ActivityUtils.getBitmapFromActivity(this);
        mImageEditLight.setImageBitmap(mImageBitmapSrc);
        mSeekbarChangeLight.setOnSeekBarChangeListener(this);
        mSeekbarChangeLight.setMax(ConstantManager.SEEKBAR_SIZE_MAX);
        mSeekbarChangeLight.setProgress(ConstantManager.SEEKBAR_SIZE_MEDIUM);
    }

    public void setUpActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ActivityUtils.startEditActivity(
                    this,
                    EditImageFunctionsActivity.class,
                    mImageBitmapSrc);
                break;
            case R.id.menu_item_done:
                ActivityUtils.startEditActivity(
                    this,
                    EditImageFunctionsActivity.class,
                    mImageBitmapEdit);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int value = mSeekbarChangeLight.getProgress() - ConstantManager.SEEKBAR_SIZE_MEDIUM;
        new ChangeLightImage().execute(value);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public int configColorValue(int colorRGB, int value) {
        colorRGB += value;
        if (colorRGB > ConstantManager.COLOR_ARGB_MAX) {
            colorRGB = ConstantManager.COLOR_ARGB_MAX;
        } else if (colorRGB < ConstantManager.COLOR_ARGB_MIN) {
            colorRGB = ConstantManager.COLOR_ARGB_MIN;
        }
        return colorRGB;
    }

    public class ChangeLightImage extends AsyncTask<Integer, Integer, Bitmap> {
        @Override
        protected Bitmap doInBackground(Integer... params) {
            int imageSizeWidth = mImageBitmapSrc.getWidth();
            int imageSizeHeight = mImageBitmapSrc.getHeight();
            Bitmap output =
                Bitmap.createBitmap(imageSizeWidth, imageSizeHeight, mImageBitmapSrc.getConfig());
            int alpha, red, green, blue, pixel;
            for (int i = 0; i < imageSizeWidth; ++i) {
                for (int j = 0; j < imageSizeHeight; ++j) {
                    pixel = mImageBitmapSrc.getPixel(i, j);
                    alpha = Color.alpha(pixel);
                    red = Color.red(pixel);
                    green = Color.green(pixel);
                    blue = Color.blue(pixel);
                    red = configColorValue(red, params[0]);
                    green = configColorValue(green, params[0]);
                    blue = configColorValue(blue, params[0]);
                    output.setPixel(i, j, Color.argb(alpha, red, green, blue));
                }
            }
            return output;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mImageBitmapEdit = bitmap;
            mImageEditLight.setImageBitmap(mImageBitmapEdit);
        }
    }
}
