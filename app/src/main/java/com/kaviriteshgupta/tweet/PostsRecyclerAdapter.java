package com.kaviriteshgupta.tweet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostsRecyclerAdapter extends FirestoreRecyclerAdapter<PostModel, PostsRecyclerAdapter.ViewHolder> {

    public PostsRecyclerAdapter(@NonNull FirestoreRecyclerOptions<PostModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull PostModel model) {
        holder.body.setText(model.getBody());
        holder.name.setText(model.getName());
        loadProfileImage(holder.circleImageView,model);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.main_item_layout,parent,false);
        return new ViewHolder(view);
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView circleImageView;
        TextView name, body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.circleImageViewProfile);
            name = itemView.findViewById(R.id.textView);
            body = itemView.findViewById(R.id.textView3);
        }
    }

    private void loadProfileImage(CircleImageView circleImageView, PostModel model){
        Glide.with(circleImageView.getContext())
                .load(model.getImageUrl())
                .into(circleImageView);
    }
}

