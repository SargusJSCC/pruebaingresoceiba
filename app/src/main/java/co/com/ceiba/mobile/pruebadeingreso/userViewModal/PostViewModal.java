package co.com.ceiba.mobile.pruebadeingreso.userViewModal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.modal.Posts;
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRespository;


public class PostViewModal extends AndroidViewModel {

    private final UserRespository userRespository;

    public PostViewModal(@NonNull Application application) {
        super(application);
        userRespository = new UserRespository(application);
    }

    public void insert(List<Posts> postsList)
    {
        userRespository.insertPosts(postsList);
    }

    public LiveData<List<Posts>> getPosts(String id)
    {
        return userRespository.getPosts(id);
    }

}
