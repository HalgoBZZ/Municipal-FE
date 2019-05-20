package com.halgo.municipalpfe.api;

import com.halgo.municipalpfe.modals.Payement;
import com.halgo.municipalpfe.modals.Propriete;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiPayement {
    @GET("payement/all")
    Call<List<Payement>> getAllPayements();
}
