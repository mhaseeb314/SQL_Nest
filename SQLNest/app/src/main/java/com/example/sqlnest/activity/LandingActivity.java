package com.example.sqlnest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sqlnest.R;
import com.example.sqlnest.fragment.HomeFragment;
import com.example.sqlnest.fragment.LoginFragment;
import com.example.sqlnest.utils.Util;

public class LandingActivity extends AppCompatActivity {


    public RelativeLayout toolbar;
    public TextView tvToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);


        toolbar = findViewById(R.id.rlToolbar);
        tvToolbar = findViewById(R.id.tvToolbar);

        LoginFragment loginFragment = new LoginFragment();
        Util.replaceFragment(loginFragment , loginFragment.getClass().getSimpleName() , false , true , this);
    }
}
