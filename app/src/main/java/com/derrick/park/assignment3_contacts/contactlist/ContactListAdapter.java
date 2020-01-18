package com.derrick.park.assignment3_contacts.contactlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;


import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Contact> contactList;
    private int ITEM_VIEW_TYPE_INDEX = 0;
    private int ITEM_VIEW_TYPE_ITEM = 1;

    public ContactListAdapter(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (getItemViewType(i) == ITEM_VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_contact_item, viewGroup, false);
            ContactListViewHolder vh = new ContactListViewHolder(view);
            return vh;
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_contact_index, viewGroup, false);
            IndexViewHolder vh = new IndexViewHolder(view);
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Contact contact = contactList.get(i);
        if (getItemViewType(i) == ITEM_VIEW_TYPE_INDEX) {
            IndexViewHolder ivh = (IndexViewHolder) viewHolder;
            ivh.bind(contact);
        } else  {
            ContactListViewHolder clvh = (ContactListViewHolder) viewHolder;
            clvh.bind(contact);
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (contactList.get(position).getIndex() == null) {
            return ITEM_VIEW_TYPE_ITEM;
        }
        return ITEM_VIEW_TYPE_INDEX;
    }

    class ContactListViewHolder extends RecyclerView.ViewHolder {
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

    class IndexViewHolder extends RecyclerView.ViewHolder {
        TextView titleTV;

        public IndexViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.index);
        }

        public void bind(Contact contact) {
            titleTV.setText(contact.getIndex());
        }
    }
}
