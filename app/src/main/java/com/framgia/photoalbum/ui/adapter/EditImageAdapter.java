package com.framgia.photoalbum.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.photoalbum.R;
import com.framgia.photoalbum.data.model.EditItem;

import java.util.ArrayList;

/**
 * Created by SANG-TRAN on 16/01/2017.
 */
public class EditImageAdapter extends RecyclerView.Adapter<EditImageAdapter.EditImageHolder> {
    private Context mContext;
    private ArrayList<EditItem> mListItem;
    private LayoutInflater mInflater;
    private ClickEvent mListener;

    public EditImageAdapter(Context context, ArrayList<EditItem> listItem, ClickEvent listener) {
        mContext = context;
        mListItem = listItem;
        mInflater = LayoutInflater.from(context);
        mListener = listener;
    }

    @Override
    public EditImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_edit_image_function, parent, false);
        return new EditImageHolder(view);
    }

    @Override
    public void onBindViewHolder(EditImageHolder holder, int position) {
        holder.bindData(mListItem.get(position));
    }

    @Override
    public int getItemCount() {
        return mListItem == null ? 0 : mListItem.size();
    }

    public interface ClickEvent{
        void onItemClicked(EditItem item);
    }

    public class EditImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageItem;
        private TextView mTextTitle;

        public EditImageHolder(View itemView) {
            super(itemView);
            mImageItem = (ImageView) itemView.findViewById(R.id.image_function_item);
            mTextTitle = (TextView) itemView.findViewById(R.id.text_function_item);
            itemView.setOnClickListener(this);
        }

        public void bindData(EditItem item) {
            mImageItem.setImageResource(item.getImageItem());
            mTextTitle.setText(item.getTitleItem());
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClicked(mListItem.get(getAdapterPosition()));
        }
    }
}
