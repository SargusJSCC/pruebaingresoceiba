package co.com.ceiba.mobile.pruebadeingreso.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;

import co.com.ceiba.mobile.pruebadeingreso.dao.PostsDao;
import co.com.ceiba.mobile.pruebadeingreso.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.modal.Posts;
import co.com.ceiba.mobile.pruebadeingreso.modal.Users;


@Database(entities = {Users.class, Posts.class},
          version = 1,
        exportSchema = false)

public abstract class UserDatabase extends RoomDatabase {

    private static final String DATABASE_NAME="UserDatabase";

    private static volatile UserDatabase INSTANCE;

    private static final Object sLock = new Object();

    public abstract UserDao userDao();

    public abstract PostsDao postsDao();


    public static UserDatabase getInstance(Context context){
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = buildDatabase(context);
            }
            return INSTANCE;
        }
    }

    private static UserDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                UserDatabase.class,
                DATABASE_NAME).allowMainThreadQueries().build();
    }

}
