package com.example.application3a;

import retrofit2.http.GET;
import retrofit2.Call;

public interface AtomeApi {
    @GET("atomeapi.json")
    Call<RestAtomeResponse> getAtomeResponse();
}
