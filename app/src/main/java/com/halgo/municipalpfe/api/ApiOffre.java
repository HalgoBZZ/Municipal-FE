package com.halgo.municipalpfe.api;

import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Offre;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiOffre {
    @GET("offre/all")
    Call<List<Offre>> getAllOffres();

    @POST("/offre/create")
    Call <ResponseBody> saveOffre(@Body Offre client);

}
