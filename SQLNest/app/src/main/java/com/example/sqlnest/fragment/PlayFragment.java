package com.example.sqlnest.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlnest.R;
import com.example.sqlnest.activity.MainActivity;
import com.example.sqlnest.adapter.TableAdapter;
import com.example.sqlnest.model.Customer;
import com.example.sqlnest.utils.AppDatabaseInstance;

import java.util.ArrayList;


public class PlayFragment extends Fragment {

    private Bundle bundle;
    private int requestCode;
    private RecyclerView rvTable;
    private TableAdapter adapter;
    private ArrayList<Customer> customers = new ArrayList<>();

    private EditText etQuery;
    private TextView tvRunSQL, tvNumberRecord;
    private TextView tvID, tvName, tvCity, tvCountry;

    private final String SYNTAX_ERROR = "Syntax Error";
    private final String UNKNOWN_ATTRIBUTE = "Unknown Attribute";
    private final String MISSING_ATTRIBUTE = "Write At Least Attribute";

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = this.getArguments();
        requestCode = bundle.getInt("requestCode");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        setUI();
        setAdapter(true, true, true, true, true);
    }

    private void init(View view) {
        rvTable = view.findViewById(R.id.rvTable);
        etQuery = view.findViewById(R.id.etQuery);
        tvRunSQL = view.findViewById(R.id.tvRunSQL);
        tvNumberRecord = view.findViewById(R.id.tvNumberRecord);
        tvID = view.findViewById(R.id.tvID);
        tvName = view.findViewById(R.id.tvName);
        tvCity = view.findViewById(R.id.tvCity);
        tvCountry = view.findViewById(R.id.tvCountry);

        pref = getContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        editor = pref.edit();

        tvRunSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboardFrom(getContext(), etQuery);
                selectQueryCalculation();
            }
        });
    }

    private void setUI() {
        switch (requestCode) {
            case 2:
                if (pref.getString("ShowData", "").equals(""))
                    populateWithTestData(AppDatabaseInstance.getAppDatabaseInstance(getContext()));
                etQuery.setText("SELECT * FROM Customers;");
                String record = "Number of Records: " + AppDatabaseInstance.getAppDatabaseInstance(getContext()).customerDao().countCustomers();
                tvNumberRecord.setText(record);
                getAllCustomer(AppDatabaseInstance.getAppDatabaseInstance(getContext()));
                break;
        }
    }

    private void setAdapter(Boolean showStar, Boolean showID, Boolean showName, Boolean showCity, Boolean showCountry) {
        adapter = new TableAdapter(getContext(), customers, showStar, showID, showName, showCity, showCountry);
        rvTable.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManagerHome = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        rvTable.setLayoutManager(mLayoutManagerHome);
        rvTable.setAdapter(adapter);
    }

    private Customer addCustomer(final AppDatabaseInstance db, Customer customer) {
        db.customerDao().insertAll(customer);
        return customer;
    }

    private void getAllCustomer(final AppDatabaseInstance db) {
        customers = (ArrayList<Customer>) db.customerDao().getAll();
    }

    private void populateWithTestData(AppDatabaseInstance db) {
        Customer customer = new Customer("Haseeb", "Lahore", "Pakistan");
        Customer customer2 = new Customer("Moon", "Karachi", "Pakistan");
        Customer customer3 = new Customer("Haroon", "Multan", "Pakistan");
        Customer customer4 = new Customer("Nabeel", "New York", "USA");
        addCustomer(db, customer);
        addCustomer(db, customer2);
        addCustomer(db, customer3);
        addCustomer(db, customer4);
        editor.putString("ShowData","data").commit();
    }

    private void selectQueryCalculation() {
        String queryStr = etQuery.getText().toString();
        queryStr = queryStr.replaceAll(",", "");
        Log.i("PlayFragment", queryStr);

        String strArray[] = queryStr.split(" ");


        Boolean showStar = false;
        Boolean showID = false;
        Boolean showName = false;
        Boolean showCity = false;
        Boolean showCountry = false;
        Boolean done = true;

        if (strArray.length >= 4 && strArray.length <= 7) {

            for (int i = 0; i < strArray.length; i++) {
                if (i == 0) {
                    if (!strArray[i].toLowerCase().trim().equals("select")) {
                        showAlertDialog(SYNTAX_ERROR);
                        done = false;
                        break;
                    }
                } else if (i == strArray.length - 2) {
                    if (!strArray[i].toLowerCase().trim().equals("from")) {
                        showAlertDialog(SYNTAX_ERROR);
                        done = false;
                        break;
                    }
                } else if (i == strArray.length - 1) {
                    if (!strArray[i].toLowerCase().trim().equals("customers;")) {
                        showAlertDialog(SYNTAX_ERROR);
                        done = false;
                        break;
                    }
                } else {
                    Log.i("PlayFragment Star", strArray[i].toLowerCase().trim());
                    Log.i("PlayFragment Star2", strArray[i].trim());
                    if (strArray[i].toLowerCase().trim().equals("*") || strArray[i].toLowerCase().trim().equals("id") || strArray[i].toLowerCase().trim().equals("name") ||
                            strArray[i].toLowerCase().trim().equals("city") || strArray[i].toLowerCase().trim().equals("country")) {

                        if (!showStar) {
                            if (strArray[i].trim().equals("*")) {
                                showStar = true;
                            } else if (strArray[i].toLowerCase().trim().equals("id")) {
                                showID = true;
                            } else if (strArray[i].toLowerCase().trim().equals("name")) {
                                showName = true;
                            } else if (strArray[i].toLowerCase().trim().equals("city")) {
                                showCity = true;
                            } else if (strArray[i].toLowerCase().trim().equals("country")) {
                                showCountry = true;
                            }
                        } else {
                            showAlertDialog(SYNTAX_ERROR);
                            done = false;
                            break;
                        }

                    } else {
                        showAlertDialog(SYNTAX_ERROR);
                        done = false;
                        break;
                    }
                }
            }

        } else {
            showAlertDialog(SYNTAX_ERROR);
            done = false;
        }

        if (done) {
            if (showStar) {
                tvID.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                tvCity.setVisibility(View.VISIBLE);
                tvCountry.setVisibility(View.VISIBLE);
            } else {
                if (showID)
                    tvID.setVisibility(View.VISIBLE);
                else
                    tvID.setVisibility(View.GONE);

                if (showName)
                    tvName.setVisibility(View.VISIBLE);
                else
                    tvName.setVisibility(View.GONE);

                if (showCity)
                    tvCity.setVisibility(View.VISIBLE);
                else
                    tvCity.setVisibility(View.GONE);

                if (showCountry)
                    tvCountry.setVisibility(View.VISIBLE);
                else
                    tvCountry.setVisibility(View.GONE);
            }

            setAdapter(showStar, showID, showName, showCity, showCountry);
        }

        Log.i("PlayFragment", "DONE");
        //print elements of String array
        for (int i = 0; i < strArray.length; i++) {
            Log.i("PlayFragment", i + "- " + strArray[i]);
        }

    }

    public void showAlertDialog(String msg) {
        new AlertDialog.Builder(getContext())
                .setTitle("Alert")
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    public void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public PlayFragment newInstance(int requestCode) {
        PlayFragment fragment = new PlayFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("requestCode", requestCode);
        fragment.setArguments(bundle);
        return fragment;
    }
}
