package co.com.ceiba.mobile.pruebadeingreso;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import co.com.ceiba.mobile.pruebadeingreso.base.BaseApplication;
import co.com.ceiba.mobile.pruebadeingreso.dao.PostsDao;
import co.com.ceiba.mobile.pruebadeingreso.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.database.UserDatabase;
import co.com.ceiba.mobile.pruebadeingreso.modal.Posts;
import co.com.ceiba.mobile.pruebadeingreso.modal.Users;
import co.com.ceiba.mobile.pruebadeingreso.network.Retrofit;
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRespository;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertTrue;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@Config(sdk = 28)
@RunWith(RobolectricTestRunner.class)
public class ExampleUnitTest {

    private UserDao userDao;
    private PostsDao postsDao;
    private UserDatabase db;
    private UserRespository userRespository;
    private Context context = ApplicationProvider.getApplicationContext();


    @Before
    public void  onCreateDB() {
        db = Room.inMemoryDatabaseBuilder(context, UserDatabase.class).build();
        userDao = db.userDao();
        postsDao = db.postsDao();
        userRespository =  new UserRespository(BaseApplication.getInstance());
    }

    @After
    public void  closeDB() {
        db.close();
    }

    @Test
    public void insertAll() {
        insertUser();
        insertPost();
    }

    @Test
    public void insertUser() {

        AsyncTask.execute(() -> {
        List<Users> users = new ArrayList<>();
        Users users1  = new Users(1,"john","john@gmail.com", "1123456789");
        Users users2  = new Users(2,"john1","john1@gmail.com", "2123456789");
        Users users3  = new Users(3,"john2","john2@gmail.com", "3123456789");
        users.add(users1);
        users.add(users2);
        users.add(users3);
        userDao.insertUser(users);});
    }


    @Test
    public void insertPost() {

        AsyncTask.execute(() -> {
        List<Posts> posts = new ArrayList<>();
        Posts posts1  = new Posts(1,1,"titulo1", "body1");
        Posts posts2  = new Posts(1,2,"titulo2", "body2");
        Posts posts3  = new Posts(1,3,"titulo3", "body3");
        posts.add(posts1);
        posts.add(posts2);
        posts.add(posts3);
        postsDao.insertPost(posts);});

    }

    @Test
    public void getAllUsers() {
        AsyncTask.execute(() -> {
        List<Users> users = userRespository.getListUsers();
        users.get(1).getName();
        users.get(1).getEmail();
        users.get(1).getPhone();});
    }


    @Test
    public void api_Success() {
        Retrofit retrofit=new Retrofit();
        Call<List<Users>> call=retrofit.api.getAllUsers();
        try {
            Response<List<Users>> response = call.execute();
            List<Users> Response = response.body();
            assertTrue(response.isSuccessful());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}