package com.src.start;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Aboutme_Myket extends LinearLayout {
    String alpha_package;
    Context alpa_context;
    String versionName;
    @SuppressLint("SetTextI18n")
    public Aboutme_Myket(Context context) {
        super(context);
         initialize(context);
        alpa_context=context;
        PackageManager manager = alpa_context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(alpa_context.getPackageName(), PackageManager.GET_ACTIVITIES);
            versionName=info.versionName;
            alpha_package=info.packageName;
//            Toast.makeText(alpa_context,
//                    "PackageName = " + info.packageName + "\nVersionCode = "
//                            + info.versionCode + "\nVersionName = "
//                            + info.versionName + "\nPermissions = " + info.permissions, Toast.LENGTH_LONG).show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        TextView textView= findViewById(R.id.textView);
        String app_name = alpa_context.getApplicationInfo().loadLabel(alpa_context.getPackageManager()).toString();


        ImageView imageView= findViewById(R.id.imageView);
        int s= alpa_context.getApplicationInfo().icon ;
        imageView.setImageResource(s);


//        String versionName = BuildConfig.VERSION_NAME;

        
        textView.setText(app_name+" "+versionName);
    }

    public Aboutme_Myket(Context context, AttributeSet attrs) {
        super(context, attrs);
         initialize(context);
    }
    private void initialize(Context context) {
        inflate(context, R.layout.my_view, this);
    }

    public void setHeaderImage(int s){
       View imageView= findViewById(R.id.view);
        imageView.setBackgroundResource(s);

    }

    public void setBodyImage(int s){
        View imageView= findViewById(R.id.body);
        imageView.setBackgroundResource(s);
    }
    public void setAppIcon(int s){
        ImageView imageView= findViewById(R.id.imageView);
        imageView.setImageResource(s);
    }
    public void setAppCompany(String s){
        TextView textView= findViewById(R.id.textView2);
        textView.setText(s);
    }
    public void setAppDescription(int s){
        TextView textView= findViewById(R.id.textView3);
        textView.setText(s);
    }

    public void setProfileImage(int s){
        ImageView imageView2= findViewById(R.id.imageView2);
        imageView2.setImageResource(s);
    }
    public void setProfileName(String s){
        TextView textView= findViewById(R.id.textView4);
        textView.setText(s);
    }
    public void setProfileJob(String s){
        TextView textView= findViewById(R.id.textView5);
        textView.setText(s);
    }
    public void setProfileDescription(int s){
        TextView textView= findViewById(R.id.textView6);
        textView.setText(s);
    }


    public void setMyketActive(){
        Button btnDown= findViewById(R.id.btnDown);
        Button btnShare= findViewById(R.id.btnShare);
        Button btnCom= findViewById(R.id.btnCom);
        Button btnHelp= findViewById(R.id.btnHelp);

        btnDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               String url="myket://download/"+alpha_package;
                alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
        btnShare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://myket.ir/app/"+alpha_package;
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "دوستان دانلود کنید");
                share.putExtra(Intent.EXTRA_TEXT, url);

                alpa_context.startActivity(Intent.createChooser(share, "Share link!"));

            }
        });
        btnCom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="myket://comment?id="+alpha_package;
                alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
        btnHelp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="myket://details?id="+alpha_package;
                alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });

    }

    public void addGithub(final String github){
        Button btn= findViewById(R.id.btnGithub);

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://github.com/"+github;
                alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
    }
    public void addEmail(final String email){
        Button btn= findViewById(R.id.btnEmail);

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",email, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                alpa_context.startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
    }
    public void addInstagram(final String id){
        Button btn= findViewById(R.id.btnInsta);

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.instagram.com/"+id;
                alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
    }
    public void addWhatsapp(final String phoneN){

        Button btn= findViewById(R.id.btnWat);

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://wa.me/"+phoneN;
                alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
    }

    public Intent onIntent(String url){
        return new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    }


}
