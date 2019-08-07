package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class UserDetailActivity extends AppCompatActivity {

    ImageView mRetour, mUserPic;
    TextView tvUsername;
    FloatingActionButton fabFavorite;
    Integer idFavItem = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        getSupportActionBar().hide();

        //CHECK IN NEIGHBOUR IS ALREADY IN FAVS AND DISABLE BUTTON OR NOT
        fabFavorite = findViewById(R.id.floatingActionButton);
        Neighbour nouvoVoisin = new Neighbour(Integer.parseInt(getIntent().getStringExtra("id")), getIntent().getStringExtra("name"), getIntent().getStringExtra("avatar_url"));
        if (FavNeighbourFragment.mNeighbours.contains(nouvoVoisin)) {
            fabFavorite.setEnabled(false);
            fabFavorite.setImageDrawable(getDrawable(R.drawable.star_filled));
        } else {
            fabFavorite.setEnabled(true);
            fabFavorite.setImageDrawable(getDrawable(R.drawable.star_empty));
        }

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

        //GERE LES FAVORIS
        fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserDetailActivity.this, "ADDED TO FAVS CLICKED", Toast.LENGTH_SHORT).show();

                fabFavorite.setImageDrawable(getDrawable(R.drawable.star_filled));
                fabFavorite.setEnabled(false);

                //RECUP LE NOM ET LA PHOTO ET l'ID ORIGINAL ET CREER UN MODEL "Neighbour" qui sera add a la liste des favs
                FavNeighbourFragment.mNeighbours.add(nouvoVoisin);
                //ON ACTUALISE L'ADAPTER DE LA FAV LISTE DU FRAGMENT POUR VOIR L'ITEM APPARAITRE
                FavNeighbourFragment.mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(FavNeighbourFragment.mNeighbours));

                Toast.makeText(UserDetailActivity.this, "Valeur de l'id : " + getIntent().getStringExtra("id"), Toast.LENGTH_SHORT).show();


            }
        });

    }
}
