package com.src.terans;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.src.start.Aboutme_Myket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Aboutme_Myket v = new Aboutme_Myket(this);
        v.setHeaderImage(R.drawable.ba1); // It can get (int) , id.mipmap/drawable/color , png,xml
        v.setBodyImage(R.drawable.ba1);// It can get (int) , id.mipmap/drawable/color , png,xml

        v.setAppIcon(R.mipmap.ic_launcher_round);
        v.setAppCompany("خلاقیت کاری و کسب درآمد"); //It can get String
        v.setAppDescription(R.string.app_desc);

        v.setProfileName("Morteza"); //It can get String
        v.setProfileJob("App Developer");//It can get String
        v.setProfileDescription(R.string.prof_desc);
        v.setProfileImage(R.drawable.profile);// It can get (int) , id.mipmap/drawable/color , png,xml

        v.setMyketActive(true);// Myket links will be active by call this method. Don't need more!
        v.addEmail("e@t.com"); // Developer's email
        v.addGithub("Mori-Hub"); // Developer's Github account
        v.addInstagram("b72243"); // Developer's Instagram account id
        v.addWhatsapp("989000004"); // Developer's Whatsapp
        v.addGoogle();// Add google play store link
        setContentView(v); // Don't forget set it !

    }
}
