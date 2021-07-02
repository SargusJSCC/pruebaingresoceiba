package co.com.ceiba.mobile.pruebadeingreso.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.adapter.PostAdapter;
import co.com.ceiba.mobile.pruebadeingreso.modal.Posts;
import co.com.ceiba.mobile.pruebadeingreso.network.Retrofit;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRespository;
import co.com.ceiba.mobile.pruebadeingreso.userViewModal.PostViewModal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserRespository userRespository;
    private PostAdapter postAdapter;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent = getIntent();
        String id     = intent.getStringExtra("Id");
        String name   = intent.getStringExtra("name");
        String phone  = intent.getStringExtra("phone");
        String email  = intent.getStringExtra("email");

        TextView tv_name = findViewById(R.id.name);
        TextView tv_phone = findViewById(R.id.phone);
        TextView tv_email = findViewById(R.id.email);
        recyclerView = findViewById(R.id.recyclerViewPostsResults);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        userRespository = new UserRespository(getApplication());
        List<Posts> postsList = new ArrayList<>();

        postAdapter=new PostAdapter(this, postsList);

        PostViewModal postViewModal = new ViewModelProvider(this).get(PostViewModal.class);

        loading();

        tv_name.setText(name);
        tv_phone.setText(phone);
        tv_email.setText(email);

        networkRequest(id);

        postViewModal.getPosts(id).observe(this, postsList1 -> {
            if(postsList1 !=null) {
                recyclerView.setAdapter(postAdapter);
                postAdapter.getAllPosts(postsList1);
            }
        });

    }

    private void loading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(R.layout.loading_dialog);
        dialog = builder.create();
        dialog.show();
    }

    private void networkRequest(String id) {

        Retrofit retrofit=new Retrofit();
        Call<List<Posts>> call=retrofit.api.getAllPosts(id);
        call.enqueue(new Callback<List<Posts>>() {

            @Override
            public void onResponse(@NonNull Call<List<Posts>> call, @NonNull Response<List<Posts>> response) {
                if(response.isSuccessful()) {
                    userRespository.insertPosts(response.body());
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Posts>> call, @NonNull Throwable t) {
                Toast.makeText(PostActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

}