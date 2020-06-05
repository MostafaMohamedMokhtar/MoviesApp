package com.example.mymovies.View;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymovies.R;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    // using ButterKnife
    @BindView(R.id.image_details_id)
    ImageView image;
    @BindView(R.id.myTitle_id)
    TextView title;
    @BindView(R.id.myGenre_id)
    TextView genre;
    @BindView(R.id.myYear_id)
    TextView year;
    private Bundle extras;
   // NativeExpressAdView nativeExpressAdView ;
    ColorDrawable background ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
 try {
     MobileAds.initialize(this, new OnInitializationCompleteListener() {
         @Override
         public void onInitializationComplete(InitializationStatus initializationStatus) {

         }
     });
     AdLoader adLoader = new AdLoader.Builder(this , getString(R.string.native_advanced_ad_unit_id))
             .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                 @Override
                 public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                     NativeTemplateStyle styles = new
                             NativeTemplateStyle.Builder().withMainBackgroundColor(background).build();

                     TemplateView templateView = findViewById(R.id.my_template);
                     templateView.setStyles(styles);
                     templateView.setNativeAd(unifiedNativeAd);
                 }
             })
             .build();
     adLoader.loadAd(new AdRequest.Builder().build());
//      nativeExpressAdView = findViewById(R.id.native_id);
//      nativeExpressAdView = new NativeExpressAdView(this);
//     nativeExpressAdView.setAdSize(new AdSize(AdSize.FULL_WIDTH ,320));
//     nativeExpressAdView.setAdUnitId("ca-app-pub-3940256099942544/2247696110");
//     AdRequest adRequest = new AdRequest.Builder().build();
//     nativeExpressAdView.loadAd(adRequest);
     Toast.makeText(getApplicationContext() , "Success loading" , Toast.LENGTH_LONG).show();
 }
 catch (Exception e){
     Log.d(" Error message " , e.getLocalizedMessage());
     Toast.makeText(getApplicationContext() , e.getLocalizedMessage() , Toast.LENGTH_LONG).show();
 }
        ButterKnife.bind(this);
        extras = getIntent().getExtras();
        if (extras != null) {
            title.setText(extras.getString("Title"));
            genre.setText(extras.getString("Genre"));
            year.setText(extras.getString("Year"));
            image.setImageResource(extras.getInt("Image"));
        }
    } // end onCreate()

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //nativeExpressAdView.destroy();
    }
} // end class
