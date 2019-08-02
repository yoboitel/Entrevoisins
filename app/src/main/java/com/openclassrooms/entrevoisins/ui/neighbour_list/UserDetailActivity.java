package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

public class UserDetailActivity extends AppCompatActivity {

    ImageView mRetour, mUserPic;
    TextView tvUsername;
    FloatingActionButton fabFavorite;
    private Boolean isFavorite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        getSupportActionBar().hide();

        //RETOUR QUAND ON CLIQUE SUR LA FLECHE RETOUR
        mRetour = findViewById(R.id.ivBackArrow);
        mRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //RECUPERE LA DATA ENVOYE PAR L'INTENT DU RECYCLER VIEW ADAPTER ACTIVITY

        //RECUPERE ET SET LE PSEUDO
        tvUsername = findViewById(R.id.tvUserName);
        tvUsername.setText(getIntent().getStringExtra("name"));
        //RECUPERE ET SET L'IMAGE
        mUserPic = findViewById(R.id.ivUserAvatar);
        Glide.with(mUserPic.getContext())
                .load(getIntent().getStringExtra("avatar_url"))
                .centerCrop()
                .into(mUserPic);

        isFavorite = false;

        //GERE LES FAVORIS
        fabFavorite = findViewById(R.id.floatingActionButton);
        fabFavorite.setImageDrawable(getDrawable(R.drawable.star_filled));
        fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserDetailActivity.this, "ADDED TO FAVS CLICKED", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
