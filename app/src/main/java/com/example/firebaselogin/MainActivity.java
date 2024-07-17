package com.example.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    Button btnLogout;
    TextView userName;


    EditText detailOneEdt;
    TextView detailOneTxt;
    Button detailOneBtn;

    EditText detailTwoEdt;
    TextView detailTwoTxt;
    Button detailTwoBtn;

    EditText detailThreeEdt;
    TextView detailThreeTxt;
    Button detailThreeBtn;

    FirebaseFirestore db;



    private boolean detailOneToggle=false;
    private boolean detailTwoToggle=false;
    private boolean detailThreeToggle=false;

    private String detailOneFirestore;
    private String detailTwoFirestore;
    private String detailThreeFirestore;

    public String getDetailOneFirestore() {
        return detailOneFirestore;
    }

    public void setDetailOneFirestore(String detailOneFirestore) {
        this.detailOneFirestore = detailOneFirestore;
    }

    public String getDetailTwoFirestore() {
        return detailTwoFirestore;
    }

    public void setDetailTwoFirestore(String detailTwoFirestore) {
        this.detailTwoFirestore = detailTwoFirestore;
    }

    public String getDetailThreeFirestore() {
        return detailThreeFirestore;
    }

    public void setDetailThreeFirestore(String detailThreeFirestore) {
        this.detailThreeFirestore = detailThreeFirestore;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        userName=findViewById(R.id.userName);
        detailOneBtn=findViewById(R.id.detailOneBtn);
        detailTwoBtn=findViewById(R.id.detailTwoBtn);
        detailThreeBtn=findViewById(R.id.detailThreeBtn);
        if (user==null){
            Intent intent=new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();
        }else{
            userName.setText(user.getEmail());
        }
        db = FirebaseFirestore.getInstance();
        btnLogout=findViewById(R.id.btnLogout);

        detailOneEdt=findViewById(R.id.detailOneEdt);
        detailTwoEdt=findViewById(R.id.detailTwoEdt);
        detailThreeEdt=findViewById(R.id.detailThreeEdt);

        detailOneTxt=findViewById(R.id.detailOneTxt);
        detailTwoTxt=findViewById(R.id.detailTwoTxt);
        detailThreeTxt=findViewById(R.id.detailThreeTxt);


        CollectionReference users=db.collection("users");


        if(db!=null){
            String uid=user.getUid();
            DocumentReference userDocRef=users.document(uid);

            detailOneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!detailOneToggle){
                        detailOneToggle=true;
                        detailOneEdt.setVisibility(View.VISIBLE);
                        detailOneTxt.setVisibility(View.GONE);
                        detailOneBtn.setText("Set");
                        detailOneEdt.setText(getDetailOneFirestore());
                    }else{
                        detailOneToggle=false;
                        detailOneEdt.setVisibility(View.GONE);
                        detailOneTxt.setVisibility(View.VISIBLE);
                        detailOneBtn.setText("Edit");
                        Map<String,Object> detailOneObj=new HashMap<>();
                        detailOneObj.put("detailOne",detailOneEdt.getText().toString());
                        userDocRef.update(detailOneObj).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    setDetailOneFirestore(detailOneEdt.getText().toString());
                                    detailOneTxt.setText(detailOneEdt.getText().toString());
                                }else{
                                    Toast.makeText(MainActivity.this, "Error updating", Toast.LENGTH_SHORT).show();
                                }

                            }

                        });
                    }
                }
            });
            detailTwoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!detailTwoToggle){
                        detailTwoToggle=true;
                        detailTwoEdt.setVisibility(View.VISIBLE);
                        detailTwoTxt.setVisibility(View.GONE);
                        detailTwoBtn.setText("Set");
                        detailTwoEdt.setText(getDetailTwoFirestore());
                    }else{
                        detailTwoToggle=false;
                        detailTwoEdt.setVisibility(View.GONE);
                        detailTwoTxt.setVisibility(View.VISIBLE);
                        detailTwoBtn.setText("Edit");
                        Map<String,Object> detailTwoObj=new HashMap<>();
                        detailTwoObj.put("detailTwo",detailTwoEdt.getText().toString());
                        userDocRef.update(detailTwoObj).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    setDetailTwoFirestore(detailTwoEdt.getText().toString());
                                    detailTwoTxt.setText(detailTwoEdt.getText().toString());
                                }else{
                                    Toast.makeText(MainActivity.this, "Error updating", Toast.LENGTH_SHORT).show();
                                }

                            }

                        });
                    }
                }
            });
            detailThreeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!detailThreeToggle){
                        detailThreeToggle=true;
                        detailThreeEdt.setVisibility(View.VISIBLE);
                        detailThreeTxt.setVisibility(View.GONE);
                        detailThreeBtn.setText("Set");
                        detailThreeEdt.setText(getDetailThreeFirestore());
                    }else{
                        detailThreeToggle=false;
                        detailThreeEdt.setVisibility(View.GONE);
                        detailThreeTxt.setVisibility(View.VISIBLE);
                        detailThreeBtn.setText("Edit");
                        Map<String,Object> detailThreeObj=new HashMap<>();
                        detailThreeObj.put("detailThree",detailThreeEdt.getText().toString());
                        userDocRef.update(detailThreeObj).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    setDetailThreeFirestore(detailThreeEdt.getText().toString());
                                    detailThreeTxt.setText(detailThreeEdt.getText().toString());
                                }else{
                                    Toast.makeText(MainActivity.this, "Error updating", Toast.LENGTH_SHORT).show();
                                }

                            }

                        });
                    }
                }
            });


            userDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();

                        if (document.exists()) {
                            setDetailOneFirestore(document.getData().get("detailOne").toString());
                            detailOneTxt.setText((getDetailOneFirestore().isEmpty()?"Not Set":getDetailOneFirestore()));
                            detailOneEdt.setText(detailOneFirestore);

                            setDetailTwoFirestore(document.getData().get("detailTwo").toString());
                            detailTwoTxt.setText((getDetailTwoFirestore().isEmpty()?"Not Set":getDetailTwoFirestore()));
                            detailTwoEdt.setText(detailTwoFirestore);

                            setDetailThreeFirestore(document.getData().get("detailThree").toString());
                            detailThreeTxt.setText((getDetailThreeFirestore().isEmpty()?"Not Set":getDetailThreeFirestore()));
                            detailThreeEdt.setText(detailThreeFirestore);


                        } else {
                            Log.d("FIRESTORE", "No such document");
                        }
                    } else {
                        Log.d("FIRESTORE", "get failed with ", task.getException());
                    }
                }
            });
        }
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}