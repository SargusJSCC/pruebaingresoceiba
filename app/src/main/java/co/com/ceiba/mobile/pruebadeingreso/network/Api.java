package co.com.ceiba.mobile.pruebadeingreso.network;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.modal.Posts;
import co.com.ceiba.mobile.pruebadeingreso.modal.Users;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET(Endpoints.GET_USERS)
    Call<List<Users>> getAllUsers();

    @GET(Endpoints.GET_POST_USER)
    Call<List<Posts>> getAllPosts(@Query("userId") String id);
}
