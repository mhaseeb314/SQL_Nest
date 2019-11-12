package com.example.sqlnest.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sqlnest.R;
import com.example.sqlnest.activity.MainActivity;
import com.example.sqlnest.activity.SplashActivity;
import com.example.sqlnest.utils.Util;


//public class GameplayFragment extends Fragment implements View.OnClickListener {
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//
//            case R.id.rlMain : {
//                handler.postDelayed(runnable , NEXT_QUESTION_DELAY);
//            }
//            break;
//        }
//    }
//
//    final int NEXT_QUESTION_DELAY  = 5000;
//    Handler handler = null;
//    Runnable runnable = null;
//    Thread t1;
//    int progressStatus = 100;
//    ContentLoadingProgressBar pbTime;
//    RelativeLayout rlMain;
//    boolean stopThread = false;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_gameplay, container, false);
//
//
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        pbTime = view.findViewById(R.id.pbTime);
//        rlMain = view.findViewById(R.id.rlMain);
//        rlMain.setOnClickListener(this);
//        handler = new Handler();
//
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//                t1.interrupt();
//                changeUi();
//            }
//        };
//
//        t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (!Thread.currentThread().isInterrupted() && progressStatus >= 0){
//                    if(progressStatus == 0){
//                        Toast.makeText(getContext() , "Time Up!" , Toast.LENGTH_LONG).show();
//                        handler.postDelayed(runnable , NEXT_QUESTION_DELAY);
//                        Thread.currentThread().interrupt();
//                    }else{
//                        progressStatus -= 1;
//                        try {
//                            pbTime.setProgress(progressStatus);
//                            Thread.currentThread().sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                }
//            }
//        });
//        t1.start();
//    }
//
//    public void changeUi(){
//
//        QuizFragment gamePlayFrgment = new QuizFragment();
//        Util.replaceFragment(gamePlayFrgment , QuizFragment.class.getSimpleName(),
//                true ,
//                false ,
//                getActivity());
//    }
//
//    public boolean stopThread(){
//        return  stopThread = true;
//    }
//}
