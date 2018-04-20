package com.example.developer.domotic;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.developer.domotic.models.ApiRest;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    //private RecyclerView recyclerView;
    //private RecyclerView.Adapter adapter;
    //private RecyclerView.LayoutManager layoutManager;

    List<ApiRest> dataApi= new ArrayList<>();
    RecyclerView recyclerViewData;
    ApiDataAdapter adapter;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager= getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentTransaction.replace(R.id.content,new HomeFragment()).commit();
                    return true;
                case R.id.navigarion_air_conditioner:
                    fragmentTransaction.replace(R.id.content,new AcUnitFragment()).commit();
                    return true;
                case R.id.navigation_light:
                    fragmentTransaction.replace(R.id.content,new LightsFragment()).commit();
                    return true;
                case R.id.navigation_notifications:
                    fragmentTransaction.replace(R.id.content,new NotificationsFragment()).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewData=(RecyclerView)findViewById(R.id.RecyclerViewData);

        //Vistas
        //recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        //recyclerView.setHasFixedSize(true);
        //adapter =new
        //layoutManager =new GridLayoutManager(this , getSpanCount());
        //recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setAdapter(adapter);




        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //llamando al metodo quecrea la infomación
        getDataApi();

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        getDataApi();
                    }
                },300);

            }
        });

    }

    private void getDataApi(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(ApiRestInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Adapter
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerViewData.setLayoutManager(linearLayoutManager);

        adapter=new ApiDataAdapter(dataApi);
        recyclerViewData.setAdapter(adapter);

        DividerItemDecoration itemDecoration=new DividerItemDecoration(recyclerViewData.getContext(), linearLayoutManager.getOrientation());
        recyclerViewData.addItemDecoration(itemDecoration);


        ApiRestInterface service= retrofit.create(ApiRestInterface.class);
        Call<List<ApiRest>> data=service.getData();
        data.enqueue(new Callback<List<ApiRest>>() {
            @Override
            public void onResponse(Call<List<ApiRest>> call, Response<List<ApiRest>> response) {
                if (response.isSuccessful()){
                    dataApi=response.body();
                    //Log.i("Mensaje", response.body().toString());
                    //Log.i("Mensaje",response.body().toString());
                    adapter=new ApiDataAdapter(dataApi);
                    recyclerViewData.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else{
                    Log.i("Mensaje",response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<ApiRest>> call, Throwable throwable) {
                Log.i("Mensaje",throwable.toString());
            }
        });
    }

    private int getSpanCount(){
        DisplayMetrics metrics= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthScreen=metrics.widthPixels;
        float widthMinElement= TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                150,
                metrics
        );

        return (int)(widthScreen/widthMinElement);
    }

}
