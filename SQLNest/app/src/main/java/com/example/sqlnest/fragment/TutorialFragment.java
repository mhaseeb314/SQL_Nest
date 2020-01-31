package com.example.sqlnest.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.sqlnest.R;
import com.example.sqlnest.activity.MainActivity;
import com.example.sqlnest.utils.Util;

public class TutorialFragment extends Fragment implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.tvDdl: {
                TutorialOfFragment nextFragment = new TutorialOfFragment().newInstance(0);
                Util.replaceFragment(nextFragment , TutorialOfFragment.class.getSimpleName() ,
                        true ,
                        false ,
                        getActivity());
            }
            break;
            case R.id.tvDml : {
                TutorialOfFragment nextFragment = new TutorialOfFragment().newInstance(1);
                Util.replaceFragment(nextFragment , TutorialOfFragment.class.getSimpleName() ,
                        true ,
                        false ,
                        getActivity());
            }
            break;
            case  R.id.tvAf : {
                TutorialOfFragment nextFragment = new TutorialOfFragment().newInstance(2);
                Util.replaceFragment(nextFragment , TutorialOfFragment.class.getSimpleName() ,
                        true ,
                        false ,
                        getActivity());
            }
            break;
            case  R.id.tvAcf : {
                TutorialOfFragment nextFragment = new TutorialOfFragment().newInstance(3);
                Util.replaceFragment(nextFragment , TutorialOfFragment.class.getSimpleName() ,
                        true ,
                        false ,
                        getActivity());
            }
        }
    }

    TextView tvDdl;
    TextView tvDml;
    TextView tvAf;
    TextView tvAcf;
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
        return inflater.inflate(R.layout.fragment_tutorial, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbar();

        tvDdl = view.findViewById(R.id.tvDdl);
        tvDml = view.findViewById(R.id.tvDml);
        tvAf = view.findViewById(R.id.tvAf);
        tvAcf = view.findViewById(R.id.tvAcf);

        tvDdl.setOnClickListener(this);
        tvDml.setOnClickListener(this);
        tvAf.setOnClickListener(this);
        tvAcf.setOnClickListener(this);
    }

    private void setToolbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.blue));
        }
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).tvToolbar.setText(getString(R.string.tutorial));
        ((MainActivity) getActivity()).rlToolbar.setBackgroundColor(getResources().getColor(R.color.blue));
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbar();
    }
}
