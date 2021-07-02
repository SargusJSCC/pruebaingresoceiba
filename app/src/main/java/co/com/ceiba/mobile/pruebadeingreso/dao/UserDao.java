package co.com.ceiba.mobile.pruebadeingreso.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.modal.Users;


@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(List<Users> userList);

    @Transaction
    @Query("SELECT * FROM user")
    LiveData<List<Users>> getAllUsers();

    @Transaction
    @Query("SELECT * FROM user")
    List<Users> getListUsers();


}
