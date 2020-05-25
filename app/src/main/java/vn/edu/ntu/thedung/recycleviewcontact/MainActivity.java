package vn.edu.ntu.thedung.recycleviewcontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.thedung.controller.IContactController;
import vn.edu.ntu.thedung.model.Contact;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvListContact;
    IContactController controller;
    ContactApdapter apdapter;
    List<Contact> listContact = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnu_mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itemAdd:
                showAddContact();
                break;
            case R.id.itemExit:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAddContact(){
        Intent intent = new Intent(MainActivity.this,EditActivity.class);
        startActivity(intent);
    }

    private void AddViews(){
        rvListContact = findViewById(R.id.rvListContact);
        rvListContact.setLayoutManager(new LinearLayoutManager(this));
        controller = (IContactController) getApplication();
        listContact = controller.getAllContact();
        apdapter = new ContactApdapter(listContact);
        rvListContact.setAdapter(apdapter);
    }

    private class ContactApdapter extends RecyclerView.Adapter<ContactViewHolder>{

        List<Contact> listContacts = new ArrayList<>();

        public ContactApdapter(List<Contact> listContacts) {
            this.listContacts = listContacts;
        }

        @NonNull
        @Override
        public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.contact_layout,parent,false);
            return new ContactViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactViewHolder holder, final int position) {
            holder.bind(listContacts.get(position));
            holder.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    intent.putExtra("position",new Integer(position).toString());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return listContacts.size();
        }
    }

    private class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtDateOfBirth, txtPhoneNumber;
        ImageView btnEdit;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtDateOfBirth = itemView.findViewById(R.id.txtDateOfBirth);
            txtPhoneNumber = itemView.findViewById(R.id.txtPhoneNumber);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }

        public void bind(Contact c){
            txtName.setText(c.getName());
            txtDateOfBirth.setText(c.getDateOfBirth());
            txtPhoneNumber.setText(c.getPhoneNumber());
        }

    }


}
