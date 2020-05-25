package vn.edu.ntu.thedung.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.thedung.model.Contact;

public class ContactController extends Application implements IContactController {
    List<Contact> listContact = new ArrayList<>();

    public ContactController() {
        listContact.add(new Contact("Lê Thế Dũng","28/06/1998","0369337366","12 Trần Khánh"));
        listContact.add(new Contact("Trương Hoàng Khoa","10/06/1999","0987806758","20 Nguyễn Đình Chiểu"));
        listContact.add(new Contact("Nguyễn Nữ Ngọc Châu","11/07/1999","0979540819","06 Trần Phú"));
        listContact.add(new Contact("Nguyễn Minh Hiếu","11/01/1999","0365256332","25 Lê Lợi"));
        listContact.add(new Contact("Phạm Văn Cường","29/03/1999","0128525337","80 Quang Trung"));
        listContact.add(new Contact("Lương Đức Toàn","06/02/1999","0979541022","16 Lý Tự Trọng"));
    }

    @Override
    public List<Contact> getAllContact() {
        return listContact;
    }
}
