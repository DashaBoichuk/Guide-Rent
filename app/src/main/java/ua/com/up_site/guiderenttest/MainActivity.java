package ua.com.up_site.guiderenttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    ImageView imageview;
    private boolean isGirlOnImage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageview = findViewById(R.id.imageView);

       // imageview.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {
       //         if (isGirlOnImage) {
       //             imageview.setImageResource(R.drawable.man_full);
       //             isGirlOnImage = false;
       //         } else {
       //             imageview.setImageResource(R.drawable.girl);
       //             isGirlOnImage = true;
       //         }
       //     }
       // });



        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TopGuidesActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
