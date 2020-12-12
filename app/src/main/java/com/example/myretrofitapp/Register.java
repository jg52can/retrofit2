package com.example.myretrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myretrofitapp.Models.UserModel;
import com.example.myretrofitapp.Retrofit.ApiClient;
import com.example.myretrofitapp.Retrofit.ApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    @BindView(R.id.editxtreg_email) EditText editTextRegEmail;
    @BindView(R.id.editxtreg_password) EditText editTextRegPassword;
    @BindView(R.id.editxtreg_phone) EditText editTextRegPhone;
    @BindView(R.id.editxtreg_name) EditText editTextRegName;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void registerUser(View v){
        String name=editTextRegName.getText().toString();
                String email=editTextRegEmail.getText().toString();
                        String password=editTextRegPassword.getText().toString();
                                String phone=editTextRegPhone.getText().toString();

        UserModel userModel  = new UserModel(name,email,phone,password);
        Call<UserModel> callRegister = apiInterface.registerUser(name,email
                ,phone
                ,password
                );

        callRegister.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.isSuccessful() && response.body()!=null){
                    UserModel userModel = response.body();
                    if(userModel.isSuccess()){
                        Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Register.this, "User could not be registered", Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(Register.this, "Error, could not connect", Toast.LENGTH_SHORT).show();
            }
        });


    }
}