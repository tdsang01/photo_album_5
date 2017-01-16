package com.framgia.photoalbum.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.framgia.photoalbum.R;
import com.framgia.photoalbum.data.local.SDCardReader;
import com.framgia.photoalbum.data.model.ImageInfo;
import com.framgia.photoalbum.ui.adapter.ImagesAdapter;
import com.framgia.photoalbum.ui.interactor.OnSelectedListener;
import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by trungnguyens93gmail.com on 1/13/17.
 */
public class ImagesActivity extends AppCompatActivity implements OnSelectedListener {
    @BindView(R.id.recycle_images)
    RecyclerView mImagesRecyclerView;
    private List<ImageInfo> mImages;
    private static final int NUM_COLUMN_OF_ROW_RECYCLE_VIEW = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        ButterKnife.bind(this);
        // Setting ActionBar
        setTitle(R.string.title_choice_a_image);
        setActionBar();
        mImagesRecyclerView.setHasFixedSize(true);
        mImagesRecyclerView.setLayoutManager(new GridLayoutManager(ImagesActivity.this, NUM_COLUMN_OF_ROW_RECYCLE_VIEW));
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
        // TODO Go to Custom Image Activity
        // Waiting for the custom image activity
    }
}
