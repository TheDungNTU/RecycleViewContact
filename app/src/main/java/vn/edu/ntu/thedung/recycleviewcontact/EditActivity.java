package vn.edu.ntu.thedung.recycleviewcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.thedung.controller.IContactController;
import vn.edu.ntu.thedung.model.Contact;

public class EditActivity extends AppCompatActivity {

    EditText edtID, edtName, edtBirthOfDate, edtPhoneNumber, edtAddress;
    Button btnSave, btnCancel;
    List<Contact> listContacts = new ArrayList<>();
    IContactController controller;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        addViews();
        addEvents();
    }

    private void addViews(){
        edtID = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);
        edtBirthOfDate = findViewById(R.id.edtDateOfBirth);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtAddress = findViewById(R.id.edtAddress);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        controller = (IContactController) getApplication();
        listContacts = controller.getAllContact();

        Intent intent = getIntent();
        if (intent.getStringExtra("position") != null){
            position = Integer.parseInt(intent.getStringExtra("position"));
            edtID.setText(new Integer(position).toString());
            edtName.setText(listContacts.get(position).getName());
            edtBirthOfDate.setText(listContacts.get(position).getDateOfBirth());
            edtPhoneNumber.setText(listContacts.get(position).getPhoneNumber());
            edtAddress.setText(listContacts.get(position).getAddress());
        }
        else{
            edtID.setText(new Integer(listContacts.size()).toString());
        }
    }

    private void addEvents(){

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EditActivity.this,MainActivity.class);

                if (position == -1){
                    Contact c = new Contact();
                    c.setName(edtName.getText().toString());
                    c.setDateOfBirth(edtBirthOfDate.getText().toString());
                    c.setPhoneNumber(edtPhoneNumber.getText().toString());
                    c.setAddress(edtAddress.getText().toString());
                    listContacts.add(c);
                    startActivity(intent);

                }
                else {
                    Contact c = new Contact();
                    c.setName(edtName.getText().toString());
                    c.setDateOfBirth(edtBirthOfDate.getText().toString());
                    c.setPhoneNumber(edtPhoneNumber.getText().toString());
                    c.setAddress(edtAddress.getText().toString());
                    listContacts.set(position,c);
                    startActivity(intent);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
