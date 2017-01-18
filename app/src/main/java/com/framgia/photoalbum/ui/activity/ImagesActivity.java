package com.framgia.photoalbum.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.framgia.photoalbum.R;
import com.framgia.photoalbum.data.local.SDCardReader;
import com.framgia.photoalbum.data.model.ConstantManager;
import com.framgia.photoalbum.data.model.ImageInfo;
import com.framgia.photoalbum.ui.adapter.ImagesAdapter;
import com.framgia.photoalbum.ui.interactor.OnSelectedListener;

import java.io.ByteArrayOutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by trungnguyens93gmail.com on 1/13/17.
 */
public class ImagesActivity extends AppCompatActivity implements OnSelectedListener {
    private static final int NUM_COLUMN_OF_ROW_RECYCLE_VIEW = 2;
    @BindView(R.id.recycle_images)
    RecyclerView mImagesRecyclerView;
    private List<ImageInfo> mImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        ButterKnife.bind(this);
        // Setting ActionBar
        setTitle(R.string.title_choice_a_image);
        setActionBar();
        mImagesRecyclerView.setHasFixedSize(true);
        mImagesRecyclerView.setLayoutManager(
            new GridLayoutManager(ImagesActivity.this, NUM_COLUMN_OF_ROW_RECYCLE_VIEW));
        mImages = SDCardReader.getAllImageFile(this);
        mImagesRecyclerView.setAdapter(new ImagesAdapter(getApplicationContext(), mImages,
            this));
    }

    public void setActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onImageSelect(ImageInfo imageInfo) {
        Bitmap bitmapImage = BitmapFactory.decodeFile(imageInfo.getPath());
        if (bitmapImage != null) {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            bitmapImage.compress(Bitmap.CompressFormat.PNG, ConstantManager.QUALYTY_IMAGE, bs);
            byte[] bytes = bs.toByteArray();
            Intent i = new Intent(ImagesActivity.this, EditImageFunctionsActivity.class);
            i.putExtra(ConstantManager.ARGUMENT_PUT_IMAGE_INTENT, bytes);
            startActivity(i);
        } else {
            Toast.makeText(this, getString(R.string.error_get_image_sd_card), Toast.LENGTH_SHORT)
                .show();
        }
    }
}
