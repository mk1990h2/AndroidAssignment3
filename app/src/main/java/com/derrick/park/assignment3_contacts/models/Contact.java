package com.derrick.park.assignment3_contacts.models;

import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {

    public Contact() {
    }

    public Contact(String index) {
        this.index = index;
        this.name = new Name(index, index);
    }

    public Contact(String first, String last, String cell) {
        this.name = new Name(first, last);
        this.cell = cell;
    }

    private String index;

    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("cell")
    @Expose
    private String cell;

    public String getIndex() {
        return index;
    }

    public Name getName() {
        return name;
    }

//    public boolean setName(String name) {
//        if (validateName(name)) {
//            return false;
//        }
//        this.name = createName(name);
//        return true;
//    }

    public String getNameStr() {
        return name != null ? name.toString() : "";
    }

    public String getCell() {
        return cell;
    }

//    public boolean setCell(String cell) {
//        if (validateCell(cell)) {
//            return false;
//        }
//        this.cell = cell;
//        return true;
//    }

    public boolean validateName(String name) {
        if (name == null || name.length() == 0) {
            return false;
        }
        String[] splitName = name.split(" ");
        if (splitName.length < 2) {
            return false;
        }

        return true;
    }

    public boolean validateCell(String cell) {
        String regex = "^[0-9]*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(cell);
        if (cell.length() != 10 || !m.find()) {
            return false;
        }
        return true;
    }

        private Name createName(String name) {
        String[] splitName = name.split(" ", 2);
        return new Name(splitName[0], splitName[1]);
    }

    @Override
    public String toString() {
        return String.format("%s%s", name, cell);
    }


    /**
     * Name {first: , last: }
     */
    class Name {
        public Name(String first, String last) {
            this.first = first;
            this.last = last;
        }

        @SerializedName("first")
        @Expose
        private String first;
        @SerializedName("last")
        @Expose
        private String last;

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }

        @Override
        public String toString() {
            return first + " " + last;
        }
    }

    /**
     * Location {street: , city: , state: , postcode: }
     */
    class Location {
        public Location(String city, String province, String postcode) {
            this.city = city;
            this.province = province;
            this.postcode = postcode;
        }

        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private String province;
        @SerializedName("postcode")
        @Expose
        private String postcode;

        public String getCity() {
            return city;
        }

        public String getProvince() {
            return province;
        }

        public String getPostcode() {
            return postcode;
        }

        @Override
        public String toString() {
            return city + ", " + province + " Canada " + postcode;
        }
    }
}

