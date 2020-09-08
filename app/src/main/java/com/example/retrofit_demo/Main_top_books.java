package com.example.retrofit_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main_top_books extends AppCompatActivity {
    RecyclerView recyclerView;
    public static List<String> title = new ArrayList<>();
    public static List<String> author = new ArrayList<>();
    public static List<String> pages = new ArrayList<>();
    public static List<String> category = new ArrayList<>();
    public static List<String> rating = new ArrayList<>();
    public  static List<String> thumbnail=new ArrayList<>();
    Integer MaxResults=0;
    String book_title;
    EditText input_max_books,booktitle;
    Button go,clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_top_books);
        recyclerView = findViewById(R.id.rec);

       go=findViewById(R.id.go);
       clear=findViewById(R.id.again);
        input_max_books=findViewById(R.id.max_books);
        booktitle=findViewById(R.id.bookTitle);

go.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(booktitle.getText().toString().isEmpty())
        {
            booktitle.setError("enter book  title");
        }
        else if(input_max_books.getText().toString().isEmpty())
        {
            input_max_books.setError("enter no of  books" );
        }
        else
        {
            MaxResults=Integer.parseInt(input_max_books.getText().toString().trim());
            book_title=booktitle.getText().toString().trim();
            fetch_data();
        }

    }

});
clear.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        booktitle.setText("");
        input_max_books.setText("");
    }
});
    }
    private void fetch_data()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/books/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Interface_for_books interface_for_books=retrofit.create(Interface_for_books.class);
        Map <String,String> parameter=new HashMap();
        //applying queries

        parameter.put("q",book_title);
        parameter.put("maxResults",String.valueOf(MaxResults));

        Call<Example> call=interface_for_books.getdata(parameter);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful()) {
                    Example example = response.body();
                    title.clear();
                    author.clear();
                    thumbnail.clear();
                    category.clear();
                    rating.clear();
                    pages.clear();
                    for (int i = 0; i < MaxResults; i++) {
                        //title
                        title.add(example.getItems().get(i).getVolumeInfo().getTitle());
                        //author
                        author.add(example.getItems().get(i).getVolumeInfo().getAuthors().get(0));
                        //pages
                        pages.add(String.valueOf(example.getItems().get(i).getVolumeInfo().getPageCount()));
                        //category
                        category.add(example.getItems().get(i).getVolumeInfo().getCategories().get(0));
                        //rating
                        rating.add(String.valueOf(example.getItems().get(i).getVolumeInfo().getAverageRating()));
                        //thumbnails
                        thumbnail.add(example.getItems().get(i).getVolumeInfo().getImageLinks().getThumbnail());
                    }
                    //set in recycler view
                    Adapter_ adapter_=new Adapter_(Main_top_books.this,title,author,pages,category,rating,thumbnail);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(Main_top_books.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter_);
                }
                else {
                    Toast.makeText(Main_top_books.this, response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(Main_top_books.this,"error   "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}