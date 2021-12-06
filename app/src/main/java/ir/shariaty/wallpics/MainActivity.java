package ir.shariaty.wallpics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModel> modelClassList;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView mcar , mrain , mtrend , mmusic;
    EditText editText;
    ImageButton search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        mcar = findViewById(R.id.car);
        mrain = findViewById(R.id.rain);
        mmusic = findViewById(R.id.music);
        mtrend = findViewById(R.id.trend);
        editText = findViewById(R.id.edittext);
        search = findViewById(R.id.search);

        modelClassList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(getApplicationContext() , modelClassList);
        recyclerView.setAdapter(adapter);
        findphotos();

        mcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "car";
                getsearchimage(query);
            }
        });

        mrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "rain";
                getsearchimage(query);
            }
        });

        mmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "music";
                getsearchimage(query);
            }
        });

        mtrend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findphotos();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editText.getText().toString().trim().toLowerCase();
                if (query.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter a Subject", Toast.LENGTH_SHORT).show();
                }
                else {
                    getsearchimage(query);
                }
            }
        });

    }

    private void getsearchimage(String query) {

        ApiUtilities.getApiInterface().getsearchImage(query , 1 , 50).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClassList.clear();
                if (response.isSuccessful())
                {
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getApplicationContext(), "We Have Problem! 🙄", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });

    }

    private void findphotos() {

        modelClassList.clear();
        ApiUtilities.getApiInterface().getImage(1,50).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClassList.clear();
                if (response.isSuccessful())
                {
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getApplicationContext(), "We Have Problem! 🙄", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }
}