package com.example.jaebong.multishotcamera.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jaebong.multishotcamera.R;
import com.example.jaebong.multishotcamera.model.FilePath;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by gingeraebi on 2016. 8. 15..
 */
public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {

    private RealmResults<FilePath> filePaths;
    private Context context;

    public ImageListAdapter(Context context, RealmResults<FilePath> filePaths) {
        this.context = context;
        this.filePaths = filePaths;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnailImage)
        ImageView thumbnailImage;

        @BindView(R.id.delete)
        ImageView deleteImage;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, final int position) {
        Picasso.with(context).load(new File(filePaths.get(position).filePath)).into(holder.thumbnailImage);
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(filePaths.get(position).filePath);
                file.delete();
                filePaths.remove(filePaths.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return (filePaths != null ? filePaths.size() : 0);
    }


}
