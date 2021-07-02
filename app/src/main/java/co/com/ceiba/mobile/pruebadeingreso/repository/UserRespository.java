package co.com.ceiba.mobile.pruebadeingreso.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.dao.PostsDao;
import co.com.ceiba.mobile.pruebadeingreso.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.database.UserDatabase;
import co.com.ceiba.mobile.pruebadeingreso.modal.Posts;
import co.com.ceiba.mobile.pruebadeingreso.modal.Users;

public class UserRespository {

    private final UserDatabase database;
    private final LiveData<List<Users>> getAllUsers;
    private final List<Users> getListUsers;
    private final LiveData<List<Posts>> getAllPosts;


    public UserRespository(Application application)
    {
        database    = UserDatabase.getInstance(application);
        getAllUsers = database.userDao().getAllUsers();
        getListUsers = database.userDao().getListUsers();
        getAllPosts = database.postsDao().getAllPosts();

    }

    public void insertUser  (List<Users> userList){ new InsertAsynTaskA(database).execute(userList); }

    public void insertPosts (List<Posts> postsList){ new InsertAsynTaskB(database).execute(postsList); }

    public LiveData<List<Users>> getAllUsers()
    {
        return getAllUsers;
    }

    public List<Users> getListUsers() { return getListUsers; }


    public LiveData<List<Posts>> getGetAllPosts()
    {
        return getAllPosts;
    }


    public LiveData<List<Posts>> getPosts(String id) {
        return database.postsDao().getPosts(id);
    }


   static class InsertAsynTaskA extends AsyncTask<List<Users>, Void,Void> {
        private final UserDao userDao;

        InsertAsynTaskA(UserDatabase userDatabase) { userDao  = userDatabase.userDao(); }
        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Users>... lists) {
             userDao.insertUser(lists[0]);
            return null;
        }
    }

    static class InsertAsynTaskB extends AsyncTask<List<Posts>, Void,Void> {
        private final PostsDao postsDao;

        InsertAsynTaskB(UserDatabase userDatabase) { postsDao  = userDatabase.postsDao(); }
        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Posts>... lists) {
            postsDao.insertPost(lists[0]);
            return null;
        }
    }
}
