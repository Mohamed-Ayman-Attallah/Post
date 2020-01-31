package com.example.post;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends ArrayAdapter<User> {

    Context context;
    int resource;

    public UserAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        TextView textView =convertView.findViewById(R.id.name);
        ImageView post_image = convertView.findViewById(R.id.post_image);
        CircleImageView circleImageView = convertView.findViewById(R.id.imagee);
        final ImageView love = convertView.findViewById(R.id.love);
        ImageView comment = convertView.findViewById(R.id.comment);
        ImageView star = convertView.findViewById(R.id.star);
        ImageView share = convertView.findViewById(R.id.share);


        final User currentUser = (User) getItem(position);

        textView.setText(currentUser.getName());
        post_image.setImageResource(currentUser.getPost_image());
        circleImageView.setImageResource(currentUser.getProfile());
        love.setImageResource( currentUser.isLove() ? R.drawable.favorite : R.drawable.ic_favorite_border_black_24dp);
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentUser.setLove( !currentUser.isLove());
                love.setImageResource( currentUser.isLove() ? R.drawable.favorite : R.drawable.ic_favorite_border_black_24dp);

            }
        });



        return convertView;
    }
}
