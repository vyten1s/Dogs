package com.vsandr.dogs.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.vsandr.dogs.R;
import com.vsandr.dogs.adapter.DogsAdapter;
import com.vsandr.dogs.model.Dogs;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DogsContract.View {

    public static final String JSON_FILE = "dog_urls.json";
    public static final String TAG = "MainActivity";

    private DogsPresenter presenter;

    private DogsAdapter mAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter = new DogsPresenter(MainActivity.this, this);

        presenter.checkInternetConnection();

        presenter.getDogsData();
    }

    @Override
    public void showDogs(List<Dogs> dogs) {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, calculateNoOfColumns()));
        mAdapter = new DogsAdapter(MainActivity.this, dogs);
        mRecyclerView.scrollToPosition(dogs.size());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void connectivityStatus(boolean isActive) {
        if(!isActive){
            Toast.makeText(MainActivity.this, R.string.error_internet_connection,
                    Toast.LENGTH_SHORT).show();
        }
    }

    private int calculateNoOfColumns() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 180);
    }

}
