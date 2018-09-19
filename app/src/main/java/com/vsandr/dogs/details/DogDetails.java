package com.vsandr.dogs.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vsandr.dogs.R;
import com.vsandr.dogs.adapter.DogsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DogDetails extends AppCompatActivity implements DogDetailContract.View{

    private DogDetailPresenter presenter;

    @BindView(R.id.image_dog)
    ImageView mDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_details);

        ButterKnife.bind(this);

        presenter = new DogDetailPresenter(DogDetails.this, this);

        Intent intent = getIntent();
        String url = intent.getStringExtra(DogsAdapter.DOG_POSITION);

        presenter.loadDogImage(url);
    }

    @Override
    public void showDogs(String url) {
        RequestOptions optionsWorkout = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher);

        Glide.with(DogDetails.this)
                .load(url)
                .thumbnail(0.9f)
                .apply(optionsWorkout)
                .into(mDog);
    }

    @Override
    public void showError() {
        Toast.makeText(DogDetails.this, R.string.error_picture_loading,
                Toast.LENGTH_SHORT).show();
    }

}
