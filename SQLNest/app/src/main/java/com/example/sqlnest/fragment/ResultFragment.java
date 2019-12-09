package com.example.sqlnest.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sqlnest.R;
import com.example.sqlnest.activity.MainActivity;
import com.example.sqlnest.models.Test;
import com.example.sqlnest.utils.SessionManager;
import com.example.sqlnest.utils.Util;

public class ResultFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.ivHome :{
                removeTopFragmentFromStack(this);
                QuizFragment quizFragment = new QuizFragment();
                Util.replaceFragment(quizFragment , QuizFragment.class.getSimpleName(),
                        false , false,
                        getActivity());
            }
            break;
            case R.id.ivReplay : {
                test.setToDefault();
                removeTopFragmentFromStack(this);
                GameplayFragment gamePlayFrgment = new GameplayFragment().newInstance(test);
                Util.replaceFragment(gamePlayFrgment , GameplayFragment.class.getSimpleName(),
                        true ,
                        false ,
                        getActivity());
            }
        }
    }

    Test test;
    ProgressBar pbResult;
    TextView tvRemarks;
    TextView tvResult;
    TextView tvScore;
    TextView tvScoreR;
    TextView tvTotalScoreR;
    TextView tvTotalScore;
    TextView tvCorrectAnswers;
    ImageView ivHome;
    ImageView ivReplay;
    LinearLayout llCorrectQuestions;
    TextView tvTotalQuestions;
    ObjectAnimator pbAnimator;
    int scorePercentage;
    ValueAnimator resultAnimator;
    int ANIMATION_DURATION = 4000;
    Window window;
    GradientDrawable drawable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            test = ((Test) getArguments().getSerializable("test"));
        }
        window = getActivity().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViewById(view);
        setUI();

        ivReplay.setOnClickListener(this);
        ivHome.setOnClickListener(this);
    }

    private void setAnimations() {

        pbAnimator = ObjectAnimator.ofInt(pbResult , "progress" , 0 , scorePercentage);
        resultAnimator = ValueAnimator.ofInt(0 , scorePercentage);
        resultAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                tvResult.setText(valueAnimator.getAnimatedValue().toString() + "%");
            }
        });
        pbAnimator.setDuration(ANIMATION_DURATION);
        resultAnimator.setDuration(ANIMATION_DURATION);

        pbAnimator.start();
        resultAnimator.start();
    }

    private void setUI() {

        switch (test.getTestName()){
                case "ddl" : {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.pink));
                    }
                    ((MainActivity) getActivity()).tvToolbar.setText(getString(R.string.result));
                    ((MainActivity) getActivity()).tvToolbar.setBackgroundColor(getResources().getColor(R.color.pink));
                    tvResult.setTextColor(getResources().getColor(R.color.pink));
                    tvScore.setTextColor(getResources().getColor(R.color.pink));
                    tvScoreR.setTextColor(getResources().getColor(R.color.pink));
                    tvTotalScore.setTextColor(getResources().getColor(R.color.pink));
                    tvTotalScoreR.setTextColor(getResources().getColor(R.color.pink));

                    drawable = (GradientDrawable) llCorrectQuestions.getBackground();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        drawable.setColor(getResources().getColorStateList(R.color.pink));
                        ivHome.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.pink)));
                        ivReplay.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.pink)));
                    }

                }
                break;
                case "dml" : {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.pulm));
                    }
                    ((MainActivity) getActivity()).tvToolbar.setText(getString(R.string.result));
                    ((MainActivity) getActivity()).tvToolbar.setBackgroundColor(getResources().getColor(R.color.pulm));
                    tvResult.setTextColor(getResources().getColor(R.color.pulm));
                    tvScore.setTextColor(getResources().getColor(R.color.pulm));
                    tvScoreR.setTextColor(getResources().getColor(R.color.pulm));
                    tvTotalScore.setTextColor(getResources().getColor(R.color.pulm));
                    tvTotalScoreR.setTextColor(getResources().getColor(R.color.pulm));

                    drawable = (GradientDrawable) llCorrectQuestions.getBackground();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        drawable.setColor(getResources().getColorStateList(R.color.pulm));
                        ivHome.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.pulm)));
                        ivReplay.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.pulm)));
                    }
                }
                break;
                case "af" : {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.seaGreen));
                    }
                    ((MainActivity) getActivity()).tvToolbar.setText(getString(R.string.result));
                    ((MainActivity) getActivity()).tvToolbar.setBackgroundColor(getResources().getColor(R.color.seaGreen));
                    tvResult.setTextColor(getResources().getColor(R.color.seaGreen));
                    tvScore.setTextColor(getResources().getColor(R.color.seaGreen));
                    tvScoreR.setTextColor(getResources().getColor(R.color.seaGreen));
                    tvTotalScore.setTextColor(getResources().getColor(R.color.seaGreen));
                    tvTotalScoreR.setTextColor(getResources().getColor(R.color.seaGreen));

                    drawable = (GradientDrawable) llCorrectQuestions.getBackground();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        drawable.setColor(getResources().getColorStateList(R.color.seaGreen));
                        ivHome.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.seaGreen)));
                        ivReplay.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.seaGreen)));
                    }
                }
                break;
                default :{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.peach));
                    }
                    ((MainActivity) getActivity()).tvToolbar.setText(getString(R.string.result));
                    ((MainActivity) getActivity()).tvToolbar.setBackgroundColor(getResources().getColor(R.color.peach));
                    tvResult.setTextColor(getResources().getColor(R.color.peach));
                    tvScore.setTextColor(getResources().getColor(R.color.peach));
                    tvScoreR.setTextColor(getResources().getColor(R.color.peach));
                    tvTotalScore.setTextColor(getResources().getColor(R.color.peach));
                    tvTotalScoreR.setTextColor(getResources().getColor(R.color.peach));

                    drawable = (GradientDrawable) llCorrectQuestions.getBackground();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        drawable.setColor(getResources().getColorStateList(R.color.peach));
                        ivHome.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.peach)));
                        ivReplay.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.peach)));
                    }
                }

        }

        double gainedScore;
        double totalTestScore = 0;

        for (int i = 0; i <test.getQuestions().size(); i++) {
            totalTestScore += test.getQuestions().get(i).getQuestionScore();
        }

        gainedScore = test.getTotalGainScore();
        double percentage = (gainedScore / totalTestScore) * 100;
        scorePercentage = (int) percentage;

        tvTotalScore.setText(String.valueOf((int) totalTestScore));
        tvScore.setText(String.valueOf(test.getTotalGainScore()));
        tvCorrectAnswers.setText(String.valueOf(test.getTotalCorrectAnswers()));
        tvTotalQuestions.setText(String.valueOf(test.getQuestions().size()));

        if(scorePercentage <= 50){

            tvRemarks.setText("Better Luck Next Time!");
            tvRemarks.setTextColor(getResources().getColor(R.color.red));

        }else if(scorePercentage <= 75){

            tvRemarks.setText("Very Good!");
            tvRemarks.setTextColor(getResources().getColor(R.color.yellow));

        }else if(scorePercentage <= 100){

            tvRemarks.setText("Excellent!");
            tvRemarks.setTextColor(getResources().getColor(R.color.green));
        }

        setAnimations();
    }

    private void findViewById(View view) {

        pbResult = view.findViewById(R.id.pbResult);
        tvRemarks = view.findViewById(R.id.tvRemarks);
        tvScore = view.findViewById(R.id.tvResultScore);
        tvResult = view.findViewById(R.id.tvResult);
        tvScoreR = view.findViewById(R.id.tvScoreR);
        tvTotalScoreR = view.findViewById(R.id.tvTotalScoreR);
        tvTotalScore = view.findViewById(R.id.tvTotalScore);
        tvCorrectAnswers = view.findViewById(R.id.tvCorrectAnswers);
        tvTotalQuestions = view.findViewById(R.id.tvTotalQuestions);
        llCorrectQuestions = view.findViewById(R.id.llCorrectQuestions);
        ivHome = view.findViewById(R.id.ivHome);
        ivReplay = view.findViewById(R.id.ivReplay);

    }
    public static ResultFragment newInstance(Test test) {

        Bundle args = new Bundle();
        args.putSerializable("test" , test);
        ResultFragment fragment = new ResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void removeTopFragmentFromStack(Fragment targetedFragment) {

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(targetedFragment);
        trans.commit();
        manager.popBackStack();
    }

}
