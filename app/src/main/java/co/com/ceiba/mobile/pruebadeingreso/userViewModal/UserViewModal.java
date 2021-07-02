package co.com.ceiba.mobile.pruebadeingreso.userViewModal;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.modal.Users;
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRespository;


public class UserViewModal extends AndroidViewModel {

    private final UserRespository userRespository;
    private final LiveData<List<Users>> getAllUsers;

    public UserViewModal(@NonNull Application application) {
        super(application);
        userRespository = new UserRespository(application);
        getAllUsers     = userRespository.getAllUsers();
    }

    public void insert(List<Users> list) { userRespository.insertUser(list); }

    public LiveData<List<Users>> getGetAllUsers()
    {
        return getAllUsers;
    }

}
