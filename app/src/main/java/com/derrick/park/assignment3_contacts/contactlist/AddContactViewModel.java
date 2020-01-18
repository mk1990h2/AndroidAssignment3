package com.derrick.park.assignment3_contacts.contactlist;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.derrick.park.assignment3_contacts.database.ContactDao;
import com.derrick.park.assignment3_contacts.database.ContactEntity;
import com.derrick.park.assignment3_contacts.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class AddContactViewModel extends ViewModel {
    private ContactDao dao;
    private Application application;
    private LiveData<List<Contact>> contactListLiveData;

    public AddContactViewModel(ContactDao dao, Application application) {
        this.dao = dao;
        this.application = application;
    }

    public void insert(ContactEntity contact) {
        dao.insert(contact);
    }

    public List<Contact> getAll() {
        List<ContactEntity> list = dao.getAll();
        List<Contact> contacts = new ArrayList<>();
        for (ContactEntity ce : list) {
            contacts.add(new Contact(ce.firstName, ce.lastName, ce.phone));
        }
        return contacts;
    }
}
