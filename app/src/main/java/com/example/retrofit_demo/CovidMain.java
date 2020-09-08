package com.example.retrofit_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView textView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_main);
textView=findViewById(R.id.text_for_covid);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://disease.sh/v3/covid-19/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Interface_for_covid interface_for_covid=retrofit.create(Interface_for_covid.class);
        Call<Pojoclass_for_covid> call=interface_for_covid.getData();
        call.enqueue(new Callback<Pojoclass_for_covid>() {
            @Override
            public void onResponse(Call<Pojoclass_for_covid>call, Response<Pojoclass_for_covid> response) {
                if(response.isSuccessful())
                {
                    Pojoclass_for_covid pojoclass_for_covid= (Pojoclass_for_covid) response.body();

                    StringBuilder stringBuilder=new StringBuilder();
                    stringBuilder.append("cases :  ").append(pojoclass_for_covid.getCases()).append("\n");
                    stringBuilder.append("deaths   ").append(pojoclass_for_covid.getDeaths()).append("\n");
                    stringBuilder.append("today cases   ").append(pojoclass_for_covid.getTodayCases()).append("\n");
                    stringBuilder.append("critical   ").append(pojoclass_for_covid.getCritical()).append("\n");
                    stringBuilder.append("today deaths   ").append(pojoclass_for_covid.getTodayDeaths()).append("\n");
                    stringBuilder.append("recovered   :   ").append(pojoclass_for_covid.getRecovered()).append("\n");
                    textView.setText(String.valueOf(stringBuilder));
                }
                else
                {
                    Toast.makeText(CovidMain.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pojoclass_for_covid> call, Throwable t) {
                Toast.makeText(CovidMain.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}