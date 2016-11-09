package com.example.kunal.to_dolist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class SqlActivity extends AppCompatActivity {
    SqlDatabaseAdapter sqlhelper;
    EditText edittitle,editdetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        sqlhelper=new SqlDatabaseAdapter(this);
        edittitle=(EditText)findViewById(R.id.editName);
        editdetails=(EditText)findViewById(R.id.editMarks);

    }

    public void addStudent(View view){

        String title=edittitle.getText().toString();
        String details=editdetails.getText().toString();
        if(title.equals("")|| details.equals("")){
            Toast.makeText(this,"Data not inserted",Toast.LENGTH_LONG).show();
            return;
        }
        long id=sqlhelper.insertdata(title,details);

        if(id<0 ){
            Toast.makeText(this,"Data not inserted",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Data inserted",Toast.LENGTH_LONG).show();
        }
        edittitle.setText("");
        editdetails.setText("");
    }

    public void viewData(View view){
        Cursor res=sqlhelper.getallData();
        if(res.getCount()==0){
            showMessage("Error","No data found");
            return;
        }
        StringBuffer stringBuffer=new StringBuffer();
        while(res.moveToNext()){
            stringBuffer.append("ID: "+res.getString(0)+"\n");
            stringBuffer.append("Title: "+res.getString(1)+"\n");
            stringBuffer.append("Details: "+res.getString(2)+"\n\n");
        }
        showMessage("To-Do List",stringBuffer.toString());
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(SqlActivity.this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}

