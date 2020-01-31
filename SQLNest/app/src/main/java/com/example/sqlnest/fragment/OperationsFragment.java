package com.example.sqlnest.fragment;


import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.example.sqlnest.R;
import com.example.sqlnest.activity.MainActivity;
import com.example.sqlnest.utils.Util;


public class OperationsFragment extends Fragment implements View.OnClickListener {

    Window window;
    private TextView tvInsert;
    private TextView tvSelect;
    private TextView tvUpdate;
    private TextView tvDelete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        window = getActivity().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_operations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbar();
        tvInsert = view.findViewById(R.id.tvInsert);
        tvSelect = view.findViewById(R.id.tvSelect);
        tvUpdate = view.findViewById(R.id.tvUpdate);
        tvDelete = view.findViewById(R.id.tvDelete);

        tvInsert.setOnClickListener(this);
        tvSelect.setOnClickListener(this);
        tvUpdate.setOnClickListener(this);
        tvDelete.setOnClickListener(this);

    }

    private void setToolbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.red));
        }
        ((MainActivity) getActivity()).toolbar.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).tvToolbar.setText(getString(R.string.operations));
        ((MainActivity) getActivity()).rlToolbar.setBackgroundColor(getResources().getColor(R.color.red));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.tvInsert){
            callPlayFragment(1);
        }
        else if(view.getId() == R.id.tvSelect){
            callPlayFragment(2);
        }
        else if(view.getId() == R.id.tvUpdate){
            callPlayFragment(3);
        }
        else if(view.getId() == R.id.tvDelete){
            callPlayFragment(4);
        }
    }

    private void callPlayFragment(int requestCode){
        PlayFragment playFragment = new PlayFragment().newInstance(requestCode);
        Util.replaceFragment(playFragment, PlayFragment.class.getSimpleName(), true , false, getActivity());
    }

}
