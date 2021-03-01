package com.example.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.User;
import com.example.myapplication.ui.home.HomeActivity;
import com.example.myapplication.utils.AppConstant;
import com.example.myapplication.utils.control_volley.VolleySingleton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    //====
    TextView txtContent;
    CardView cardViewLogin;
    TextInputLayout txtNumberPhone,txtPassWord;
    RequestQueue requestQueue;
    TextInputEditText txtPhone,txtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtContent = findViewById(R.id.textViewContent);
        cardViewLogin = findViewById(R.id.cardViewLogin);
        txtNumberPhone = findViewById(R.id.editTextNumberPhone);
        txtPassWord = findViewById(R.id.editTextPassword);
        txtPhone = findViewById(R.id.textInputEditTextNumberPhone);
        txtPass = findViewById(R.id.textInputEditTextPassword);
        try {
            Toast.makeText(this, "You was login at "+ readContent(), Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        cardViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberPhone = txtPhone.getText().toString();
                String password = txtPass.getText().toString();
                login(numberPhone,password);
                
            }
        });
    }

    private void login(String numberP,String pass) {
        Intent intent = new Intent(this, HomeActivity.class);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest loginRequest = new StringRequest(Request.Method.POST,"https://mylifemrrobot.000webhostapp.com/dangnhap.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG",response.toString());
                Gson gson = new Gson();
                Type List = new TypeToken<ArrayList<User>>(){}.getType();
                java.util.List<User> listUser = gson.fromJson(response, List);
                if(listUser.size()!=0) {
                    //Log.d("TAG", "User Name : " + listUser.get(0).ge() + " Password: " + listUser.get(0).getPassWord());
                    SharedPreferences sharedPref = getSharedPreferences("StatusLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("status", true);
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Go to Screen Home", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Login False", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Login false", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("SODIENTHOAI", numberP);
                params.put("MATKHAU", pass);
                return params;
            }
        };
        VolleySingleton.getInstance(this).getRequestQueue().add(loginRequest);
    }
    private String readContent() throws ParseException {
        //
        SharedPreferences sharedPref = getSharedPreferences("StatusLogin", Context.MODE_PRIVATE);
        boolean active = sharedPref.getBoolean("status", false);
        Toast.makeText(this, "Gia tri SharePreferences" + active, Toast.LENGTH_SHORT).show();
        String timeFromWelcome = "01/01/1991";
        Intent intent = getIntent();
        if(intent!=null){
            timeFromWelcome = intent.getStringExtra(AppConstant.TIME_FROM_WELCOME);
            
            txtContent.setText("You was login at : "+timeFromWelcome );
        }
        return timeFromWelcome;
    }
}