package com.example.user.connections;

import com.parse.ParseClassName;
import com.parse.ParseUser;

/**
 * Created by User on 25/01/2017.
 */
@ParseClassName("MyUser")
public class MyUser extends ParseUser {
    // constant keys for saving in parse
    public static final String YEAR_KEY = "year";
    public static final String MONTH_KEY = "month";
    public static final String DAY_KEY = "day";

    public MyUser() {
    }

    public int getYear() {
        return getInt(YEAR_KEY);
    }

    public void setYear(int year) {
        put(YEAR_KEY, year);
    }

    public int getMonth() {
        return getInt(MONTH_KEY);
    }

    public void setMonth(int month) {
        put(MONTH_KEY, month);
    }

    public int getDay() {
        return getInt(DAY_KEY);
    }

    public void setDay(int day) {
        put(DAY_KEY, day);
    }


}
