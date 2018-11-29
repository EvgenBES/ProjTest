package com.example.fox.projtest.ui.info;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fox.projtest.R;
import com.example.fox.projtest.ui.base.BaseActivity;
import com.squareup.picasso.Picasso;

public class InfoActivity extends BaseActivity implements InfoView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ImageView infoImageView = findViewById(R.id.info_imageView);
        TextView infoTextView = findViewById(R.id.info_textView);

        Intent intent = getIntent();
        String url = intent.getStringExtra("GET_URL");
        String message = intent.getStringExtra("GET_MESSAGE");


        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_hourglass)
                .into(infoImageView);

        infoTextView.setText(message);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
