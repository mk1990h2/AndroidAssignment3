package com.derrick.park.assignment3_contacts.contactlist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.derrick.park.assignment3_contacts.models.Contact;

import java.util.ArrayList;

public class ContactListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Contact>> liveContactList;
    public ArrayList<Contact> contactList;

    public ContactListViewModel() {
        liveContactList = new MutableLiveData<>();
        contactList = new ArrayList<>();
    }

    public MutableLiveData<ArrayList<Contact>> getContactList() {
        return liveContactList;
    }

    public void init() {
        generateList();
        liveContactList.setValue(contactList);
    }

    public void add(Contact contact) {
        contactList.add(contact);
        liveContactList.setValue(contactList);
    }

    public void generateList() {
        // TODO: test data
        contactList.add(new Contact("aaa bbb", "000-000-0000"));
    }
}
