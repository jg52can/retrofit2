package com.example.myretrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myretrofitapp.Models.UserModel;
import com.example.myretrofitapp.Retrofit.ApiClient;
import com.example.myretrofitapp.Retrofit.ApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface;

    @BindView(R.id.editxt_email) EditText editTextEmail;
    @BindView(R.id.editxt_password) EditText editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

    }

    public  void loginUser(View v)
    {
        Call<UserModel> userModelCall = apiInterface.loginUser(editTextEmail.getText().toString(),
                editTextPassword.getText().toString());

        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if(response.body()!=null){
                    UserModel userModel = response.body();

                    if(userModel.isSuccess()){
                        Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Not found " +userModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error, could not connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public  void registerUser(View v)
    {
        startActivity(new Intent(MainActivity.this, Register.class));
    }

}