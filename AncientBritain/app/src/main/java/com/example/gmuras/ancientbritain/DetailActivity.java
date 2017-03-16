package com.example.gmuras.ancientbritain;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class DetailActivity extends AppCompatActivity {
    private ImageView detailImage;
    private ArrayList<MainDataDef> detailData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailImage = (ImageView) findViewById(R.id.detail_image);
        TextView detailName = (TextView) findViewById(R.id.detail_name);
        TextView detailDistance = (TextView) findViewById(R.id.detail_distance);

        TextView detailText = (TextView) findViewById(R.id.detail_text);
        detailText.setMovementMethod(new ScrollingMovementMethod());

        ImageView detailWebLink = (ImageView) findViewById(R.id.detail_web_link);

        int i = MainActivity.currentItem;
        Random n = new Random();
        int m = n.nextInt((600 - 20) + 1) + 20;

        setTitle(getString(R.string.app_name) + " - " + MainData.nameArray[i]);

        detailImage.setImageResource(MainData.detailImageArray[i]);
        detailName.setText(MainData.nameArray[i]);
        detailDistance.setText(String.valueOf(m) + " miles");
        detailText.setText(MainData.detailTextArray[i]);

        detailWebLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(MainData.detailWebLink[MainActivity.currentItem]));
                startActivity(intent);
            }
        });
    }
}
