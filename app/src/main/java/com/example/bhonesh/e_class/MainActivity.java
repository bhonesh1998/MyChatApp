package com.example.bhonesh.e_class;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
public class MainActivity extends AppCompatActivity {
Firebase ref1,ref2,ref3;
String semail,spass;

   String s;
    EditText iemail,ipass,iid;
    Button create,signin;
    boolean a , b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);




        signin=(Button)findViewById(R.id.signin);

        iemail=(EditText)findViewById(R.id.editemail);

        ipass=(EditText)findViewById(R.id.editpass);

        iid=(EditText)findViewById(R.id.editid);

        Firebase.setAndroidContext(this);



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



               /* ProgressDialog pd = new ProgressDialog(MainActivity.this);

                pd.setTitle("yes");
                pd.setMessage("logging in");
                pd.show();*/




                DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("users");

                //DatabaseReference ref2 = ref1.child("22").child("email");
                DatabaseReference ref3 = ref1.child("22").child("password");

                DatabaseReference ref2=ref1.child("22");

                ref2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Log.e("error","data "+dataSnapshot.getValue());



                       Map<String,String> m = (Map<String, String>) dataSnapshot.getValue();
                              // dataSnapshot.getValue(Map.class);
                       String semail = m.get("email");
                       String spass=m.get("password");

                        String seditemail = iemail.getText().toString();
                        String seditpass = ipass.getText().toString();



                        if( seditpass.equals(spass) && seditemail.equals(semail) )
                        {
                          //  pd.dismiss();
                            Intent i1=new Intent("com.example.bhonesh.e_class.yes");
                            startActivity(i1);

                        }
                        else
                        {
                            //pd.dismiss();

                            Log.e("wow","error");
                            //Toast.makeText(this,"error",Toast.LENGTH_LONG).show();

                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




            /*    ref2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        semail =  dataSnapshot.getValue(String.class).toString();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("error", "onCancelled", databaseError.toException());
                    }
                });
                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        spass =  dataSnapshot.getValue(String.class).toString();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("error", "onCancelled", databaseError.toException());
                    }
                });

*/








                //in strings ko hmesha ander hi likhte h bahar likhne se null deta h


             /*   System.out.println(seditemail);
                System.out.println(seditpass);
                System.out.println(semail);
                System.out.println(spass); */






            }
        });




        create=(Button)findViewById(R.id.create);

  create.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

          Intent i1=new Intent("com.example.bhonesh.e_class.options");
          startActivity(i1);
      }
  });


    }





}
