package com.example.sqlnest.fragment;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.sqlnest.R;
import com.example.sqlnest.activity.MainActivity;
import com.example.sqlnest.adapter.QuizAdapter;
import com.example.sqlnest.models.Test;
import com.example.sqlnest.utils.ListSpacingDecoration;
import com.example.sqlnest.utils.Util;

import java.util.ArrayList;


public class QuizFragment extends Fragment implements QuizAdapter.ItemClickListener , View.OnClickListener {

    @Override
    public void onDetail(Test test) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

//            case R.id.rlMixTest : {
//                GameplayFragment gamePlayFrgment = new GameplayFragment();
//                Util.replaceFragment(gamePlayFrgment , GameplayFragment.class.getSimpleName(),
//                        true ,
//                        false ,
//                        getActivity());
//            }
//            break;
        }
    }

    Window window;
//    QuizAdapter adapter;
//    ArrayList<Test> tests = new ArrayList<>();
//    RecyclerView rvQuiz;
//    RelativeLayout rlMixTest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        tests = Util.getTests();
        window = getActivity().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        rlMixTest = view.findViewById(R.id.rlMixTest);
//        rlMixTest.setOnClickListener(this);
//        findViewById(view);
//        setAdapter();
        setToolbar();
    }

//    private void findViewById(View view) {
//        rvQuiz = view.findViewById(R.id.rvQuiz);
//    }

//    private void setAdapter() {
//        adapter = new QuizAdapter(getContext(), tests, this);
//        rvQuiz.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        rvQuiz.addItemDecoration(new ListSpacingDecoration((int) getResources().getDimension(R.dimen.recycler_view_grid_spacing)));
//        rvQuiz.setAdapter(adapter);
//
//
//    }

    private void setToolbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.red));
        }
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).tvToolbar.setText(getString(R.string.quiz));
        ((MainActivity) getActivity()).tvToolbar.setBackgroundColor(getResources().getColor(R.color.red));
    }


}
