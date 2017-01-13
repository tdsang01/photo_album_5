package com.framgia.photoalbum.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.framgia.photoalbum.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mGetImageSDCardButton, mGetImageCaptureButton;

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
                // TODO Get image after capture a image
                break;
            default:
                break;
        }
    }
}
