package com.example.sqlnest.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlnest.R;
import com.example.sqlnest.fragment.HomeFragment;
import com.example.sqlnest.model.User;
import com.example.sqlnest.utils.Util;

public class MainActivity extends AppCompatActivity {

    public LinearLayout toolbar;
    public TextView tvToolbar;
    public RelativeLayout rlToolbar;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new User();
        user = (User) (getIntent().getSerializableExtra("user"));

        toolbar = findViewById(R.id.toolbar);
        tvToolbar = findViewById(R.id.tvToolbar);
        rlToolbar = findViewById(R.id.rlToolbar);

        HomeFragment homeFragment = new HomeFragment();
        Util.replaceFragment(homeFragment, homeFragment.getClass().getSimpleName(), false, true, this);
    }


//     void replaceFragment(Fragment fragment,String tag ,Boolean addToStack , Boolean clearStack ){
//        if(clearStack)
//            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.fragment_container,fragment,tag);
//        if(addToStack)
//            ft.addToBackStack(tag);
//        ft.commit();
//    }
}
