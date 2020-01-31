package com.example.sqlnest.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sqlnest.R;
import com.example.sqlnest.activity.LandingActivity;
import com.example.sqlnest.activity.MainActivity;
import com.example.sqlnest.activity.SplashActivity;
import com.example.sqlnest.model.User;
import com.example.sqlnest.utils.AppDatabaseInstance;

import static android.app.Activity.RESULT_OK;


public class RegisterFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnRegister: {
                checkValidations();
            }

            break;

            case R.id.rlImage: {
                getImageFromGallery();
            }

            break;
        }
    }


    EditText etName;
    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;
    ImageView ivDp;
    ImageView ivCamera;
    RelativeLayout llProgress_bar_register;
    RelativeLayout rlImage;
    RelativeLayout btnRegister;

    User user;
    String imageUrl = "";

    private final int REQUEST_CODE = 101;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViewById(view);
        setListeners();
    }

    private void setListeners() {

        rlImage.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    private void findViewById(View view) {

        etName = view.findViewById(R.id.etName);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword);
        ivDp = view.findViewById(R.id.ivDp);
        ivCamera = view.findViewById(R.id.ivCamera);
        llProgress_bar_register = view.findViewById(R.id.llProgress_bar_register);
        rlImage = view.findViewById(R.id.rlImage);
        btnRegister = view.findViewById(R.id.btnRegister);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LandingActivity) getActivity()).tvToolbar.setText(getString(R.string.register));
    }

    private void checkValidations() {

        if(etName.getText().toString().isEmpty()){
            etName.setError(getString(R.string.enter_name));
            etName.requestFocus();
        }
        else if(!isEmailValid(etEmail.getText().toString()) && !etEmail.getText().toString().isEmpty()){
            etEmail.setError(getString(R.string.invalid_email));
            etEmail.requestFocus();
        }
        else if(etPassword.getText().toString().isEmpty()){
            etPassword.setError(getString(R.string.enter_password));
            etPassword.requestFocus();
        }
        else if(etPassword.getText().toString().length() < 6){
            etPassword.setError(getString(R.string.password_error_msg));
            etPassword.requestFocus();
        }
        else if(etConfirmPassword.getText().toString().isEmpty()){
            etConfirmPassword.setError(getString(R.string.confirm_password));
            etConfirmPassword.requestFocus();
        }
        else if(!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())){
            etConfirmPassword.setError(getString(R.string.match_error_msg));
            etConfirmPassword.requestFocus();
        }
        else if(ivCamera.getVisibility() == View.VISIBLE || imageUrl.equals("")){
            Toast.makeText(getContext(),getString(R.string.select_image),Toast.LENGTH_LONG).show();
        }
        else {
            saveUserToDb();
        }
    }

    private void saveUserToDb() {

        llProgress_bar_register.setVisibility(View.VISIBLE);

        user = new User(
                etName.getText().toString(),
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                imageUrl
        );

        AppDatabaseInstance
                .getAppDatabaseInstance(getContext())
                .userDao()
                .insertUser(user);

        updateUI();
    }

    private void updateUI() {

        Intent nextActivity = new Intent(getActivity(), MainActivity.class);
        nextActivity.putExtra("user" , user);
        startActivity(nextActivity);
        llProgress_bar_register.setVisibility(View.GONE);
        getActivity().finish();
    }

    private Boolean isEmailValid(CharSequence target) {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void getImageFromGallery() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){

            if(resultCode == RESULT_OK){

                imageUrl = data.getData().toString();
                Log.i("resultURL", imageUrl);

                Glide.with(getContext())
                        .load(data.getData())
                        .apply(RequestOptions.circleCropTransform())
                        .into(ivDp);
                ivCamera.setVisibility(View.GONE);

            }else {
                Toast.makeText(getContext(), "Selecting image failed: ", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

}

