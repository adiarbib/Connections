package com.example.user.connections;

import com.parse.ParseClassName;
import com.parse.ParseUser;

import java.util.Date;

/**
 * Created by User on 25/01/2017.
 */
@ParseClassName("MyUser")
public class MyUser extends ParseUser {
    // constant keys for saving in parse
    public static final String DATE_KEY = "date";

    public MyUser() {
    }

    public Date getBirthDate() {
        return getDate(DATE_KEY);
    }

    public void setBirthate(Date date) {
        put(DATE_KEY, date);
    }

}
