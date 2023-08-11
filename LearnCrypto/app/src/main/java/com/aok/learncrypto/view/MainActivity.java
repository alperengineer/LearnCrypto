package com.aok.learncrypto.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aok.learncrypto.R;
import com.aok.learncrypto.adapter.RecyclerViewAdapter;
import com.aok.learncrypto.databinding.ActivityMainBinding;
import com.aok.learncrypto.model.CryptoModel;
import com.aok.learncrypto.service.CryptoAPIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL = "https://raw.githubusercontent.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        Gson gson = new GsonBuilder().setLenient().create();

        // Retrofit ve JSON

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();

    }

    private void loadData(){
        CryptoAPIService service = retrofit.create(CryptoAPIService.class);

        Call<List<CryptoModel>> call = service.getData();

        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if(response.isSuccessful()){
                    List<CryptoModel> responseList = response.body();
                    cryptoModels = new ArrayList<>(responseList);


                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(cryptoModels);
                    binding.recyclerView.setAdapter(recyclerViewAdapter);

                }else{
                    System.out.println("isSuccesful else bloğu");
                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}