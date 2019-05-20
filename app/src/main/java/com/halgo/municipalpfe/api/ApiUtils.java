package com.halgo.municipalpfe.api;

public class ApiUtils {
    public static final String BASE_URL = "http://192.168.31.2:8081";
            //"http://10.0.2.2:8888";

    public static ApiClientInterface getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiClientInterface.class);
    }

    public static ApiOffre getOffreService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiOffre.class);
    }


    public static ApiAvertissement getAvertissementService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiAvertissement.class);
    }

    public static ApiNotification getNotifictionService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiNotification.class);
    }

    public static ApiContrat getContratService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiContrat.class);
    }

    public static ApiPayement getPayementService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiPayement.class);
    }

    public static ApiPropriete getProprieteService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiPropriete.class);
    }

}
