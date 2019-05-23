package com.halgo.municipalpfe.api;

import com.halgo.municipalpfe.modals.Offre;
import com.halgo.municipalpfe.modals.Payement;
import com.halgo.municipalpfe.modals.Propriete;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiPayement {
    @GET("payement/all")
    Call<List<Payement>> getAllPayements();

    @POST("/payement/create")
    Call <ResponseBody> savePayement(@Body Payement payement);
}
