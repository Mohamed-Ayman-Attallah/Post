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

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

class commentAdabter extends ArrayAdapter<comment2> {

    public commentAdabter(@NonNull Context context, @NonNull List<comment2> objects) {
        super(context, 0 , objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment3 ,parent ,false);

        }

        CircleImageView profile_img = convertView.findViewById(R.id.comment_profile_image);
        TextView user_name = convertView.findViewById(R.id.comment_username) ,
                comment_text = convertView.findViewById(R.id.the_comment);
        final ImageView love = convertView.findViewById(R.id.comment_like);

        final comment2 current_comment = getItem(position);

        profile_img.setImageResource( current_comment.getCommentProfileImg());
        user_name.setText( current_comment.getCommentUsername());
        comment_text.setText( current_comment.getThecomment());
        love.setImageResource( current_comment.isLove() ? R.drawable.favorite : R.drawable.ic_favorite_border_black_24dp );
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                current_comment.setLove( !current_comment.isLove());
                love.setImageResource( current_comment.isLove() ? R.drawable.favorite : R.drawable.ic_favorite_border_black_24dp );

            }
        });



        return convertView;
    }
}