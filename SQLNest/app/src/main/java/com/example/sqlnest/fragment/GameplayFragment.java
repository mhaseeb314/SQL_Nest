package com.example.sqlnest.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sqlnest.R;
import com.example.sqlnest.activity.MainActivity;
import com.example.sqlnest.models.Test;
import com.example.sqlnest.utils.Util;


public class GameplayFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tvChoiceA: {
                stopAnimations();
                changeButtonsColor(R.id.tvChoiceA);
                test.getQuestions().get(i).getOptions().get(0).setSelectedOption(true);
                setQuestionScore(0);
                handler = new Handler();
                isHandlerActive = handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isHandlerActive = false;
                        setToDefault();
                        changeUi(++i);
                    }
                }, NEXT_QUESTION_DELAY);

            }
            break;
            case R.id.tvChoiceB: {
                stopAnimations();
                changeButtonsColor(R.id.tvChoiceB);
                test.getQuestions().get(i).getOptions().get(1).setSelectedOption(true);
                setQuestionScore(1);
                handler = new Handler();
                isHandlerActive = handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isHandlerActive = false;
                        setToDefault();
                        changeUi(++i);
                    }
                }, NEXT_QUESTION_DELAY);
            }
            break;
            case R.id.tvChoiceC: {
                stopAnimations();
                changeButtonsColor(R.id.tvChoiceC);
                test.getQuestions().get(i).getOptions().get(2).setSelectedOption(true);
                setQuestionScore(2);
                handler = new Handler();
                isHandlerActive = handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isHandlerActive = false;
                        setToDefault();
                        changeUi(++i);
                    }
                }, NEXT_QUESTION_DELAY);
            }
            break;
            case R.id.tvChoiceD: {
                stopAnimations();
                changeButtonsColor(R.id.tvChoiceD);
                test.getQuestions().get(i).getOptions().get(3).setSelectedOption(true);
                setQuestionScore(3);
                handler = new Handler();
                isHandlerActive = handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isHandlerActive = false;
                        setToDefault();
                        changeUi(++i);
                    }
                }, NEXT_QUESTION_DELAY);
            }
            break;
        }
    }


    final int NEXT_QUESTION_DELAY = 3000;
    Test test;
    int i = 0;
    long questionTime;
    int questionScore;
    float pbTop = 0;
    float pbRight = 0;
    float pbLeft = 0;
    float pbBottom = 0;
    boolean isTestStarted = false;
    boolean isHandlerActive = false;

    ImageView ivDropUp;
    ImageView ivDropDown;
    TextView tvTime;
    TextView tvScore;
    TextView tvChoiceA;
    TextView tvChoiceB;
    TextView tvChoiceC;
    TextView tvChoiceD;
    TextView tvQuestion;
    RelativeLayout rlScore;
    RelativeLayout rlTime;
    TextView tvQuestionNumber;
    LinearLayout rlQuestion;

    CountDownTimer timeTimer;
    ObjectAnimator progressAnimator;
    ObjectAnimator scoreAnimator;
    ObjectAnimator timeAnimator;
    ValueAnimator scoreTimer;
    ContentLoadingProgressBar pbTime;
    Window window;
    GradientDrawable drawable;
    Handler handler;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            test = (Test) getArguments().getSerializable("test");
        }
        window = getActivity().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gameplay, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        if(!sessionManager.getTest().equals("")){
//            String testName = sessionManager.getTest();
//
//            switch (testName){
//
//                case "ddl" : {
//                    test = Util.getTests().get(0);
//                }
//                break;
//
//                case "dml" : {
//                    test = Util.getTests().get(1);
//                }
//                break;
//
//                case "af" : {
//                    test = Util.getTests().get(2);
//                }
//                break;
//
//                case "acf" : {
//                    test = Util.getTests().get(3);
//                }
//                break;
//            }
//        }
        test.setToDefault();
        findViewById(view);
        setOnClickListeners();
        setToDefault();
        getScreenSize();

    }

    private void getScreenSize() {
        pbTime.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("GamplayFragment", "Top " + pbTime.getTop() + " bottom " + pbTime.getBottom() + " Right " + pbTime.getRight() + " Left " + pbTime.getLeft());
                Log.i("ScreenSize", "Width " + Resources.getSystem().getDisplayMetrics().widthPixels + " Height" + Resources.getSystem().getDisplayMetrics().heightPixels);

                pbTop = pbTime.getTop();
                pbLeft = pbTime.getLeft();
                pbRight = pbTime.getRight();
                pbBottom = pbTime.getBottom();

                progressAnimator = ObjectAnimator.ofInt(pbTime, "progress", 100, 0);
                timeAnimator = ObjectAnimator.ofFloat(rlTime, "x", pbRight - 20f, pbLeft - 20f);
                scoreAnimator = ObjectAnimator.ofFloat(rlScore, "x", pbRight - 20f, pbLeft - 20f);

                if (!isTestStarted) {
                    startTest();
                    isTestStarted = true;
                }
                pbTime.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

    }

    private void startTest() {

        if (test != null) {
            changeUi(i);
        }
    }

    private void changeUi(final int questionNumber) {

        if (i < test.getQuestions().size()) {

            int question = questionNumber + 1;
            tvQuestionNumber.setText("Q " + question + ":");
            tvQuestion.setText(test.getQuestions().get(questionNumber).getQuestion());
            int noOfChoices = test.getQuestions().get(questionNumber).getOptions().size();
            int timeInMilliSec = test.getQuestions().get(questionNumber).getQuestionTime() * 1000;

            for (int i = 0; i < noOfChoices; i++) {

                if (noOfChoices == 2) {

                    switch (i) {

                        case 0: {
                            tvChoiceA.setText(test.getQuestions().get(questionNumber)
                                    .getOptions().get(i).getOption());
                        }
                        break;
                        case 1: {
                            tvChoiceB.setText(test.getQuestions().get(questionNumber)
                                    .getOptions().get(i).getOption());
                        }
                    }

                    tvChoiceC.setVisibility(View.GONE);
                    tvChoiceD.setVisibility(View.GONE);

                } else {

                    switch (i) {

                        case 0: {
                            tvChoiceA.setText(test.getQuestions().get(questionNumber)
                                    .getOptions().get(i).getOption());
                        }
                        break;
                        case 1: {
                            tvChoiceB.setText(test.getQuestions().get(questionNumber)
                                    .getOptions().get(i).getOption());
                        }
                        break;
                        case 2: {
                            tvChoiceC.setText(test.getQuestions().get(questionNumber)
                                    .getOptions().get(i).getOption());
                        }
                        break;
                        case 3: {
                            tvChoiceD.setText(test.getQuestions().get(questionNumber)
                                    .getOptions().get(i).getOption());
                        }
                    }
                }
            }

            tvTime.setText(String.valueOf(test.getQuestions().get(questionNumber).getQuestionTime()));
            tvScore.setText(String.valueOf(test.getQuestions().get(questionNumber).getQuestionScore()));
            pbTime.setProgress(100);

            setTimerTime(timeInMilliSec , test.getQuestions().get(i).getQuestionScore());
            setTimer();
            startAnimationAndTest(timeInMilliSec);

        } else {
            stopAnimations();
            setTotalScoreGained();
            removeTopFragmentFromStack(this);
            ResultFragment resultFragment = new ResultFragment().newInstance(test);
            Util.replaceFragment(resultFragment, ResultFragment.class.getSimpleName(),
                    true,
                    false, getActivity());
        }

    }

    private void removeTopFragmentFromStack(Fragment targetedFragment) {

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(targetedFragment);
        trans.commit();
        manager.popBackStack();
    }

    private void setTotalScoreGained() {

        int totalGainedScore = 0;
        for (int j = 0; j < test.getQuestions().size() - 1; j++) {
            totalGainedScore += test.getQuestions().get(j).getGainScore();
        }
        test.setTotalGainScore(totalGainedScore);
    }

    private void startAnimationAndTest(final long timeDurationMiliSecs) {


        progressAnimator.setDuration(timeDurationMiliSecs);
        timeAnimator.setDuration(timeDurationMiliSecs);
        scoreAnimator.setDuration(timeDurationMiliSecs);

        scoreAnimator.start();
        progressAnimator.start();
        timeAnimator.start();
        timeTimer.start();
        scoreTimer.start();

    }

    private void setTimerTime(long timeDurationInMilliSecs , int score) {
        questionTime = timeDurationInMilliSecs + 1000;
        questionScore = score;
    }

    private void setTimer() {

        long countDownInterval = questionTime / 100;
        long qTime = questionTime;


        timeTimer = new CountDownTimer(qTime, countDownInterval) {

            @Override
            public void onTick(long millisUntilFinished) {

                long seconds = millisUntilFinished / 1000;

                String str = (String.format("%02d", seconds / questionTime)
                        + ":" + String.format("%02d", seconds % questionTime));

                tvTime.setText(str);
            }

            @Override
            public void onFinish() {
                pbTime.setProgress(0);
                tvScore.setText("0");
                Toast.makeText(getContext(), "Time Up!", Toast.LENGTH_SHORT).show();
                stopAnimations();
                tvChoiceA.setClickable(false);
                tvChoiceB.setClickable(false);
                tvChoiceC.setClickable(false);
                tvChoiceD.setClickable(false);

                handler = new Handler();
                isHandlerActive = handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isHandlerActive = false;
                        setToDefault();
                        changeUi(++i);
                    }
                }, NEXT_QUESTION_DELAY);


            }
        };

        scoreTimer =  ValueAnimator.ofInt(questionScore , 0);
        scoreTimer.setDuration(questionTime)
                .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        tvScore.setText(valueAnimator.getAnimatedValue().toString());
                    }
                });

    }

    private void cancelTimer() {
        if (timeTimer != null) {
            timeTimer.cancel();
        }
    }

    private void findViewById(View view) {

        pbTime = view.findViewById(R.id.pbTime);
        tvTime = view.findViewById(R.id.tvTime);
        tvScore = view.findViewById(R.id.tvScore);
        rlScore = view.findViewById(R.id.rlScore);
        rlTime = view.findViewById(R.id.rlTime);
        tvChoiceA = view.findViewById(R.id.tvChoiceA);
        tvChoiceB = view.findViewById(R.id.tvChoiceB);
        tvChoiceC = view.findViewById(R.id.tvChoiceC);
        tvChoiceD = view.findViewById(R.id.tvChoiceD);
        tvQuestion = view.findViewById(R.id.tvQuestion);
        tvQuestionNumber = view.findViewById(R.id.tvQuestionNumber);
        rlQuestion = view.findViewById(R.id.rlQuestion);
        ivDropUp = view.findViewById(R.id.ivDropUp);
        ivDropDown = view.findViewById(R.id.ivDropDown);

    }

    private void setOnClickListeners() {

        tvChoiceA.setOnClickListener(this);
        tvChoiceB.setOnClickListener(this);
        tvChoiceC.setOnClickListener(this);
        tvChoiceD.setOnClickListener(this);

    }

    private void changeButtonsColor(int id) {

        switch (test.getTestName()) {
            case "ddl": {
                switch (id) {

                    case R.id.tvChoiceA: {
                        tvChoiceA.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceA.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.pink));
                        }
                        tvChoiceA.setTextColor(getResources().getColor(R.color.colorWhite));
                    }
                    break;
                    case R.id.tvChoiceB: {
                        tvChoiceB.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceB.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.pink));
                        }
                        tvChoiceB.setTextColor(getResources().getColor(R.color.colorWhite));
                    }
                    break;
                    case R.id.tvChoiceC: {
                        tvChoiceC.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceC.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.pink));
                        }
                        tvChoiceC.setTextColor(getResources().getColor(R.color.colorWhite));
                    }
                    break;
                    case R.id.tvChoiceD: {
                        tvChoiceD.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceD.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.pink));
                        }
                        tvChoiceD.setTextColor(getResources().getColor(R.color.colorWhite));
                    }
                }
                tvChoiceA.setClickable(false);
                tvChoiceB.setClickable(false);
                tvChoiceC.setClickable(false);
                tvChoiceD.setClickable(false);
            }
            break;
            case "dml": {
                switch (id) {

                    case R.id.tvChoiceA: {
                        tvChoiceA.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceA.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.pulm));
                        }
                        tvChoiceA.setTextColor(getResources().getColor(R.color.colorWhite));

                    }
                    break;
                    case R.id.tvChoiceB: {
                        tvChoiceB.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceB.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.pulm));
                        }
                        tvChoiceB.setTextColor(getResources().getColor(R.color.colorWhite));

                    }
                    break;
                    case R.id.tvChoiceC: {
                        tvChoiceC.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceC.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.pulm));
                        }
                        tvChoiceC.setTextColor(getResources().getColor(R.color.colorWhite));

                    }
                    break;
                    case R.id.tvChoiceD: {
                        tvChoiceD.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceD.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.pulm));
                        }
                        tvChoiceD.setTextColor(getResources().getColor(R.color.colorWhite));

                    }
                }
                tvChoiceA.setClickable(false);
                tvChoiceB.setClickable(false);
                tvChoiceC.setClickable(false);
                tvChoiceD.setClickable(false);
            }
            break;
            case "af": {
                switch (id) {

                    case R.id.tvChoiceA: {
                        tvChoiceA.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceA.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.seaGreen));
                        }
                        tvChoiceA.setTextColor(getResources().getColor(R.color.colorWhite));
                    }
                    break;
                    case R.id.tvChoiceB: {
                        tvChoiceB.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceB.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.seaGreen));
                        }
                        tvChoiceB.setTextColor(getResources().getColor(R.color.colorWhite));

                    }
                    break;
                    case R.id.tvChoiceC: {
                        tvChoiceC.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceC.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.seaGreen));
                        }
                        tvChoiceC.setTextColor(getResources().getColor(R.color.colorWhite));
                    }
                    break;
                    case R.id.tvChoiceD: {
                        tvChoiceD.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceD.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.seaGreen));
                        }
                        tvChoiceD.setTextColor(getResources().getColor(R.color.colorWhite));
                    }
                }

                tvChoiceA.setClickable(false);
                tvChoiceB.setClickable(false);
                tvChoiceC.setClickable(false);
                tvChoiceD.setClickable(false);
            }
            break;
            default: {
                switch (id) {

                    case R.id.tvChoiceA: {
                        tvChoiceA.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceA.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.peach));
                        }
                        tvChoiceA.setTextColor(getResources().getColor(R.color.colorWhite));
                    }
                    break;
                    case R.id.tvChoiceB: {
                        tvChoiceB.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceB.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.peach));
                        }
                        tvChoiceB.setTextColor(getResources().getColor(R.color.colorWhite));
                    }
                    break;
                    case R.id.tvChoiceC: {
                        tvChoiceC.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceC.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.peach));
                        }
                        tvChoiceC.setTextColor(getResources().getColor(R.color.colorWhite));
                    }
                    break;
                    case R.id.tvChoiceD: {
                        tvChoiceD.setBackground(getResources().getDrawable(R.drawable.drawable_selected_choice));

                        drawable = (GradientDrawable) tvChoiceD.getBackground();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            drawable.setColor(getResources().getColorStateList(R.color.peach));
                        }
                        tvChoiceD.setTextColor(getResources().getColor(R.color.colorWhite));
                    }
                }
                tvChoiceA.setClickable(false);
                tvChoiceB.setClickable(false);
                tvChoiceC.setClickable(false);
                tvChoiceD.setClickable(false);
            }

        }

    }

    private void setToDefault() {

        if (!isTestStarted) {
            switch (test.getTestName()) {
                case "ddl": {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.pink));
                        pbTime.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.pink)));
                    }
                    ((MainActivity) getActivity()).tvToolbar.setText(test.getTestName().toUpperCase() + " " + getResources().getString(R.string.quiz));
                    ((MainActivity) getActivity()).tvToolbar.setBackgroundColor(getResources().getColor(R.color.pink));

                    ivDropUp.setImageResource(R.drawable.pinkup);
                    ivDropDown.setImageResource(R.drawable.pinkdown);

                    drawable = (GradientDrawable) rlQuestion.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pink));

                    drawable = (GradientDrawable) tvChoiceA.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pink));

                    drawable = (GradientDrawable) tvChoiceB.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pink));

                    drawable = (GradientDrawable) tvChoiceC.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pink));

                    drawable = (GradientDrawable) tvChoiceD.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pink));

                }
                break;
                case "dml": {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.pulm));
                        pbTime.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.pulm)));

                    }
                    ((MainActivity) getActivity()).tvToolbar.setText(test.getTestName().toUpperCase() + " " + getResources().getString(R.string.quiz));
                    ((MainActivity) getActivity()).tvToolbar.setBackgroundColor(getResources().getColor(R.color.pulm));


                    drawable = (GradientDrawable) rlQuestion.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pulm));

                    ivDropUp.setImageResource(R.drawable.plumup);
                    ivDropDown.setImageResource(R.drawable.plumdown);

                    drawable = (GradientDrawable) tvChoiceA.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pulm));

                    drawable = (GradientDrawable) tvChoiceB.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pulm));

                    drawable = (GradientDrawable) tvChoiceC.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pulm));

                    drawable = (GradientDrawable) tvChoiceD.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pulm));

                }
                break;
                case "af": {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.seaGreen));
                        pbTime.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.seaGreen)));

                    }
                    ((MainActivity) getActivity()).tvToolbar.setText(test.getTestName().toUpperCase() + " " + getResources().getString(R.string.quiz));
                    ((MainActivity) getActivity()).tvToolbar.setBackgroundColor(getResources().getColor(R.color.seaGreen));

                    ivDropUp.setImageResource(R.drawable.seagreenup);
                    ivDropDown.setImageResource(R.drawable.seagreendown);

                    drawable = (GradientDrawable) rlQuestion.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.seaGreen));

                    drawable = (GradientDrawable) tvChoiceA.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.seaGreen));

                    drawable = (GradientDrawable) tvChoiceB.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.seaGreen));

                    drawable = (GradientDrawable) tvChoiceC.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.seaGreen));

                    drawable = (GradientDrawable) tvChoiceD.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.seaGreen));

                }
                break;
                default: {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.peach));
                        pbTime.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.peach)));

                    }
                    ((MainActivity) getActivity()).tvToolbar.setText(test.getTestName().toUpperCase() + " " + getResources().getString(R.string.quiz));
                    ((MainActivity) getActivity()).tvToolbar.setBackgroundColor(getResources().getColor(R.color.peach));

                    ivDropUp.setImageResource(R.drawable.peachup);
                    ivDropDown.setImageResource(R.drawable.peachdown);

                    drawable = (GradientDrawable) rlQuestion.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.peach));

                    drawable = (GradientDrawable) tvChoiceA.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.peach));

                    drawable = (GradientDrawable) tvChoiceB.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.peach));

                    drawable = (GradientDrawable) tvChoiceC.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.peach));

                    drawable = (GradientDrawable) tvChoiceD.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.peach));

                }
                break;
            }
        } else {

            switch (test.getTestName()) {
                case "ddl": {

                    tvChoiceA.setBackground(getResources().getDrawable(R.drawable.drawable_choice));
                    tvChoiceB.setBackground(getResources().getDrawable(R.drawable.drawable_choice));
                    tvChoiceC.setBackground(getResources().getDrawable(R.drawable.drawable_choice));
                    tvChoiceD.setBackground(getResources().getDrawable(R.drawable.drawable_choice));


                    tvChoiceA.setTextColor(getResources().getColor(R.color.black));
                    tvChoiceB.setTextColor(getResources().getColor(R.color.black));
                    tvChoiceC.setTextColor(getResources().getColor(R.color.black));
                    tvChoiceD.setTextColor(getResources().getColor(R.color.black));

                    drawable = (GradientDrawable) rlQuestion.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pink));

                    drawable = (GradientDrawable) tvChoiceA.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pink));

                    drawable = (GradientDrawable) tvChoiceB.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pink));

                    drawable = (GradientDrawable) tvChoiceC.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pink));

                    drawable = (GradientDrawable) tvChoiceD.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pink));

                }
                break;
                case "dml": {

                    tvChoiceA.setBackground(getResources().getDrawable(R.drawable.drawable_choice));
                    tvChoiceB.setBackground(getResources().getDrawable(R.drawable.drawable_choice));
                    tvChoiceC.setBackground(getResources().getDrawable(R.drawable.drawable_choice));
                    tvChoiceD.setBackground(getResources().getDrawable(R.drawable.drawable_choice));

                    tvChoiceA.setTextColor(getResources().getColor(R.color.black));
                    tvChoiceB.setTextColor(getResources().getColor(R.color.black));
                    tvChoiceC.setTextColor(getResources().getColor(R.color.black));
                    tvChoiceD.setTextColor(getResources().getColor(R.color.black));

                    drawable = (GradientDrawable) rlQuestion.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pulm));

                    drawable = (GradientDrawable) tvChoiceA.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pulm));

                    drawable = (GradientDrawable) tvChoiceB.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pulm));

                    drawable = (GradientDrawable) tvChoiceC.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pulm));

                    drawable = (GradientDrawable) tvChoiceD.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.pulm));

                }
                break;
                case "af": {

                    tvChoiceA.setBackground(getResources().getDrawable(R.drawable.drawable_choice));
                    tvChoiceB.setBackground(getResources().getDrawable(R.drawable.drawable_choice));
                    tvChoiceC.setBackground(getResources().getDrawable(R.drawable.drawable_choice));
                    tvChoiceD.setBackground(getResources().getDrawable(R.drawable.drawable_choice));

                    tvChoiceA.setTextColor(getResources().getColor(R.color.black));
                    tvChoiceB.setTextColor(getResources().getColor(R.color.black));
                    tvChoiceC.setTextColor(getResources().getColor(R.color.black));
                    tvChoiceD.setTextColor(getResources().getColor(R.color.black));

                    drawable = (GradientDrawable) rlQuestion.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.seaGreen));

                    drawable = (GradientDrawable) tvChoiceA.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.seaGreen));

                    drawable = (GradientDrawable) tvChoiceB.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.seaGreen));

                    drawable = (GradientDrawable) tvChoiceC.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.seaGreen));

                    drawable = (GradientDrawable) tvChoiceD.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.seaGreen));
                }
                break;
                default: {

                    tvChoiceA.setBackground(getResources().getDrawable(R.drawable.drawable_choice));
                    tvChoiceB.setBackground(getResources().getDrawable(R.drawable.drawable_choice));
                    tvChoiceC.setBackground(getResources().getDrawable(R.drawable.drawable_choice));
                    tvChoiceD.setBackground(getResources().getDrawable(R.drawable.drawable_choice));

                    tvChoiceA.setTextColor(getResources().getColor(R.color.black));
                    tvChoiceB.setTextColor(getResources().getColor(R.color.black));
                    tvChoiceC.setTextColor(getResources().getColor(R.color.black));
                    tvChoiceD.setTextColor(getResources().getColor(R.color.black));

                    drawable = (GradientDrawable) rlQuestion.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.peach));

                    drawable = (GradientDrawable) tvChoiceA.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.peach));

                    drawable = (GradientDrawable) tvChoiceB.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.peach));

                    drawable = (GradientDrawable) tvChoiceC.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.peach));

                    drawable = (GradientDrawable) tvChoiceD.getBackground();
                    drawable.setStroke(2, getResources().getColor(R.color.peach));
                }
            }

        }
        tvChoiceA.setClickable(true);
        tvChoiceB.setClickable(true);
        tvChoiceC.setClickable(true);
        tvChoiceD.setClickable(true);

        tvChoiceC.setVisibility(View.VISIBLE);
        tvChoiceD.setVisibility(View.VISIBLE);

        pbTime.setProgress(0);
        tvTime.setText("0");
        tvScore.setText("0");

    }

    private void stopAnimations() {
        progressAnimator.cancel();
        scoreAnimator.cancel();
        timeAnimator.cancel();
        scoreTimer.cancel();
        cancelTimer();

    }

    private void setQuestionScore(int selectedAnswer) {

        if (test.getQuestions().get(i).getCorrectAnswerId() == test.getQuestions().get(i).getOptions().get(selectedAnswer).getId()) {
            test.getQuestions().get(i).setGainScore(Integer.parseInt(tvScore.getText().toString()));
            test.setTotalCorrectAnswers(test.getTotalCorrectAnswers() + 1);

        } else {
            test.getQuestions().get(i).setGainScore(Integer.parseInt("0"));
        }
    }

    public static GameplayFragment newInstance(Test test) {

        Bundle args = new Bundle();
        args.putSerializable("test", test);

        GameplayFragment fragment = new GameplayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        setToDefault();
        stopAnimations();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(isTestStarted){
            if(isHandlerActive)//checking if handler delay time is in process
                 handler.removeCallbacksAndMessages(null);//for removing the delay of handler
            stopAnimations();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isTestStarted){
            setToDefault();
            changeUi(++i);
        }
    }
}
