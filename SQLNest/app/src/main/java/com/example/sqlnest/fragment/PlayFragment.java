package com.example.sqlnest.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlnest.R;
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

                switch (requestCode) {
                    case 1:
                        insertQueryCalculation();
                        break;
                    case 2:
                        selectQueryCalculation();
                        break;
                    case 3:
                        updateQueryCalculation();
                        break;
                    case 4:
                        deleteQueryCalculation();
                        break;
                }

            }
        });
    }

    private void setUI() {
        if (pref.getString("ShowData", "").equals(""))
            populateWithTestData(AppDatabaseInstance.getAppDatabaseInstance(getContext()));
        getAllCustomer(AppDatabaseInstance.getAppDatabaseInstance(getContext()));
        String record = "Number of Records: " + AppDatabaseInstance.getAppDatabaseInstance(getContext()).customerDao().countCustomers();
        tvNumberRecord.setText(record);

        switch (requestCode) {
            case 1:
                etQuery.setText("INSERT INTO Customers (name,city,country) VALUES (\'Ali\',\'Lahore\',\'Pakistan\');");

                break;
            case 2:
                etQuery.setText("SELECT * FROM Customers;");

                break;
            case 3:
                etQuery.setText("UPDATE Customers SET name=\'Ahmed\',city=\'Lahore\' WHERE name=\'Moon\';");

                break;
            case 4:
                etQuery.setText("DELETE FROM Customers WHERE name='Haroon';");
        }
    }

    private void setAdapter(Boolean showStar, Boolean showID, Boolean showName, Boolean showCity, Boolean showCountry) {
        adapter = new TableAdapter(getContext(), customers, showStar, showID, showName, showCity, showCountry);
        rvTable.setHasFixedSize(false);
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
        editor.putString("ShowData", "data").commit();
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

    private void insertQueryCalculation() {
        String queryStr = etQuery.getText().toString();
//        queryStr = queryStr.replaceAll(",", "");
        Log.i("PlayFragment", queryStr);

        String strArray[] = queryStr.split(" ");

        Boolean done = true;
        String name = "";
        String city = "";
        String country = "";

        if (strArray.length == 6) {

            for (int i = 0; i < strArray.length; i++) {
                if (i == 0) {
                    if (!strArray[i].toLowerCase().trim().equals("insert")) {
                        showAlertDialog(SYNTAX_ERROR);
                        done = false;
                        break;
                    }
                } else if (i == 1) {
                    if (!strArray[i].toLowerCase().trim().equals("into")) {
                        showAlertDialog(SYNTAX_ERROR);
                        done = false;
                        break;
                    }
                } else if (i == 2) {
                    if (!strArray[i].toLowerCase().trim().equals("customers")) {
                        showAlertDialog(SYNTAX_ERROR);
                        done = false;
                        break;
                    }
                } else if (i == 4) {
                    if (!strArray[i].toLowerCase().trim().equals("values")) {
                        showAlertDialog(SYNTAX_ERROR);
                        done = false;
                        break;
                    }

                } else {

                    if (strArray[i].length() != 0 && strArray[i].toLowerCase().trim().charAt(0) == '(') {

                        if (i == 3 && strArray[i].toLowerCase().trim().charAt(strArray[i].length() - 1) != ')') {
                            showAlertDialog(SYNTAX_ERROR);
                            done = false;
                            break;
                        } else if (i == 5
                                && strArray[i].toLowerCase().trim().charAt(strArray[i].length() - 1) != ';'
                                && strArray[i].toLowerCase().trim().charAt(strArray[i].length() - 2) != ')') {
                            showAlertDialog(SYNTAX_ERROR);
                            done = false;
                            break;
                        }
                    } else {
                        showAlertDialog(SYNTAX_ERROR);
                        done = false;
                        break;
                    }

                    String columnsString = strArray[3].trim().replaceAll("[()]", "");
                    String columnNames[] = columnsString.split(",");

                    String valuesString = strArray[5].trim().replaceAll("[()]", "");
                    String values[] = valuesString.split(",");

                    if (columnNames.length != values.length) {
                        showAlertDialog(SYNTAX_ERROR);
                        done = false;
                        break;

                    } else if (!checkForSingleInvertedComma(values)) {
                        showAlertDialog(SYNTAX_ERROR);
                        done = false;
                        break;

                    } else {

                        for (int j = 0; j < columnNames.length; j++) {

                            if (columnNames[j].length() != 0 &&
                                    columnNames[j].trim().equalsIgnoreCase("name") ||
                                    columnNames[j].trim().equalsIgnoreCase("city") ||
                                    columnNames[j].trim().equalsIgnoreCase("country")) {

                                if (columnNames[j].trim().equalsIgnoreCase("name")) {
                                    name = values[j].replaceAll("[';]", "");
                                } else if (columnNames[j].trim().equalsIgnoreCase("city")) {
                                    city = values[j].replaceAll("[';]", "");
                                    ;
                                } else if (columnNames[j].trim().equalsIgnoreCase("country")) {
                                    country = values[j].replaceAll("[';]", "");
                                    ;
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

                }
            }

        } else {
            showAlertDialog(SYNTAX_ERROR);
            done = false;
        }

        if (done) {
            Customer newCustomer = new Customer(name, city, country);
            //add into dp
            addCustomer(AppDatabaseInstance.getAppDatabaseInstance(getContext()),
                    newCustomer);
            updateUI("Customer inserted!");
        }
    }

    private boolean checkForSingleInvertedComma(String[] values) {

        if (values.length != 0) {

            for (int i = 0; i < values.length; i++) {

                if (values[i].length() != 0 && values[i].toLowerCase().trim().charAt(0) != '\''
                        && values[i].toLowerCase().trim().charAt(values[i].length() - 1) != '\'') {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private void deleteQueryCalculation() {
        String queryStr = etQuery.getText().toString();
//        queryStr = queryStr.replaceAll(",", "");
        Log.i("PlayFragment", queryStr);

        String strArray[] = queryStr.split(" ");

        String name = "";
        String city = "";
        String country = "";
        int id = 0;

        if (strArray.length == 3) {

            for (int i = 0; i < strArray.length; i++) {
                if (i == 0) {
                    if (!strArray[i].toLowerCase().trim().equals("delete")) {
                        showAlertDialog(SYNTAX_ERROR);
                        break;
                    }
                } else if (i == 1) {
                    if (!strArray[i].toLowerCase().trim().equals("from")) {
                        showAlertDialog(SYNTAX_ERROR);
                        break;
                    }
                } else {
                    if (!strArray[i].toLowerCase().trim().equals("customers;")) {
                        showAlertDialog(SYNTAX_ERROR);
                        break;
                    } else {
                        AppDatabaseInstance.getAppDatabaseInstance(getContext()).customerDao().deleteAllUsers();
                        updateUI("All records deleted!");
                        break;
                    }
                }

            }
        } else if (strArray.length == 5) {
            for (int i = 0; i < strArray.length; i++) {
                if (i == 0) {
                    if (!strArray[i].toLowerCase().trim().equals("delete")) {
                        showAlertDialog(SYNTAX_ERROR);
                        break;
                    }
                } else if (i == 1) {
                    if (!strArray[i].toLowerCase().trim().equals("from")) {
                        showAlertDialog(SYNTAX_ERROR);
                        break;
                    }
                } else if (i == 2) {
                    if (!strArray[i].toLowerCase().trim().equals("customers")) {
                        showAlertDialog(SYNTAX_ERROR);
                        break;
                    }
                } else if (i == 3) {
                    if (!strArray[i].toLowerCase().trim().equals("where")) {
                        showAlertDialog(SYNTAX_ERROR);
                        break;
                    }
                } else {

                    String columnToDelete[] = strArray[i].trim().split("=");

                    if (columnToDelete.length == 2)
                        if (!columnToDelete[0].trim().equalsIgnoreCase("id") &&
                                columnToDelete[1].length() >= 3 &&
                                columnToDelete[1].charAt(0) != '\'' &&
                                columnToDelete[1].charAt(columnToDelete[1].length() - 2) != '\'') {
                            showAlertDialog(SYNTAX_ERROR);
                            break;

                        } else if (columnToDelete[0].length() != 0 && strArray[i].charAt(strArray[i].length() - 1) == ';' &&
                                columnToDelete[0].trim().equalsIgnoreCase("name") ||
                                columnToDelete[0].trim().equalsIgnoreCase("city") ||
                                columnToDelete[0].trim().equalsIgnoreCase("country") ||
                                columnToDelete[0].trim().equalsIgnoreCase("id")) {

                            if (columnToDelete[0].trim().equalsIgnoreCase("name")) {
                                name = columnToDelete[1].replaceAll("[';]", "");
                                AppDatabaseInstance.getAppDatabaseInstance(getContext()).customerDao().deleteByName(name);
                                updateUI("Deleted");
                                break;
                            } else if (columnToDelete[0].trim().equalsIgnoreCase("city")) {
                                city = columnToDelete[1].replaceAll("[';]", "");
                                AppDatabaseInstance.getAppDatabaseInstance(getContext()).customerDao().deleteByCity(city);
                                updateUI("Deleted");
                                break;
                            } else if (columnToDelete[0].trim().equalsIgnoreCase("country")) {
                                country = columnToDelete[1].replaceAll("[';]", "");
                                AppDatabaseInstance.getAppDatabaseInstance(getContext()).customerDao().deleteByCountry(country);
                                updateUI("Deleted");
                                break;
                            } else if (columnToDelete[0].trim().equalsIgnoreCase("id")) {

                                if (columnToDelete[1].trim().contains("\'")) {
                                    showAlertDialog(SYNTAX_ERROR);
                                    break;
                                } else {
                                    id = Integer.parseInt(columnToDelete[1].trim().replaceAll("[;]", ""));
                                    AppDatabaseInstance.getAppDatabaseInstance(getContext()).customerDao().deleteById(id);
                                    updateUI("Deleted");
                                    break;
                                }

                            } else {
                                showAlertDialog(SYNTAX_ERROR);
                                break;
                            }

                        } else {
                            showAlertDialog(SYNTAX_ERROR);
                            break;
                        }
                    else{
                        showAlertDialog(SYNTAX_ERROR);
                        break;
                    }

                }
            }

        } else {
            showAlertDialog(SYNTAX_ERROR);
        }
    }

    private void updateUI(String s) {
        String record = "Number of Records: " + AppDatabaseInstance.getAppDatabaseInstance(getContext()).customerDao().countCustomers();
        tvNumberRecord.setText(record);
        getAllCustomer(AppDatabaseInstance.getAppDatabaseInstance(getContext()));
        setAdapter(false, true, true, true, true);
        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
    }

    private void updateQueryCalculation() {
        String queryStr = etQuery.getText().toString();
//        queryStr = queryStr.replaceAll(",", "");
        Log.i("PlayFragment", queryStr);

        String strArray[] = queryStr.split(" ");

        boolean cityToUpdate = false;
        boolean nameToUpdate = false;
        boolean countryToUpdate = false;
        String updatedName = "";
        String updatedCity = "";
        String updatedCountry = "";

        if (strArray.length == 6) {

            ArrayList<Customer> updatedCustomers = new ArrayList<Customer>();

            for (int i = 0; i < strArray.length; i++) {
                if (i == 0) {
                    if (!strArray[i].toLowerCase().trim().equals("update")) {
                        showAlertDialog(SYNTAX_ERROR);
                        break;
                    }
                } else if (i == 1) {
                    if (!strArray[i].toLowerCase().trim().equals("customers")) {
                        showAlertDialog(SYNTAX_ERROR);
                        break;
                    }
                } else if (i == 2) {
                    if (!strArray[i].toLowerCase().trim().equals("set")) {
                        showAlertDialog(SYNTAX_ERROR);
                        break;
                    }
                } else if (i == 4) {
                    if (!strArray[i].toLowerCase().trim().equals("where")) {
                        showAlertDialog(SYNTAX_ERROR);
                        break;
                    }

                } else {

                    String columnToUpdate[] = strArray[3].trim().split(",");
                    String rowToUpdate[] = strArray[5].trim().split("=");

                    for (String s : columnToUpdate) {
                        if (!s.equals("") &&
                                (s.trim().split("=")[0].equalsIgnoreCase("name") &&
                                        checkForSingleInvertedComma(new String[]{s.trim().split("=")[1]})) ||

                                (s.trim().split("=")[0].equalsIgnoreCase("city") &&
                                        checkForSingleInvertedComma(new String[]{s.trim().split("=")[1]})) ||

                                (s.trim().split("=")[0].equalsIgnoreCase("country") &&
                                        checkForSingleInvertedComma(new String[]{s.trim().split("=")[1]}))) {

                            if (s.trim().split("=")[0].equalsIgnoreCase("name")) {
                                nameToUpdate = true;
                                updatedName = s.trim().split("=")[1]
                                        .replaceAll("[']", "");
                            } else if (s.trim().split("=")[0].equalsIgnoreCase("city")) {
                                cityToUpdate = true;
                                updatedCity = s.trim().split("=")[1]
                                        .replaceAll("[']", "");
                            } else if (s.trim().split("=")[0].equalsIgnoreCase("country")) {
                                countryToUpdate = true;
                                updatedCountry = s.trim().split("=")[1]
                                        .replaceAll("[']", "");
                            } else {
                                showAlertDialog(SYNTAX_ERROR);
                                return;
                            }

                        } else {
                            showAlertDialog(SYNTAX_ERROR);
                            return;
                        }
                    }

                    if (i == 5) {
                        if (!rowToUpdate[0].trim().equalsIgnoreCase("id") &&
                                rowToUpdate[1].charAt(0) != '\'' &&
                                rowToUpdate[1].charAt(rowToUpdate[1].length() - 2) != '\'') {
                            showAlertDialog(SYNTAX_ERROR);
                            return;

                        } else if (rowToUpdate[0].length() != 0 && strArray[i].charAt(strArray[i].length() - 1) == ';' &&
                                rowToUpdate[0].trim().equalsIgnoreCase("name") ||
                                rowToUpdate[0].trim().equalsIgnoreCase("city") ||
                                rowToUpdate[0].trim().equalsIgnoreCase("country") ||
                                rowToUpdate[0].trim().equalsIgnoreCase("id")) {

                            if (rowToUpdate[0].trim().equalsIgnoreCase("name")) {
                                updatedCustomers = (ArrayList<Customer>) AppDatabaseInstance
                                        .getAppDatabaseInstance(getContext())
                                        .customerDao()
                                        .getCustomerByName(rowToUpdate[1]
                                                .replaceAll("[';]", ""));

                            } else if (rowToUpdate[0].trim().equalsIgnoreCase("city")) {
                                updatedCustomers = (ArrayList<Customer>) AppDatabaseInstance
                                        .getAppDatabaseInstance(getContext())
                                        .customerDao()
                                        .getCustomerByCity(rowToUpdate[1]
                                                .replaceAll("[';]", ""));

                            } else if (rowToUpdate[0].trim().equalsIgnoreCase("country")) {
                                updatedCustomers = (ArrayList<Customer>) AppDatabaseInstance
                                        .getAppDatabaseInstance(getContext())
                                        .customerDao()
                                        .getCustomerByCountry(rowToUpdate[1]
                                                .replaceAll("[';]", ""));

                            } else if (rowToUpdate[0].trim().equalsIgnoreCase("id")) {
                                updatedCustomers = (ArrayList<Customer>) AppDatabaseInstance
                                        .getAppDatabaseInstance(getContext())
                                        .customerDao()
                                        .getCustomerById(Integer.parseInt(rowToUpdate[1]
                                                .replaceAll("[;]", "")));

                            } else {
                                showAlertDialog(SYNTAX_ERROR);
                                return;
                            }

                        } else {
                            showAlertDialog(SYNTAX_ERROR);
                            return;
                        }

                        updateColumnAccordingly(
                                AppDatabaseInstance.getAppDatabaseInstance(getContext()),
                                nameToUpdate,
                                cityToUpdate,
                                countryToUpdate,
                                updatedCustomers,
                                new String[]{updatedName,
                                        updatedCity,
                                        updatedCountry});
                    }

                }
            }

        } else if (strArray.length == 4) {

            String columnToUpdate[] = strArray[3].trim().split(",");

            if (columnToUpdate[columnToUpdate.length - 1]
                    .charAt(columnToUpdate[columnToUpdate.length - 1].length() - 1) == ';') {

                for (String s : columnToUpdate) {
                    if (!s.equals("") &&
                            (s.trim().split("=")[0].equalsIgnoreCase("name") &&
                                    checkForSingleInvertedComma(new String[]{s.trim().split("=")[1]})) ||

                            (s.trim().split("=")[0].equalsIgnoreCase("city") &&
                                    checkForSingleInvertedComma(new String[]{s.trim().split("=")[1]})) ||

                            (s.trim().split("=")[0].equalsIgnoreCase("country") &&
                                    checkForSingleInvertedComma(new String[]{s.trim().split("=")[1]}))) {

                        if (s.trim().split("=")[0].equalsIgnoreCase("name")) {
                            AppDatabaseInstance
                                    .getAppDatabaseInstance(getContext())
                                    .customerDao()
                                    .updateAllNames(
                                            s.trim().split("=")[1]
                                                    .replaceAll("[';]", ""));
                            updateUI("All records updated!");
                        } else if (s.trim().split("=")[0].equalsIgnoreCase("city")) {
                            AppDatabaseInstance
                                    .getAppDatabaseInstance(getContext())
                                    .customerDao()
                                    .updateAllCity(
                                            s.trim().split("=")[1]
                                                    .replaceAll("[';]", ""));
                            updateUI("All records updated!");
                        } else if (s.trim().split("=")[0].equalsIgnoreCase("country")) {
                            AppDatabaseInstance
                                    .getAppDatabaseInstance(getContext())
                                    .customerDao()
                                    .updateAllCountry(
                                            s.trim().split("=")[1]
                                                    .replaceAll("[';]", ""));
                            updateUI("All records updated!");
                        } else {
                            showAlertDialog(SYNTAX_ERROR);
                            return;
                        }

                    } else {
                        showAlertDialog(SYNTAX_ERROR);
                        return;
                    }
                }

            } else {
                showAlertDialog(SYNTAX_ERROR);
                return;
            }

        } else {
            showAlertDialog(SYNTAX_ERROR);
        }

    }

    private void updateColumnAccordingly(
            AppDatabaseInstance db,
            Boolean name,
            Boolean city,
            Boolean country,
            ArrayList<Customer> customerToUpdate,
            String columnValues[]) {

        for (int i = 0; i < customerToUpdate.size(); i++) {

            if (name) {
                customerToUpdate.get(i).setName(columnValues[0]);
            }
            if (city) {
                customerToUpdate.get(i).setCity(columnValues[1]);
            }
            if (country) {
                customerToUpdate.get(i).setCountry(columnValues[2]);
            }

            addCustomer(db, customerToUpdate.get(i));
        }

        updateUI("Record/s Updated!");

    }
}