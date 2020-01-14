package com.derrick.park.assignment3_contacts.contactlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;
import com.derrick.park.assignment3_contacts.models.ContactList;

import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder> {
    private ArrayList<Contact> contactList;

    public static class ContactListViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV;
        TextView cellTV;

        public ContactListViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.name);
            cellTV = itemView.findViewById(R.id.cell);
        }

        public void bind(Contact contact) {
            nameTV.setText(contact.getNameStr());
            cellTV.setText(contact.getCell());
        }
    }

    public ContactListAdapter() {
        contactList = new ArrayList<>();
    }

    public ContactListAdapter(ArrayList<Contact> contactList) {
        this();
        if (contactList != null) {
            this.contactList = contactList;
        }
    }

    @NonNull
    @Override
    public ContactListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_contact_item, viewGroup, false);
        ContactListViewHolder vh = new ContactListViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListViewHolder viewHolder, int i) {
        Contact contact = contactList.get(i);
        viewHolder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

}
