package com.barisalgul.parselearning;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("APPLICATION ID")
                .clientKey("CLIENT KEY")
                .server("https://parseapi.back4app.com/")
                .build());
    }
}
