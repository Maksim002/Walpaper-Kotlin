package com.example.walpaper_kotlin.service;

import android.app.SearchManager;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walpaper_kotlin.R;
import com.example.walpaper_kotlin.service.model.User;
import com.example.walpaper_kotlin.service.model.WalModel;

import java.util.List;

import retrofit2.Call;

public class wdwdw extends AppCompatActivity {

    // Тестовый класс. После удалится.

    private RecyclerView recyclerView;

    private String BASE_URL = "https://api.unsplash.com/";
    private String host = "gW9sjuasObbkxughhFTiEftk-SjD7OuVSvI5aP7bRG4";
    private String key = "oqZ_uR-OVEfpANEHtz-u-DXjdR5lyGzMkBl-yKf-4dY";

    private int page = 1;
    private int per_page = 30;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }
    private void addItem(List<WalModel> list){
        for (WalModel im : list){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_i).getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        //List<WalModel> list = response.body().getResults();

        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);


    }
}
