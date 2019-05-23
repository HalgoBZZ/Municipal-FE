package com.halgo.municipalpfe.api;

import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Contrat;
import com.halgo.municipalpfe.modals.Payement;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiContrat {

    @GET("contrat/all")
    Call<List<Contrat>> getAllContrats();

    @POST("/contrat/create")
    Call <ResponseBody> saveContrat(@Body Contrat contrat);

    @PUT("/contrat/update")
    Call <ResponseBody> updateContrat(@Body Contrat contrat);

    @DELETE("/contrat/delete/{id}")
    Call<Void> deleteContrat(@Path("id") Long id);
}
