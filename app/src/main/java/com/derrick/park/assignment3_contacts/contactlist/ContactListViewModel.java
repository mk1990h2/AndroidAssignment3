package com.derrick.park.assignment3_contacts.contactlist;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;
import com.derrick.park.assignment3_contacts.models.ContactList;
import com.derrick.park.assignment3_contacts.network.ContactClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Contact>> liveContactList;
    public ArrayList<Contact> contactList;

    public ContactListViewModel() {
        liveContactList = new MutableLiveData<>();
        contactList = new ArrayList<>();
        init();
    }

    public MutableLiveData<ArrayList<Contact>> getContactList() {
        return liveContactList;
    }

    public void init() {

        Call<ContactList> call = ContactClient.getContacts(10);
        call.enqueue(new Callback<ContactList>() {
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                if (response.isSuccessful()) {
                    ArrayList<Contact> result = response.body().getContactList();
                    contactList = createDisplayData(result);
                    liveContactList.setValue(contactList);
                }
            }

            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void add(Contact contact) {
        contactList.add(contact);
        liveContactList.setValue(contactList);
    }


    private ArrayList<Contact> createDisplayData(ArrayList<Contact> contacts) {
        Set<String> temp = new HashSet<>();

        for (int i = 0; i < contacts.size() - 1; i++) {
            String obj1 = Character.toString(contacts.get(i).getNameStr().charAt(0)).toUpperCase();
            String obj2 = Character.toString(contacts.get(i + 1).getNameStr().charAt(0)).toUpperCase();
            if (!obj1.equalsIgnoreCase(obj2)) {
                temp.add(obj1);
            }
        }
        for (String title : temp) {
            Contact contact = new Contact(title);
            contacts.add(contact);
        }

        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                if (c1.getNameStr().equals(c2.getNameStr())) {
                    return 0;
                } else if (c1.getNameStr().compareTo(c2.getNameStr()) < 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        return contacts;
    }
}
