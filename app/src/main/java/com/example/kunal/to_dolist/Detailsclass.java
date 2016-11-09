package com.example.kunal.to_dolist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class Detailsclass extends FragmentActivity {

    TextView details, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsclass);
        details = (TextView) findViewById(R.id.details);
        title=(TextView)findViewById(R.id.title);
        title.setText(getIntent().getExtras().getString("title"));
        details.setText(getIntent().getExtras().getString("details"));
    }
}