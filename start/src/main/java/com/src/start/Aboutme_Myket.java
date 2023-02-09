package com.src.start;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;


public class Aboutme_Myket extends RelativeLayout {
    String alpha_package;
    Context alpa_context;
    String versionName;
    String urlbtnDown = "https://", urlbtnShare = "https://", urlbtnHelp = "https://", urlbtnCom = "https://";


    public Aboutme_Myket(Context context) {
        super(context);
        initialize(context);
        alpa_context = context;
        PackageManager manager = alpa_context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(alpa_context.getPackageName(), PackageManager.GET_ACTIVITIES);
            versionName = info.versionName;
            alpha_package = info.packageName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        TextView textView = findViewById(R.id.textView);
        String app_name = alpa_context.getApplicationInfo().loadLabel(alpa_context.getPackageManager()).toString();


        ImageView imageView = findViewById(R.id.imageView);
        int s = alpa_context.getApplicationInfo().icon;
        imageView.setImageResource(s);


        app_name = app_name + " " + versionName;
        textView.setText(app_name);

    }

    public Aboutme_Myket(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(Context context) {
        inflate(context, R.layout.my_view, this);

        ImageButton btnDown = findViewById(R.id.btnDown);
        ImageButton btnShare = findViewById(R.id.btnShare);
        ImageButton btnCom = findViewById(R.id.btnCom);
        ImageButton btnHelp = findViewById(R.id.btnHelp);




        btnDown.setOnClickListener(v -> {
            anim(btnDown);

//            String urlbtnDown = "myket://download/" + alpha_package;
            try {
                alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlbtnDown)));
            } catch (ActivityNotFoundException e) {
                Log.e("TAG", "setMyketActive: " + e.getLocalizedMessage());
            }
        });
        btnShare.setOnClickListener(v -> {
            anim(btnShare);

            // String urlbtnShare = "https://myket.ir/app/" + alpha_package;
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            share.putExtra(Intent.EXTRA_SUBJECT, "دوستان دانلود کنید");
            share.putExtra(Intent.EXTRA_TEXT, urlbtnShare);

            alpa_context.startActivity(Intent.createChooser(share, "Share link!"));

        });
        btnCom.setOnClickListener(v -> {
            anim(btnCom);

            // String urlbtnCom = "myket://comment?id=" + alpha_package;
            try {
                alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlbtnCom)));
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        });
        btnHelp.setOnClickListener(v -> {
            anim(btnHelp);

            // String urlbtnHelp = "myket://details?id=" + alpha_package;
            try {
                alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlbtnHelp)));

            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }

        });
    }

    public void setHeaderImage(int s) {
        View imageView = findViewById(R.id.view);
        imageView.setBackgroundResource(s);

    }

    public void setBodyImage(int s) {
        View imageView = findViewById(R.id.body);
        imageView.setBackgroundResource(s);
    }

    public void setAppIcon(int s) {
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(s);
    }

    public void setAppCompany(String s) {
        TextView textView = findViewById(R.id.textView2);
        textView.setText(s);
    }

    public void setAppDescription(int s) {
        TextView textView = findViewById(R.id.textView3);
        textView.setText(s);
    }

    public void setProfileImage(int s) {
        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setImageResource(s);
    }

    public void setProfileName(String s) {
        TextView textView = findViewById(R.id.textView4);
        textView.setText(s);
    }

    public void setProfileJob(String s) {
        TextView textView = findViewById(R.id.textView5);
        textView.setText(s);
    }

    public void setProfileDescription(int s) {
        TextView textView = findViewById(R.id.textView6);
        textView.setText(s);
    }


    public void setMyketActive(boolean active) {
        if (!active) {
            if (getTag() == "land")
                findViewById(R.id.s2).setVisibility(GONE);
            else {
                findViewById(R.id.divider2).setVisibility(GONE);
                findViewById(R.id.myket_icon).setVisibility(GONE);
                findViewById(R.id.myket_table).setVisibility(GONE);

            }

        }
        ImageView logo = findViewById(R.id.myket_icon);
        logo.setImageResource(R.drawable.myket_128_b);
        urlbtnDown = "myket://download/" + alpha_package;
        urlbtnShare = "https://myket.ir/app/" + alpha_package;
        urlbtnHelp = "myket://details?id=" + alpha_package;
        urlbtnCom = "myket://comment?id=" + alpha_package;


    }

    public void addGithub(final String github) {
        ImageButton btn = findViewById(R.id.btnGithub);
        btn.setVisibility(VISIBLE);
        btn.setOnClickListener(v -> {
            anim(btn);

            String url = "https://github.com/" + github;
            alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        });
    }

    public void addEmail(final String email) {
        ImageButton btn = findViewById(R.id.btnEmail);
        btn.setVisibility(VISIBLE);
        btn.setOnClickListener(v -> {
            anim(btn);

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", email, null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
            alpa_context.startActivity(Intent.createChooser(emailIntent, "Send email..."));
        });
    }

    public void addInstagram(final String id) {
        ImageButton btn = findViewById(R.id.btnInsta);
        btn.setVisibility(VISIBLE);
        btn.setOnClickListener(v -> {
            anim(btn);

            String url = "https://www.instagram.com/" + id;
            alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        });
    }

    public void addGoogle(boolean active) {
        ImageButton btn = findViewById(R.id.btnGoogle);
        if (active)
            btn.setVisibility(VISIBLE);
        btn.setOnClickListener(v -> {
            anim(btn);

            String url = "https://play.google.com/store/apps/details?id=" + alpha_package;
            try {
                alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }

        });
    }

    public void addWhatsapp(final String phoneN) {

        ImageButton btn = findViewById(R.id.btnWat);
        btn.setVisibility(VISIBLE);
        btn.setOnClickListener(v -> {
            anim(btn);

            String url = "https://wa.me/" + phoneN;
            alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        });
    }

    public void addGoogleRateApp(Context mContext) {
        ImageButton btn = findViewById(R.id.btnGRate);
        btn.setVisibility(VISIBLE);
        btn.setOnClickListener(v -> {
            anim(btn);

            try {
                ReviewManager manager = ReviewManagerFactory.create(mContext);
                manager.requestReviewFlow().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ReviewInfo reviewInfo = task.getResult();
                        manager.launchReviewFlow((Activity) mContext, reviewInfo).addOnFailureListener(e -> Toast.makeText(mContext, "Rating Failed", Toast.LENGTH_SHORT).show()).addOnCompleteListener(task1 -> Toast.makeText(mContext, "Review Completed, Thank You!", Toast.LENGTH_SHORT).show());
                    }

                }).addOnFailureListener(e -> Toast.makeText(mContext, "In-App Request Failed", Toast.LENGTH_SHORT).show());
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void anim(ImageButton btn) {
        btn.startAnimation(
                AnimationUtils.loadAnimation(
                        alpa_context,
                        R.anim.scale_short
                ));

    }

    public void addCafe(boolean active) {
        ImageView logo = findViewById(R.id.myket_icon);
        if (active) {
            logo.setImageResource(R.drawable.cafe);
            findViewById(R.id.divider2).setVisibility(VISIBLE);
            findViewById(R.id.myket_icon).setVisibility(VISIBLE);
            findViewById(R.id.myket_table).setVisibility(VISIBLE);

            urlbtnDown = "bazaar://download/" + alpha_package;
            urlbtnShare = "https://cafebazaar.ir/app/" + alpha_package;
            urlbtnHelp = "bazaar://details?id=" + alpha_package;
            urlbtnCom = "bazaar://comment?id=" + alpha_package;
        }
    }

    public void addStores(boolean googlePlay, boolean cafeBazar, boolean mayKet) {
        ImageView logo = findViewById(R.id.myket_icon);

        if (googlePlay) {
            findViewById(R.id.divider2).setVisibility(GONE);
            findViewById(R.id.myket_icon).setVisibility(GONE);
            findViewById(R.id.myket_table).setVisibility(GONE);
            addGoogleRateApp(alpa_context);
            addGoogle(true);
        }


        if (cafeBazar) {
            logo.setImageResource(R.drawable.cafe);

            addGoogle(false);
            setMyketActive(false);
            addCafe(true);
        }
        if (mayKet) {
            logo.setImageResource(R.drawable.myket_128_b);

            addCafe(false);
            addGoogle(false);
            setMyketActive(true);
        }


    }

    public void dia(Aboutme_Myket v){
         AlertDialog.Builder builder = new AlertDialog.Builder(alpa_context);
        builder.setView(v);//dialogView
        AlertDialog alertDialog = builder.create();
        findViewById(R.id.btnClose).setVisibility(GONE);
        findViewById(R.id.btnClose3).setVisibility(GONE);
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }
    public ImageButton closeWindow(){
        return findViewById(R.id.btnClose);
    }
/*
    public Intent onIntent(String url){
        return new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    }

*/
}
