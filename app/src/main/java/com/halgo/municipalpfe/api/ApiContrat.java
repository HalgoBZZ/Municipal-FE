package com.halgo.municipalpfe.api;

import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Contrat;
import com.halgo.municipalpfe.modals.Payement;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiContrat {

    @GET("contrat/all")
    Call<List<Contrat>> getAllContrats();

    @POST("/contrat/create")
    Call <ResponseBody> saveContrat(@Body Contrat contrat);
}
