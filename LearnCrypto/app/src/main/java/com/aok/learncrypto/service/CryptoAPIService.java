package com.aok.learncrypto.service;

import com.aok.learncrypto.model.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPIService {

    @GET("/atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    Call<List<CryptoModel>> getData();


    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
}
