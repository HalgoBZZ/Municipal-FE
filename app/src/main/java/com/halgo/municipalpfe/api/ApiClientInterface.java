package com.halgo.municipalpfe.api;

import com.halgo.municipalpfe.modals.Client;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClientInterface {

    @GET("client/all")
    Call <List<Client>>getAllClients();

    @GET("client/authentication/{email}/{pwd}")
    Call <Client> authentication(@Path("email") String email, @Path("pwd") String pwd);

    @POST("/client/create")
    Call <ResponseBody> saveClient(@Body Client client);

    @PUT("/client/update")
    Call <ResponseBody> updateClient(@Body Client client);

    @DELETE("/client/delete/{id}")
    Call<Void> deleteClient(@Path("id") Long id);
}



