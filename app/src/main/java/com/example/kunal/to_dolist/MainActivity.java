package com.example.kunal.to_dolist;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    SqlDatabaseAdapter sqlhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sqlhelper=new SqlDatabaseAdapter(this);
        Cursor data= sqlhelper.getallData();
        List<Listclass> listclasses=new ArrayList<>();
        int i=0;
        if(data.getCount()!=0){
            while(data.moveToNext()){
                Listclass listclass=new Listclass(data.getString(1),data.getString(2));
                listclasses.add(i,listclass);
                i++;
            }
            recyclerView= (RecyclerView) findViewById(R.id.rec_view);

            adapter=new RecyclerAdapter(listclasses,this);
            layoutManager=new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
        else{
            Toast.makeText(MainActivity.this,"No data to show",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int res_id=item.getItemId();
        if(res_id==R.id.action_add){
            Intent intent=new Intent(MainActivity.this,SqlActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
