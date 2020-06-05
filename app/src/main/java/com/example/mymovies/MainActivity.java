package com.example.mymovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mymovies.Controller.MovieAdapter;
import com.example.mymovies.Model.Movie;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView ;
    private MovieAdapter adapter ;
    private List<Movie> moviesList = new ArrayList<>();
    private AdView mAdView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        AdView adView = new AdView(this);
//        adView.setAdSize(AdSize.SMART_BANNER);
//        adView.setAdUnitId("cca-app-pub-3940256099942544/6300978111");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    recyclerView = findViewById(R.id.recyclerView_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new MovieAdapter( this, moviesList);
        recyclerView.setAdapter(adapter);
        moviesData();

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        isBackgroundRestricted();

    }
    public boolean isBackgroundRestricted (){
        Toast.makeText(getApplicationContext() , " the app is Restricted ", Toast.LENGTH_LONG).show();
        return true ;
    }

    private void moviesData() {
        Movie movie = new Movie("Birds Of Prey" , "Action | Adventure | Crime" , "2020"  , R.drawable.a1) ;
        moviesList.add(movie) ;
        Movie movie2 = new Movie("The lodge" , "Drama | Horror | Thriller" , "2019", R.drawable.a2) ;
        moviesList.add(movie2) ;
        Movie movie3 = new Movie("Shikara" , "Drama | History | Romance " , "2019", R.drawable.a3) ;
        moviesList.add(movie3) ;
        Movie movie4 = new Movie("Malang" , "Action | Romance" , "2020", R.drawable.a4) ;
        moviesList.add(movie4) ;
        Movie movie5 = new Movie("Come To Daddy" , "Comedy | Horror | Thriller" , "2019", R.drawable.a5) ;
        moviesList.add(movie5) ;
        Movie movie6 = new Movie("And Then We Danced " , "Drama | Romance " , "2020", R.drawable.a6) ;
        moviesList.add(movie6) ;
        Movie movie7 = new Movie("Legend Of Defication" , "Action | Romance" , "2020", R.drawable.a8) ;
        moviesList.add(movie7) ;
        Movie movie8 = new Movie("Back To The Future" , "Drama | History | Romance " , "2019", R.drawable.a9) ;
        moviesList.add(movie8) ;
        Movie movie9 = new Movie("Gladiator" , "Drama | Romance " , "2020", R.drawable.a10) ;
        moviesList.add(movie9) ;
        Movie movie10 = new Movie("Forrest Gump" , "Comedy | Horror | Thriller" , "2019", R.drawable.a11) ;
        moviesList.add(movie10) ;
        Movie movie11 = new Movie("The Last AirBender" , "Drama | History | Romance " , "2020",R.drawable.a12) ;
        moviesList.add(movie11) ;
        adapter.notifyDataSetChanged();







    } // end moviesData()
} // end class
