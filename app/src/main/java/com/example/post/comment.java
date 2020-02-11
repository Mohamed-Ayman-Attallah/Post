package com.example.post;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class comment extends AppCompatActivity {
    ListView commentView;
    public static ArrayList<comment2>comments = new ArrayList<>();
    public static commentAdabter CommentAdapter;

    EditText new_comment ;
    ImageView share_comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        comments.add( new comment2("mohamed","hello :)", R.drawable.favorite, true) );
        comments.add(new comment2("ayman","hello<3",R.drawable.favorite,false));
        comments.add(new comment2("medo","hello",R.drawable.favorite,false));
        comments.add(new comment2("elnos","hello",R.drawable.favorite,false));

        CommentAdapter = new commentAdabter(this,comments);
        commentView = findViewById(R.id.comment_list_view);
        commentView.setAdapter(CommentAdapter);

        new_comment = findViewById(R.id.comment_edit_text);
        share_comment = findViewById(R.id.comment_share_btn);

        new_comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(new_comment.getText().toString().isEmpty())
                {
                    share_comment.setEnabled(false);
                    share_comment.setImageResource(R.drawable.favorite);

                }else
                {
                    share_comment.setEnabled(true);
                    share_comment.setImageResource(R.drawable.ic_star_black_24dp);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        share_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comments.add(new comment2("Mohamed",new_comment.getText().toString(),R.drawable.favorite,false));
                CommentAdapter.notifyDataSetChanged();
                new_comment.setText("");
            }
        });
    }
}