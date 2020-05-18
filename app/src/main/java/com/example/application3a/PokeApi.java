package com.example.application3a;

import retrofit2.http.GET;
import retrofit2.Call;

public interface PokeApi {
    @GET("/api/v2/pokemon")
    Call<RestPokemonResponse> getPokemonResponse();
}
