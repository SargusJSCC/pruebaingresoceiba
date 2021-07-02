package co.com.ceiba.mobile.pruebadeingreso.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.modal.Users;
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRespository;
import co.com.ceiba.mobile.pruebadeingreso.view.MainActivity;
import co.com.ceiba.mobile.pruebadeingreso.view.PostActivity;

public class UserAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Users> userList;

    public final int TYPE_EMPTY = 0;
    public final int TYPE_NORMAL = 1;


    @Override
    public int getItemViewType(int position) {
        if (userList.size() <= 0) {
            return TYPE_EMPTY;
        }
        return TYPE_NORMAL;
    }

    public UserAdapter(Context context, List<Users> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == TYPE_EMPTY) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        }
        return new ListHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListHolder) {
            Users users = userList.get(position);

            ((ListHolder) holder).name.setText(users.getName());
            ((ListHolder) holder).phone.setText(users.getPhone());
            ((ListHolder) holder).email.setText(users.getEmail());



            ((ListHolder) holder).btn_view_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, PostActivity.class);
                    intent.putExtra("Id",String.valueOf(position+1));
                    intent.putExtra("name",users.getName());
                    intent.putExtra("phone",users.getPhone());
                    intent.putExtra("email",users.getEmail());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (userList.size() <= 0) {
            return 1;
        }
        return userList.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name) public TextView name;
        @BindView(R.id.phone) public TextView phone;
        @BindView(R.id.email) public TextView email;
        @BindView(R.id.btn_view_post) public Button btn_view_post;

       public ListHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void getAllUsers(List<Users> userList) {
        this.userList=userList;
        notifyDataSetChanged();
    }

    public void filterList(List<Users> text) {
        this.userList = text;
        notifyDataSetChanged();
    }

}


