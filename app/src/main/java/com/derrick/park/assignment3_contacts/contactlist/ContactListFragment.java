package com.derrick.park.assignment3_contacts.contactlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.activities.MainActivity;
import com.derrick.park.assignment3_contacts.databinding.FragmentContactListBinding;


public class ContactListFragment extends Fragment {

    private ContactListViewModel viewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

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
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        MainActivity activity = (MainActivity) getActivity();

        recyclerView = view.findViewById(R.id.contact_list);
        recyclerView.setAdapter(new ContactListAdapter(activity.getmContactList()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }


}
