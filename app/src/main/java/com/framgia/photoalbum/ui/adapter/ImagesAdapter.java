package com.framgia.photoalbum.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.framgia.photoalbum.R;
import com.framgia.photoalbum.data.model.ImageInfo;
import com.framgia.photoalbum.ui.interactor.OnSelectedListener;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by trungnguyens93gmail.com on 1/13/17.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageViewHolder> {
    private Context mContext;
    private List<ImageInfo> mImageInfos;
    private LayoutInflater mLayoutInflater;
    private OnSelectedListener mOnSelectedListener;

    public ImagesAdapter(Context context, List<ImageInfo> imageInfos, OnSelectedListener
        onSelectedListener) {
        mContext = context;
        mImageInfos = imageInfos;
        mLayoutInflater = LayoutInflater.from(context);
        mOnSelectedListener = onSelectedListener;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.card_view_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        ImageInfo file = mImageInfos.get(position);
        holder.bindData(file);
    }

    @Override
    public int getItemCount() {
        return mImageInfos == null ? 0 : mImageInfos.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_avatar)
        ImageView mAvatarImage;
        @BindView(R.id.text_name_image)
        TextView mNameImage;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(ImageInfo imageInfo) {
            if (imageInfo == null) return;
            mAvatarImage.setImageURI(Uri.parse(imageInfo.getPath()));
            mNameImage.setText(imageInfo.getName());
        }

        @OnClick(R.id.image_avatar)
        public void getImage(View v) {
            int pos = getAdapterPosition();
            if (mOnSelectedListener != null) mOnSelectedListener.onImageSelect(mImageInfos.get(pos));
        }
    }
}
