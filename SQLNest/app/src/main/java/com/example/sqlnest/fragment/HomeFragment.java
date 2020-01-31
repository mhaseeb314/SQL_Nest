package com.example.sqlnest.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sqlnest.R;
import com.example.sqlnest.activity.MainActivity;
import com.example.sqlnest.utils.Util;


public class HomeFragment extends Fragment implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvTutorial) {

            TutorialFragment tutorialFragment = new TutorialFragment();
            Util.replaceFragment(tutorialFragment, TutorialFragment.class.getSimpleName(),
                    true, false,
                    getActivity());

        } else if (v.getId() == R.id.tvQuiz) {

            OperationsFragment operationsFragment = new OperationsFragment();
            Util.replaceFragment(operationsFragment, OperationsFragment.class.getSimpleName(),
                    true, false,
                    getActivity());

        }
    }

    private TextView tvTutorial;
    private TextView tvQuiz;
    public LinearLayout llUserInfo;
    public ImageView ivUserDp;
    public TextView tvUserName;
    Window window;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        window = getActivity().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTutorial = view.findViewById(R.id.tvTutorial);
        tvQuiz = view.findViewById(R.id.tvQuiz);
        llUserInfo = view.findViewById(R.id.llUserInfo);
        tvUserName = view.findViewById(R.id.tvUserName);
        ivUserDp = view.findViewById(R.id.ivUserDp);

        tvTutorial.setOnClickListener(this);
        tvQuiz.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbar();
    }


    private void setToolbar() {
        ((MainActivity) getActivity()).toolbar.setVisibility(View.GONE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.blue));
        }
        tvUserName.setText(((MainActivity) getActivity()).user.getName());
        Glide.with(getContext())
                .load(((MainActivity) getActivity()).user.getAvatar())
                .apply(RequestOptions.circleCropTransform())
                .into(ivUserDp);

    }
}
