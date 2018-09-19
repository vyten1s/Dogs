package com.vsandr.dogs.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vsandr.dogs.R;
import com.vsandr.dogs.details.DogDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DogsAdapter extends RecyclerView.Adapter<DogsAdapter.ViewHolder> {

    private List mDogs;
    private Context mContext;

    public static final String DOG_POSITION = "position";


    public DogsAdapter(Context mContext, List mDogs) {
        this.mContext = mContext;
        this.mDogs = mDogs;
    }

    @NonNull
    @Override
    public DogsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item_dog, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DogsAdapter.ViewHolder viewHolder, final int position) {
        RequestOptions optionsWorkout = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher);

        Glide.with(mContext)
                .load(mDogs.get(position))
                .thumbnail(0.9f)
                .apply(optionsWorkout)
                .into(viewHolder.mDog);

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DogDetails.class);
                intent.putExtra(DOG_POSITION, mDogs.get(position).toString());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDogs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_dog)
        ImageView mDog;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mView = itemView;
        }
    }

}
