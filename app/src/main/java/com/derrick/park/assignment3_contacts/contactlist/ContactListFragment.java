package com.derrick.park.assignment3_contacts.contactlist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
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
    private ArrayList<Contact> mContactList;

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
        final View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        final MainActivity activity = (MainActivity) getActivity();
        FragmentContactListBinding binding = FragmentContactListBinding.inflate(inflater);
        binding.setLifecycleOwner(this);

        Call<ContactList> call = ContactClient.getContacts(10);
        call.enqueue(new Callback<ContactList>() {
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                if (response.isSuccessful()) {
                    mContactList = response.body().getContactList();
                    ArrayList<Contact> displayData = createDisplayData(mContactList);
                    recyclerView = view.findViewById(R.id.contact_list);
                    recyclerView.setAdapter(new ContactListAdapter(displayData));
                }
            }

            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {
                // Error Handling
            }
        });

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
