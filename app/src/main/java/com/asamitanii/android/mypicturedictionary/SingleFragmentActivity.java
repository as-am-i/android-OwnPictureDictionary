package com.asamitanii.android.mypicturedictionary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

/**
 *
 * Created by tanii_asami on 2/2/18.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        ParseObject.registerSubclass(User.class);
        ParseObject.registerSubclass(Word.class);
//        ParseObject.registerSubclass(Tag.class);
        ParseObject.registerSubclass(List.class);
        ParseObject.registerSubclass(Meaning.class);

        // initialize the app with Parse
        Parse.initialize(this);

        // save the current installation to Back4App
        ParseInstallation.getCurrentInstallation().saveInBackground();

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        // create and add a fragment if it doesn't exist
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}
