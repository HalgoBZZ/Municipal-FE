package com.halgo.municipalpfe.api;

import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Offre;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface ApiOffre {
    @GET("offre/all")
    Call<List<Offre>> getAllOffres();

    @POST("/offre/create")
    Call <ResponseBody> saveOffre(@Body Offre client);

    @PUT("/offre/update")
    Call <ResponseBody> updateOffre(@Body Offre client);

    @DELETE("offre/delete/{id}")
    Call<Void> deleteOffre(@Path("id") Long id);

}
