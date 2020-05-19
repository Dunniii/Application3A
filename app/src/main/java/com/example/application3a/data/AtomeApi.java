package com.example.application3a.data;

import com.example.application3a.presentation.model.RestAtomeResponse;

import retrofit2.http.GET;
import retrofit2.Call;

public interface AtomeApi {
    @GET("atomeapi.json")
    Call<RestAtomeResponse> getAtomeResponse();
}
