package com.example.sqlnest.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.sqlnest.R;
import com.example.sqlnest.activity.MainActivity;


public class TutorialOfFragment extends Fragment {

    WebView webView;
    int value;
    Window window;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        value = getArguments().getInt("value");
        window = getActivity().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        Context contextThemeWrapper;
//        LayoutInflater localInflater;
//
//        switch (value) {
//
//            case 0: {
//                // create ContextThemeWrapper from the original Activity Context with the custom theme
//                contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.DDLTheme);
//                // clone the inflater using the ContextThemeWrapper
//                localInflater = inflater.cloneInContext(contextThemeWrapper);
//            }
//            break;
//            case 1: {
//                // create ContextThemeWrapper from the original Activity Context with the custom theme
//                contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.DMLTheme);
//                // clone the inflater using the ContextThemeWrapper
//                localInflater = inflater.cloneInContext(contextThemeWrapper);
//            }
//            break;
//            case 2: {
//                // create ContextThemeWrapper from the original Activity Context with the custom theme
//                contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AfTheme);
//                // clone the inflater using the ContextThemeWrapper
//                localInflater = inflater.cloneInContext(contextThemeWrapper);
//            }
//            break;
//            default: {
//                // create ContextThemeWrapper from the original Activity Context with the custom theme
//                contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AcfTheme);
//                // clone the inflater using the ContextThemeWrapper
//                localInflater = inflater.cloneInContext(contextThemeWrapper);
//            }
//        }
        return inflater.inflate(R.layout.fragment_tutorial_of, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        webView = view.findViewById(R.id.wvDDL);

        setToolbarAccordingly();
        setWebViewAccordingly();
    }

    private void setWebViewAccordingly() {

        switch (value) {

            case 0: {
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("file:///android_asset/ddl.html");
            }
            break;
            case 1: {
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("file:///android_asset/dml.html");
            }
            break;
            case 2: {
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("file:///android_asset/af.html");
            }
            break;
            case 3: {
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("file:///android_asset/acf.html");
            }
        }

    }

    private void setToolbarAccordingly() {

        switch (value) {

            case 0: {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.pink));
                }
                ((MainActivity) getActivity()).tvToolbar.setText(getString(R.string.ddl));
                ((MainActivity) getActivity()).rlToolbar.setBackgroundColor(getResources().getColor(R.color.pink));
            }
            break;
            case 1: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.pulm));
                }
                ((MainActivity) getActivity()).tvToolbar.setText(getString(R.string.dml));
                ((MainActivity) getActivity()).rlToolbar.setBackgroundColor(getResources().getColor(R.color.pulm));
            }
            break;
            case 2: {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.seaGreen));
                }
                ((MainActivity) getActivity()).tvToolbar.setText(getString(R.string.af));
                ((MainActivity) getActivity()).rlToolbar.setBackgroundColor(getResources().getColor(R.color.seaGreen));
            }
            break;
            case 3: {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.peach));
                }
                ((MainActivity) getActivity()).tvToolbar.setText(getString(R.string.acf));
                ((MainActivity) getActivity()).rlToolbar.setBackgroundColor(getResources().getColor(R.color.peach));
            }
        }

    }

    public static TutorialOfFragment newInstance(int value) {

        Bundle args = new Bundle();
        TutorialOfFragment fragment = new TutorialOfFragment();

        args.putInt("value", value);
        fragment.setArguments(args);
        return fragment;
    }


}
