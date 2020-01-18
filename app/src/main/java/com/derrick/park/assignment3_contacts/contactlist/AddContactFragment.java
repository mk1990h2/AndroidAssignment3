package com.derrick.park.assignment3_contacts.contactlist;

import android.app.ActionBar;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.activities.MainActivity;
import com.derrick.park.assignment3_contacts.database.ContactDao;
import com.derrick.park.assignment3_contacts.database.ContactDatabase;
import com.derrick.park.assignment3_contacts.database.ContactEntity;
import com.derrick.park.assignment3_contacts.models.Contact;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddContactFragment extends Fragment {
    private Button submit;
    private EditText nameText;
    private EditText cellText;

    public AddContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        toolbar.setTitle("Add Contact");
        activity.findViewById(R.id.add_button).setVisibility(View.GONE);

        final View view = inflater.inflate(R.layout.fragment_add_contact, container, false);
        submit = view.findViewById(R.id.submit);
        nameText = view.findViewById(R.id.name_edit);
        cellText = view.findViewById(R.id.cell_edit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String phone = cellText.getText().toString();
                Contact contact = new Contact();
                if (!contact.setName(name)) {
                    Toast.makeText(getContext(), "Invalid Name: Name must have first and last name.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!contact.setCell(phone)) {
                    Toast.makeText(getContext(), "Phone must be 10 digit.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
