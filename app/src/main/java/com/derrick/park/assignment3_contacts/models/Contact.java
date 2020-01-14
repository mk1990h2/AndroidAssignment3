package com.derrick.park.assignment3_contacts.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {

    public Contact(String name, String cell) {
        this.name = createName(name);
        this.cell = cell;
    }

    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("cell")
    @Expose
    private String cell;

    public Name getName() {
        return name;
    }

    public String getNameStr() {
        return name.toString();
    }

    public String getCell() {
        return cell;
    }

    public boolean validateName(String name) {
        return true;
    }

    private Name createName(String name) {
        String[] splitName = name.split(" ");
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
        public Location(String street, String city, String province, String postcode) {
            this.street = street;
            this.city = city;
            this.province = province;
            this.postcode = postcode;
        }

        @SerializedName("street")
        @Expose
        private String street;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private String province;
        @SerializedName("postcode")
        @Expose
        private String postcode;

        public String getStreet() {
            return street;
        }

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
            return street + ", " + city + ", " + province + " Canada " + postcode;
        }
    }
}

