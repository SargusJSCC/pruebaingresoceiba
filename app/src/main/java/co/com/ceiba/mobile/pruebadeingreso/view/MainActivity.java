package co.com.ceiba.mobile.pruebadeingreso.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapter.UserAdapter;
import co.com.ceiba.mobile.pruebadeingreso.modal.Users;
import co.com.ceiba.mobile.pruebadeingreso.network.Retrofit;
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRespository;
import co.com.ceiba.mobile.pruebadeingreso.userViewModal.UserViewModal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private UserViewModal userViewModal;
    private RecyclerView recyclerView;
    private UserRespository userRespository;
    private UserAdapter userAdapter;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView et_search = findViewById(R.id.editTextSearch);
        recyclerView = findViewById(R.id.recyclerViewSearchResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        userRespository = new UserRespository(getApplication());
        List<Users> userList = new ArrayList<>();
        userAdapter   = new UserAdapter(this, userList);
        userViewModal = new ViewModelProvider(this).get(UserViewModal.class);

        if(userRespository.getListUsers().isEmpty()){
           loading();
           networkRequest(this);

        }

        userViewModal();

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

        private void filter(String text) {
            List<Users> users = userRespository.getListUsers();

            ArrayList<Users> filteredNames = new ArrayList<>();

            if(text.isEmpty()){
                userViewModal();
            }else{

                for (Users  s : users){

                    if (s.getName().contains(text)) {
                        filteredNames.add(s);
                    }
                }
               userAdapter.filterList(filteredNames);
            }
        }


    private void loading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(R.layout.loading_dialog);
        dialog = builder.create();
        dialog.show();
    }


    private void networkRequest(Context context) {

        Retrofit retrofit=new Retrofit();
        Call<List<Users>> call=retrofit.api.getAllUsers();
        call.enqueue(new Callback<List<Users>>() {

            @Override
            public void onResponse(@NonNull Call<List<Users>> call, @NonNull Response<List<Users>> response) {
                if(response.isSuccessful()) {
                    userRespository.insertUser(response.body());
                    userAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Users>> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + call.toString(),t);
                dialog.dismiss();
            }
        });
    }

    private void userViewModal() {

        userViewModal.getGetAllUsers().observe(this, users -> {
            if (users != null) {
                recyclerView.setAdapter(userAdapter);
                userAdapter.getAllUsers(users);
            }
        });
    }
}
