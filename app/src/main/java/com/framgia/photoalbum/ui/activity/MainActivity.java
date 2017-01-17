package com.framgia.photoalbum.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.framgia.photoalbum.R;
import com.framgia.photoalbum.data.model.ConstantManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.framgia.photoalbum.data.model.ConstantManager.REQUEST_TAKE_IMAGE;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button_image_sd_card)
    Button mGetImageSDCardButton;
    @BindView(R.id.button_image_capture)
    Button mGetImageCaptureButton;
    private Bitmap mImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.title_choice_a_image);
        ButterKnife.bind(this);
    }

    public void getSDCardImages() {
        Intent sdCardIntent = new Intent(MainActivity.this, ImagesActivity.class);
        startActivity(sdCardIntent);
    }

    public void captureImage() {
        Intent capImg = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (capImg.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(capImg, ConstantManager.REQUEST_TAKE_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_IMAGE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            mImageBitmap = (Bitmap) extras.get(ConstantManager.ARGUMENT_GET_IMAGE_BITMAP);
            moveToEditImageFunctionsActivity(mImageBitmap);
        }
    }

    @OnClick({R.id.button_image_sd_card, R.id.button_image_capture})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_image_sd_card:
                getSDCardImages();
                break;
            case R.id.button_image_capture:
                captureImage();
                break;
            default:
                break;
        }
    }

    private void moveToEditImageFunctionsActivity(Bitmap imageBitmap) {
        Intent i = new Intent(getApplicationContext(), EditImageFunctionsActivity.class);
        i.putExtra(ConstantManager.ARGUMENT_PUT_IMAGE_INTENT, imageBitmap);
        startActivity(i);
    }
}
