package com.framgia.photoalbum.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;

import com.framgia.photoalbum.R;
import com.framgia.photoalbum.data.model.ConstantManager;
import com.framgia.photoalbum.data.model.EditItem;
import com.framgia.photoalbum.ui.adapter.EditImageAdapter;
import com.framgia.photoalbum.util.ActivityUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditImageFunctionsActivity extends AppCompatActivity implements
    EditImageAdapter.ClickEvent {
    @BindView(R.id.image_edit)
    ImageView mImageEdit;
    @BindView(R.id.recycle_view_functions)
    RecyclerView mFunctionsRecycleView;
    private Bitmap mImageBitmap;
    private ArrayList<EditItem> mListFunctionEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image_functions);
        setTitle(R.string.activity_title_edit_image);
        ButterKnife.bind(this);
        initViews();
        getImageBitmapEdit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        setUpActionBar();
        mListFunctionEdit = createListItemEdit();
        EditImageAdapter adapter = new EditImageAdapter(getApplicationContext(),
            mListFunctionEdit, this);
        mFunctionsRecycleView.setAdapter(adapter);
        mFunctionsRecycleView.setLayoutManager(new LinearLayoutManager(
            getApplicationContext(),
            LinearLayoutManager.HORIZONTAL, false));
    }

    public void getImageBitmapEdit() {
        mImageBitmap = ActivityUtils.getBitmapFromActivity(this);
        mImageEdit.setImageBitmap(mImageBitmap);
    }

    public void setUpActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public ArrayList<EditItem> createListItemEdit() {
        ArrayList<EditItem> listItem = new ArrayList<>();
        listItem.add(new EditItem(
            ConstantManager.FunctionTitle.EDIT_CROP_IMAGE,
            R.drawable.ic_crop));
        listItem.add(new EditItem(
            ConstantManager.FunctionTitle.EDIT_LIGHT_IMAGE,
            R.drawable.ic_light));
        listItem.add(new EditItem(
            ConstantManager.FunctionTitle.EDIT_SCALE_IMAGE,
            R.drawable.ic_scale));
        listItem.add(new EditItem(
            ConstantManager.FunctionTitle.EDIT_COLOR_IMAGE,
            R.drawable.ic_color));
        listItem.add(new EditItem(
            ConstantManager.FunctionTitle.EDIT_COLOR_BLACK_WHITE_IMAGE,
            R.drawable.ic_color_b_w));
        listItem.add(new EditItem(
            ConstantManager.FunctionTitle.EDIT_CONTRAST_IMAGE,
            R.drawable.ic_contrast));
        listItem.add(new EditItem(
            ConstantManager.FunctionTitle.EDIT_HIGHLIGHT_IMAGE,
            R.drawable.ic_highlight));
        listItem.add(new EditItem(
            ConstantManager.FunctionTitle.EDIT_MERGE_IMAGE,
            R.drawable.ic_merge));
        listItem.add(new EditItem(
            ConstantManager.FunctionTitle.EDIT_COMBINE_IMAGE_AUDIO,
            R.drawable.ic_combine));
        listItem.add(new EditItem(
            ConstantManager.FunctionTitle.EDIT_ZOOM_IMAGE,
            R.drawable.ic_zoom));
        listItem.add(new EditItem(
            ConstantManager.FunctionTitle.EDIT_SLIDE_IMAGE,
            R.drawable.ic_slide));
        listItem.add(new EditItem(
            ConstantManager.FunctionTitle.SAVE_IMAGE,
            R.drawable.ic_save));
        return listItem;
    }

    @Override
    public void onItemClicked(EditItem item) {
        switch (item.getTitleItem()) {
            case ConstantManager.FunctionTitle.EDIT_CROP_IMAGE:
                //TODO Crop image
                break;
            case ConstantManager.FunctionTitle.EDIT_LIGHT_IMAGE:
                ActivityUtils.startEditActivity(this, EditLightImageActivity.class, mImageBitmap);
                break;
            case ConstantManager.FunctionTitle.EDIT_SCALE_IMAGE:
                //TODO edit scale image
                break;
            case ConstantManager.FunctionTitle.EDIT_COLOR_IMAGE:
                //TODO edit color image
                break;
            case ConstantManager.FunctionTitle.EDIT_COLOR_BLACK_WHITE_IMAGE:
                //TODO edit color b & w image
                break;
            case ConstantManager.FunctionTitle.EDIT_CONTRAST_IMAGE:
                //TODO edit contrast image
                break;
            case ConstantManager.FunctionTitle.EDIT_HIGHLIGHT_IMAGE:
                //TODO edit highlight image
                break;
            case ConstantManager.FunctionTitle.EDIT_MERGE_IMAGE:
                //TODO merge image
                break;
            case ConstantManager.FunctionTitle.EDIT_COMBINE_IMAGE_AUDIO:
                //TODO combine image and audito to make video
                break;
            case ConstantManager.FunctionTitle.EDIT_ZOOM_IMAGE:
                //TODO zoom image
                break;
            case ConstantManager.FunctionTitle.EDIT_SLIDE_IMAGE:
                //TODO slide image
                break;
            case ConstantManager.FunctionTitle.SAVE_IMAGE:
                //TODO save image
                break;
            default:
                //TODO go to home
                break;
        }
    }
}
