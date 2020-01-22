package com.example.sqlnest.utils;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.sqlnest.R;

public class Util {

    public static void replaceFragment(Fragment fragment, String tag , Boolean addToStack , Boolean clearStack
            , FragmentActivity fa){
        if(clearStack)
            fa.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction ft = fa.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container,fragment,tag);
        if(addToStack)
            ft.addToBackStack(tag);
        ft.commit();
    }
}
