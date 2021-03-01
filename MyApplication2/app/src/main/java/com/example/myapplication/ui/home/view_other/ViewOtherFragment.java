package com.example.myapplication.ui.home.view_other;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.adapter.SpinnerProductAdapter;
import com.example.myapplication.model.Product;
import com.example.myapplication.utils.control_volley.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ViewOtherFragment extends Fragment {

    CheckBox ckbOption1, ckbOption2;
    RadioButton radButton1, radButton2;
    TextView txtResultCheckBox, txtResultRadioButton, txtDatePicker, txtTimePicker;
    Spinner spinnerProduct;
    SpinnerProductAdapter adapterSpinner;
    List<Product> listProduct;
    RequestQueue requestQueue;
    Button btnShowDialog, btnShowDatePicker, btnShowTimePicker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_other, container, false);
        txtResultCheckBox = view.findViewById(R.id.textViewResultCheckBox);
        txtResultRadioButton = view.findViewById(R.id.textViewResultRadioButton);
        ckbOption1 = view.findViewById(R.id.checkboxOption1);
        ckbOption2 = view.findViewById(R.id.checkboxOption2);
        radButton1 = view.findViewById(R.id.radioButtonOption1);
        radButton2 = view.findViewById(R.id.radioButtonOption2);
        spinnerProduct = view.findViewById(R.id.spinnerProduct);
        btnShowDialog = view.findViewById(R.id.buttonShowDialog);
        btnShowDatePicker = view.findViewById(R.id.buttonShowDatePicker);
        txtDatePicker = view.findViewById(R.id.textViewValueDateTimePicker);
        btnShowTimePicker = view.findViewById(R.id.buttonShowTimePicker);
        txtTimePicker = view.findViewById(R.id.textViewValueTimePicker);

        listProduct = new ArrayList<>();
        adapterSpinner = new SpinnerProductAdapter(getActivity(), listProduct);
        spinnerProduct.setAdapter(adapterSpinner);

        fetchData();
        ckbOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResult();
            }
        });
        ckbOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResult();
            }
        });
        radButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                radioButtonChange();
            }
        });
        radButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                radioButtonChange();
            }
        });

        spinnerProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Hello. This is my dialog");
                builder.setPositiveButton("TODO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "HEHEHEHE", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Close !!!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        btnShowDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar myCalendar = Calendar.getInstance();

                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    }

                };


                DatePickerDialog datePicker = new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePicker.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast.makeText(getContext(), "" + datePicker.getDatePicker().getDayOfMonth(), Toast.LENGTH_SHORT).show();
                        txtDatePicker.setText(datePicker.getDatePicker().getDayOfMonth() + "/" + datePicker.getDatePicker().getMonth() + "/" + datePicker.getDatePicker().getYear());
                        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date date1=formatter1.parse(datePicker.getDatePicker().getDayOfMonth() + "/" + datePicker.getDatePicker().getMonth() + "/" + datePicker.getDatePicker().getYear());
                            //txtTimePicker.setText(date1.toString());
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                LocalDateTime localDateTime = LocalDateTime.now();
                                txtTimePicker.setText(date1.getMonth() + "");
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
                datePicker.show();
            }
        });

        btnShowTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar myCalendar = Calendar.getInstance();
                int mHour = myCalendar.get(Calendar.HOUR_OF_DAY);
                int mMinute = myCalendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                txtTimePicker.setText("Time: " + hourOfDay + " h: " + minute);
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });

        return view;
    }

    private void onItemSelectedHandler(AdapterView<?> parent, View view, int position, long id) {
        Adapter adapter = parent.getAdapter();
        Product product = (Product) adapter.getItem(position);

        Toast.makeText(getContext(), "Selected Employee: " + listProduct.get(position).getTENSANPHAM(), Toast.LENGTH_SHORT).show();
    }

    private void fetchData() {
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://mylifemrrobot.000webhostapp.com/getdata.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("TAG", response.toString());
                Gson gson = new Gson();
                Type ProductList = new TypeToken<ArrayList<Product>>() {
                }.getType();
                List<Product> listItem = gson.fromJson(String.valueOf(response), ProductList);
                listProduct.addAll(listItem);
                Log.d("TAG", listProduct.size() + " item");
                adapterSpinner.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "JsonArrayRequest onErrorResponse: " + error.getMessage());
            }
        });
        VolleySingleton.getInstance(getContext()).getRequestQueue().add(jsonArrayRequest);
    }

    private void radioButtonChange() {
        if (radButton1.isChecked()) {
            txtResultRadioButton.setText("RadioButton1 was checked");
        } else {
            txtResultRadioButton.setText("RadioButton2 was checked");
        }
    }

    private void checkResult() {
        if (ckbOption1.isChecked() && ckbOption2.isChecked()) {
            txtResultCheckBox.setText("All Checkbox was checked");
        } else if (!ckbOption1.isChecked() && !ckbOption2.isChecked()) {
            txtResultCheckBox.setText("All Checkbox was no checked");
        } else if (!ckbOption1.isChecked() && ckbOption2.isChecked()) {
            txtResultCheckBox.setText("Checkbox2 was checked");
        } else if (ckbOption1.isChecked() && !ckbOption2.isChecked()) {
            txtResultCheckBox.setText("Checkbox1 was checked");
        }
    }
}