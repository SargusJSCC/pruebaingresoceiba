package co.com.ceiba.mobile.pruebadeingreso.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.modal.Posts;


@Dao
public interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPost(List<Posts> postsList);

    @Query("SELECT * FROM posts")
    LiveData<List<Posts>> getAllPosts();

    @Query("SELECT * FROM posts WHERE userId IN (:ids)")
    LiveData<List<Posts>> getPosts(String ids);

}
