package co.com.ceiba.mobile.pruebadeingreso.network;

import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    retrofit2.Retrofit retrofit=new retrofit2.Retrofit.Builder()
            .baseUrl(Endpoints.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public Api api=retrofit.create(Api.class);
}
