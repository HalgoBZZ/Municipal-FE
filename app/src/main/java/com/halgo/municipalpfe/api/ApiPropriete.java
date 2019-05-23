package com.halgo.municipalpfe.api;

import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Offre;
import com.halgo.municipalpfe.modals.Propriete;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiPropriete {

    @GET("propriete/all")
    Call<List<Propriete>> getAllProprietes();

    @POST("/propriete/create")
    Call <ResponseBody> savePropriete(@Body Propriete propriete);

    @PUT("/propriete/update")
    Call <ResponseBody> updatePropriete(@Body Propriete propriete);

    @DELETE("/propriete/delete/{id}")
    Call<Void> deletePropriete(@Path("id") Long id);
}
