package com.derrick.park.assignment3_contacts.contactlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.activities.MainActivity;
import com.derrick.park.assignment3_contacts.databinding.FragmentContactListBinding;
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

public class ContactListFragment extends Fragment {

    private ContactListViewModel viewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;

    public ContactListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ContactListViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final MainActivity activity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        recyclerView = view.findViewById(R.id.contact_list);
        Observer<ArrayList<Contact>> contactListUpdateObserver = new Observer<ArrayList<Contact>>() {
            @Override
            public void onChanged(ArrayList<Contact> contacts) {
                recyclerViewAdapter = new ContactListAdapter(contacts);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        };
        viewModel.getContactList().observe(this, contactListUpdateObserver);

        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        toolbar.setTitle("Contacts");
        activity.findViewById(R.id.add_button).setVisibility(View.VISIBLE);

        Button add = activity.findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(activity, R.id.nav_host_fragment).navigate(R.id.action_contactListFragment_to_addContactFragment);
            }
        });

        return view;
    }
}
