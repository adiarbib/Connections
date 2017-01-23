package com.example.user.connections;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by User on 23/01/2017.
 */

public class MyApplication extends Application
{
    public static final String APPLICATION_ID = "Zaog9zQM6tmhZjZszen6ClyRy6MiqDWtOPwsDZd5";
    public static final String CLIENT_KEY = "3bV8eOVUPBIGS9zN6Xc5r0wcvJ5qpyEt9GBR3cdG";
    public static final String SERVER = "https://parseapi.back4app.com/";

    @Override
    public void onCreate() {
        super.onCreate();
        initParse();
    }

    public void initParse() {
        //ParseObject.registerSubclass(MyEvent.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(APPLICATION_ID)
                .clientKey(CLIENT_KEY)
                .server(SERVER)
                .build()
        );
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseUser.enableAutomaticUser();
    }
}
