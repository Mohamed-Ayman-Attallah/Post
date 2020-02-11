package com.example.post;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.post.Home.adapter;

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
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        TextView textView =convertView.findViewById(R.id.name);
        final ImageView post_image = convertView.findViewById(R.id.post_image);
        final CircleImageView circleImageView = convertView.findViewById(R.id.imagee);
        final ImageView love = convertView.findViewById(R.id.love);
        final ImageView delet = convertView.findViewById(R.id.delete);
        ImageView comment = convertView.findViewById(R.id.comment);
        final ImageView star = convertView.findViewById(R.id.star);
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

       star.setImageResource(currentUser.isRate() ? R.drawable.ic_star_black_24dp : R.drawable.ic_star_border_black_24dp);
       star.setOnClickListener(new View.OnClickListener() {

           @Override
             public void onClick(View view) {


                AlertDialog.Builder mBuilder = new AlertDialog.Builder(UserAdapter.this.context);
                View mView =LayoutInflater.from(context).inflate(R.layout.rate,null);
                final RatingBar Rating =mView.findViewById(R.id.starDialog);
                final TextView result =mView.findViewById(R.id.rate3);
                Rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean fromUser) {
                        result.setText(String.valueOf(v));
                        switch ((int) ratingBar.getRating()) {
                            case 1:
                                result.setText("Very bad");
                                break;
                            case 2:
                                result.setText("Need some improvement");
                                break;
                            case 3:
                                result.setText("Good");
                                break;
                            case 4:
                                result.setText("Great");
                                break;
                            case 5:
                                result.setText("Awesome. I love it");
                                break;
                            default:
                                result.setText("");
                        }
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

               currentUser.setRate((!currentUser.isRate()));
               star.setImageResource(currentUser.isRate()? R.drawable.ic_star_black_24dp : R.drawable.ic_star_border_black_24dp);
            }
        });

       delet.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Home.values.remove(position);
               adapter.notifyDataSetChanged();
           }
       });

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), picture.class);

                BitmapDrawable bitmapDrawable = ((BitmapDrawable) circleImageView.getDrawable());
                i.putExtra("photo",encodeImage( bitmapDrawable.getBitmap()));

                getContext().startActivity(i);
            }
        });


        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext() , comment.class);
                getContext().startActivity(i);
            }
        });

        return convertView;
    }

    static public String encodeImage(Bitmap imagee) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imagee.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] b = bytes.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }


    }

