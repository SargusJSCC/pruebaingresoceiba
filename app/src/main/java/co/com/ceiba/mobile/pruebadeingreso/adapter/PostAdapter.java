package co.com.ceiba.mobile.pruebadeingreso.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.modal.Posts;
import co.com.ceiba.mobile.pruebadeingreso.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ActorViewHolder> {

    private Context context;
    private List<Posts> postsList;

    public PostAdapter(Context context, List<Posts> postsList) {
        this.context =  context;
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ActorViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.post_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
      Posts posts = postsList.get(position);
      holder.title.setText(posts.getTitle());
      holder.body.setText(posts.getBody());
    }

    public void getAllPosts(List<Posts> postsList)
    {
        this.postsList = postsList;
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public static class ActorViewHolder extends RecyclerView.ViewHolder{
      public TextView title,body;

        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            body=itemView.findViewById(R.id.body);

        }
    }
}
