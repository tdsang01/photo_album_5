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

import static com.framgia.photoalbum.data.model.ConstantManager.REQUEST_TAKE_IMAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mGetImageSDCardButton, mGetImageCaptureButton;
    private Bitmap mImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.title_choice_a_image);
        mGetImageSDCardButton = (Button) findViewById(R.id.button_image_sd_card);
        mGetImageCaptureButton = (Button) findViewById(R.id.button_image_capture);
        mGetImageSDCardButton.setOnClickListener(this);
        mGetImageCaptureButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_image_sd_card:
                // TODO Get image from SD Card
                break;
            case R.id.button_image_capture:
                captureImage();
                break;
            default:
                break;
        }
    }

    public void captureImage(){
        Intent capImg = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(capImg.resolveActivity(getPackageManager()) != null){
            startActivityForResult(capImg, ConstantManager.REQUEST_TAKE_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_TAKE_IMAGE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            mImageBitmap = (Bitmap) extras.get(ConstantManager.ARGUMENT_GET_IMAGE_BITMAP);
        }
    }
}
