package com.vsandr.dogs.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private int mColumnNumber;

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

        presenter.countColumns();

        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,
                mColumnNumber));
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

    @Override
    public void columnNumber(int number) {
        mColumnNumber = number;
    }

}
