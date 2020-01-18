package com.derrick.park.assignment3_contacts.contactlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.derrick.park.assignment3_contacts.database.ContactDao;

public class AddContactViewModelFactory implements ViewModelProvider.Factory {
    private ContactDao dataSource;
    private Application application;

    public AddContactViewModelFactory(ContactDao dataSource, Application application) {
        this.dataSource = dataSource;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AddContactViewModel.class)) {
            return (T) new AddContactViewModel(dataSource, application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
