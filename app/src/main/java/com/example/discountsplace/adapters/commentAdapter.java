package com.example.discountsplace.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discountsplace.R;
import com.example.discountsplace.models.Comment;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class commentAdapter extends RecyclerView.Adapter<commentAdapter.commentviewHolder> {
    Activity activity;
    ArrayList<Comment> data;

    public commentAdapter(Activity activity, ArrayList<Comment> data) {
        this.activity = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public commentviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_comment, parent, false);

        return new commentAdapter.commentviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull commentviewHolder holder, int position) {
        Comment comment = data.get(position);
        holder.username.setText(comment.getUsername().toString());
        holder.content.setText(comment.getContent().toString());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class commentviewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView content;
        public commentviewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.comment_username);
            content = itemView.findViewById(R.id.comment_content);
        }
    }
}
