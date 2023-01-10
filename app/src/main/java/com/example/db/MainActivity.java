package com.example.db;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText name,age,contact;
Button insert,update,delete,view;
DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.Name);
        contact=findViewById(R.id.Contact);
        age=findViewById(R.id.Age);
        insert=findViewById(R.id.Insert);
        update=findViewById(R.id.Update);
        delete=findViewById(R.id.Delete);
        view=findViewById(R.id.View);
        DB=new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt=name.getText().toString();
                String contacttxt=contact.getText().toString();
                String agetxt=age.getText().toString();
                boolean status=DB.insertData(nametxt,contacttxt,agetxt);
                if(status==true)
                    Toast.makeText(MainActivity.this, "record inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "insertion Failed", Toast.LENGTH_SHORT).show();
            }

        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            String nametxt=name.getText().toString();
            String contacttxt=contact.getText().toString();
            String agetxt=age.getText().toString();
            boolean status=DB.updateData(nametxt,contacttxt,agetxt);
            if(status==true)
                Toast.makeText(MainActivity.this, "record updatded", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Updation Failed", Toast.LENGTH_SHORT).show();
        }

    });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt=name.getText().toString();

                boolean status=DB.deleteData(nametxt);
                if(status==true)
                    Toast.makeText(MainActivity.this, "record deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "deletion Failed", Toast.LENGTH_SHORT).show();
            }

        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=DB.viewData();

                if(res.getCount()==0)
                    Toast.makeText(MainActivity.this, "No such Record", Toast.LENGTH_SHORT).show();
                else
                {
                    StringBuffer buffer=new StringBuffer();
                    while(res.moveToNext())
                    {
                        buffer.append("Name :"+res.getString(0)+"\n");
                        buffer.append("Age:"+res.getString(2)+"\n");
                        buffer.append("Contact"+res.getString(1)+"\n");
                    }
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle(("User Data"));
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }

        });

    }
}