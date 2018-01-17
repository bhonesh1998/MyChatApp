package com.example.bhonesh.e_class;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class student_register extends AppCompatActivity {
    private Firebase mref;
    EditText editname,editemail,editpass,editcpass,editbatch,editid,editdepart,edityear,editpro;

    private static final  int GALLERY_INTENT=2;
    private  StorageReference ms;
    Button saveb,upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);



        ms = FirebaseStorage.getInstance().getReference();

        upload=(Button)findViewById(R.id.upload);


        editname=(EditText)findViewById(R.id.editname);
        editemail=(EditText)findViewById(R.id.editemail);
        editpass=(EditText)findViewById(R.id.editpass);
        editcpass=(EditText)findViewById(R.id.editcpass);
        editbatch=(EditText)findViewById(R.id.editbatch);
        editid=(EditText)findViewById(R.id.editid);
        editdepart=(EditText)findViewById(R.id.editdepart);
        edityear=(EditText)findViewById(R.id.edityear);
        editpro=(EditText)findViewById(R.id.editpro);



        saveb=(Button)findViewById(R.id.signin);








        Firebase.setAndroidContext(this);


        mref=new Firebase("https://e-class-9c419.firebaseio.com/users");


upload.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent i=new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivity(i);







    }
});





        //end of making child



        saveb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                final Firebase c_name=mref.child(editid.getText().toString()).child("name");
                final Firebase c_email=mref.child(editid.getText().toString()).child("email");
                final Firebase c_password=mref.child(editid.getText().toString()).child("password");
                final Firebase c_cpassword=mref.child(editid.getText().toString()).child("confirm password");
                final Firebase c_batch=mref.child(editid.getText().toString()).child("batch");
                final Firebase c_year=mref.child(editid.getText().toString()).child("year");
                final Firebase c_department=mref.child(editid.getText().toString()).child("department");
                final Firebase c_id=mref.child(editid.getText().toString()).child("id");
                final Firebase c_pro=mref.child(editid.getText().toString()).child("pro");


                c_name.setValue(editname.getText().toString());
                c_email.setValue(editemail.getText().toString());
                c_password.setValue(editpass.getText().toString());
                c_cpassword.setValue(editcpass.getText().toString());
                c_id.setValue(editid.getText().toString());
                c_batch.setValue(editbatch.getText().toString());
                c_department.setValue(editdepart.getText().toString());
                c_year.setValue(edityear.getText().toString());
                c_pro.setValue(editpro.getText().toString());







            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)

    {
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode==RESULT_OK)
        {
            //requestcode==GALLERY_INTENT
            Uri uri = data.getData();
            StorageReference filepath= ms.child("photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    //Toast.makeText(this,"upload done",Toast.LENGTH_LONG).show();
                    Log.e("image","uploaded");



                }
            });




        }


    }

}
