package com.example.sqlnest.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sqlnest.R;
import com.example.sqlnest.activity.LandingActivity;
import com.example.sqlnest.activity.MainActivity;
import com.example.sqlnest.model.User;
import com.example.sqlnest.utils.AppDatabaseInstance;
import com.example.sqlnest.utils.Util;

public class LoginFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSignIn: {
                checkValidations();
            }

            break;

            case R.id.tvSignUp: {
                RegisterFragment registerFragment = new RegisterFragment().newInstance();
                Util.replaceFragment(registerFragment , RegisterFragment.class.getSimpleName() ,
                        true ,
                        false ,
                        getActivity());
            }
            break;
        }
    }

    EditText etEmail;
    EditText etPassword;
    RelativeLayout llProgress_bar;
    RelativeLayout btnSignIn;
    TextView tvRegister;

    User myUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViewById(view);
        setListeners();
    }

    private void findViewById(View view) {

        etEmail = view.findViewById(R.id.etEmailLogin);
        etPassword = view.findViewById(R.id.etPasswordLogin);
        llProgress_bar = view.findViewById(R.id.llProgress_bar);
        btnSignIn = view.findViewById(R.id.btnSignIn);
        tvRegister = view.findViewById(R.id.tvSignUp);
    }

    private void setListeners() {

        btnSignIn.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    private Boolean isEmailValid(CharSequence target) {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    private void checkForPassword() {
        if (myUser.getPassword().equals(etPassword.getText().toString())){
            updateUI();
        }else{
            llProgress_bar.setVisibility(View.GONE);
            etPassword.setError(getString(R.string.wrong_password_msg));
            etPassword.requestFocus();
        }
    }

    private void updateUI() {

        Intent nextActivity = new Intent(getActivity(), MainActivity.class);
        nextActivity.putExtra("user" , myUser);
        startActivity(nextActivity);
        llProgress_bar.setVisibility(View.GONE);
        getActivity().finish();
    }

    private void checkValidations() {
        if (!isEmailValid(etEmail.getText().toString()) && !etEmail.getText().toString().isEmpty()) {
            etEmail.setError(getString(R.string.invalid_email));
            etEmail.requestFocus();
        } else if (etPassword.getText().toString().isEmpty()) {
            etPassword.setError(getString(R.string.enter_password));
            etPassword.requestFocus();
        } else if (etPassword.getText().toString().length() < 6) {
            etPassword.setError(getString(R.string.password_error_msg));
            etPassword.requestFocus();
        } else {
            checkIfEmailExistsInDb();
        }

    }

    private void checkIfEmailExistsInDb() {
        new GetUserFromEmail().execute(etEmail.getText().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LandingActivity) getActivity()).tvToolbar.setText(getString(R.string.login));
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    public class GetUserFromEmail extends AsyncTask<String, Integer, User> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            llProgress_bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected User doInBackground(String... strings) {
            return AppDatabaseInstance
                    .getAppDatabaseInstance(getContext())
                    .userDao()
                    .findUserByEmail(strings[0]);

        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);

            myUser = user;

            if(myUser != null){
                checkForPassword();
            }else {
                llProgress_bar.setVisibility(View.GONE);
                etEmail.setError(getString(R.string.email_error));
                etEmail.requestFocus();
            }
        }
    }
}
