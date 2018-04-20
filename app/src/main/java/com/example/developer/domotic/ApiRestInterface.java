package com.example.developer.domotic;

import com.example.developer.domotic.models.ApiRest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Developer on 26/11/2017.
 */

public interface ApiRestInterface {
    public static final String BASE_URL = "http://192.168.27.119/";
    @GET ("api")
    Call<List<ApiRest>> getData();

}