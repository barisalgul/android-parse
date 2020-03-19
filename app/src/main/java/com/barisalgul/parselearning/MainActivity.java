package com.barisalgul.parselearning;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DATABASE

        //WRITE
        ParseObject parseObject = new ParseObject("Fruits");
        parseObject.put("name", "Cucumber");
        parseObject.put("price", 2.75);
        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Object Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //GET FILTERED DATA
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Fruits");
        query.getInBackground("S6dOfhYhd8", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e != null){
                    e.printStackTrace();
                }else{
                    Toast.makeText(MainActivity.this, "Name: " + object.getString("name") + ", Price: " + object.getDouble("price"), Toast.LENGTH_LONG).show();
                }
            }
        });

        //GET DATA
        ParseQuery<ParseObject> queryCucumber = ParseQuery.getQuery("Fruits");
        //query.whereEqualTo("name","Cucumber");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    e.printStackTrace();
                }else {
                    if(objects.size()>0){

                        for (ParseObject object : objects){

                            System.out.println("Name: " + object.getString("name") + ", Price: " + object.getDouble("price"));

                        }

                    }
                }
            }
        });

        // USER

        //CREATE NEW USER(SIGN UP)
        ParseUser user = new ParseUser();
        user.setUsername("barisalgul");
        user.setPassword("123456");
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    Toast.makeText(MainActivity.this, "Signed Up!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //LOGIN
        ParseUser.logInInBackground("baris", "123456", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                } else if (user != null) {
                    Toast.makeText(MainActivity.this, "Welcome: " + user.getUsername(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
