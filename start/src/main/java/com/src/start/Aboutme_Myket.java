package com.src.start;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.text.LineBreaker;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class Aboutme_Myket extends RelativeLayout {
    String alpha_package;
    Context alpa_context;
    String versionName;
    String urlbtnDown = "https://", urlbtnShare = "https://", urlbtnHelp = "https://", urlbtnCom = "https://";
    String storePack = "", storeDeveloperID = "";
    ImageButton btnDown, btnShare, btnCom, btnHelp,btnClose;

    public Aboutme_Myket(Context context) {
        super(context);
        initialize(context);
        alpa_context = context;
        PackageManager manager = alpa_context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(alpa_context.getPackageName(), PackageManager.GET_ACTIVITIES);
            versionName = info.versionName;
            alpha_package = info.packageName;//

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

        btnDown = findViewById(R.id.btnDown);
        btnShare = findViewById(R.id.btnShare);
        btnCom = findViewById(R.id.btnCom);
        btnHelp = findViewById(R.id.btnHelp);
        btnClose=findViewById(R.id.btnClose);

        btnClose.setVisibility(INVISIBLE);

//download --> All Hear
        btnDown.setOnClickListener(v -> {
            anim(btnDown);

//            String urlbtnDown = "myket://download/" + alpha_package;
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urlbtnDown));
                if (!Objects.equals(storePack, ""))
                    intent.setPackage(storePack);
                alpa_context.startActivity(intent);
                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlbtnDown)));
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
        //ارجاع به صفحه نظرات
        btnCom.setOnClickListener(v -> {
            anim(btnCom);

            // String urlbtnCom = "myket://comment?id=" + alpha_package;
            try {
                if (!Objects.equals(storePack, "")) {
                    Intent intent = new Intent(Intent.ACTION_EDIT);
                    intent.setData(Uri.parse(urlbtnCom));

                    intent.setPackage(storePack);
                    alpa_context.startActivity(intent);
                } else
                    alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlbtnCom)));
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        });
        //ارجاع به برنامه
        btnHelp.setOnClickListener(v -> {
            anim(btnHelp);

            // String urlbtnHelp = "myket://details?id=" + alpha_package;
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urlbtnHelp));
                if (!Objects.equals(storePack, ""))
                    intent.setPackage(storePack);
                alpa_context.startActivity(intent);
                // .startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlbtnHelp)));

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

    public void setAppDescription(boolean justifyText, int s) {
        TextView textView = findViewById(R.id.textView3);
        textView.setText(s);
        if (justifyText)
          justifyText(textView);
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

    public void setProfileDescription(boolean justifyText,int s) {
        TextView textView = findViewById(R.id.textView6);
        textView.setText(s);
        if(justifyText)
         justifyText(textView);
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

            String url = "https://play.google.com/store/apps/developer?id=Smart+rabit";
            try {
                alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
            //"https://play.google.com/store/apps/details?id=" + alpha_package;
            //https://play.google.com/store/search?q=pub:"+"Smart rabite"
            //https://play.google.com/store/apps/developer?id=Smart+rabit
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

                }).addOnFailureListener(
                        e -> openGoogleStore()

                );
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void openGoogleStore() {
        Toast.makeText(alpa_context, "In-App Request Failed", Toast.LENGTH_SHORT).show();

        String url = "https://play.google.com/store/apps/details?id=" + alpha_package;
        try {
            alpa_context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void anim(ImageButton btn) {
        btn.startAnimation(
                AnimationUtils.loadAnimation(
                        alpa_context,
                        R.anim.scale_short
                ));

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
        urlbtnDown = "myket://developer/" + alpha_package;//myket://download/
        urlbtnShare = "https://myket.ir/app/" + alpha_package;
        urlbtnHelp = "myket://details?id=" + alpha_package;
        urlbtnCom = "myket://comment?id=" + alpha_package;
        // storePack="ir.meservices.market";
        checkName(alpa_context, "ir.meservices.market");

    }

    public void addCafe(boolean active, String storeDeveloperID) {
        ImageView logo = findViewById(R.id.myket_icon);
        if (active) {
            logo.setImageResource(R.drawable.cafe);
            findViewById(R.id.divider2).setVisibility(VISIBLE);
            findViewById(R.id.myket_icon).setVisibility(VISIBLE);
            findViewById(R.id.myket_table).setVisibility(VISIBLE);
            urlbtnDown = "bazaar://collection?slug=by_author&aid=" + storeDeveloperID; //ارجاع به برنامه‌های توسعه‌دهنده
            urlbtnShare = "https://cafebazaar.ir/app/" + alpha_package;
            urlbtnHelp = "bazaar://details?id=" + alpha_package; //ارجاع به برنامه
            urlbtnCom = "bazaar://details?id=" + alpha_package;// ارجاع به صفحه نظرات
            storePack = "com.farsitel.bazaar";
        }
    }

    public void addCafe(boolean active) {
        ImageView logo = findViewById(R.id.myket_icon);
        if (active) {
            logo.setImageResource(R.drawable.cafe);
            findViewById(R.id.divider2).setVisibility(VISIBLE);
            findViewById(R.id.myket_icon).setVisibility(VISIBLE);
            findViewById(R.id.myket_table).setVisibility(VISIBLE);
            storeDeveloperID = "0";
            urlbtnDown = "bazaar://collection?slug=by_author&aid=" + storeDeveloperID; //ارجاع به برنامه‌های توسعه‌دهنده
            urlbtnShare = "https://cafebazaar.ir/app/" + alpha_package;
            urlbtnHelp = "bazaar://details?id=" + alpha_package; //ارجاع به برنامه
            urlbtnCom = "bazaar://details?id=" + alpha_package;// ارجاع به صفحه نظرات
            storePack = "com.farsitel.bazaar";
            checkName(alpa_context, storePack);
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

    public void dia(Aboutme_Myket v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(alpa_context);
        builder.setView(v);//dialogView
        AlertDialog alertDialog = builder.create();
        findViewById(R.id.btnClose).setVisibility(GONE);
        findViewById(R.id.btnClose3).setVisibility(GONE);
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }

    public ImageButton closeWindow() {
        btnClose.setVisibility(VISIBLE);
        return findViewById(R.id.btnClose);
    }

    public void justifyText(TextView textView){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textView.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        } else {
            textView.setGravity(Gravity.CENTER|Gravity.DISPLAY_CLIP_HORIZONTAL);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setForegroundGravity(Gravity.CENTER_HORIZONTAL|Gravity.FILL_HORIZONTAL);
            }
        }
    }
    @SuppressLint("QueryPermissionsNeeded")
    public void checkName(Context context, String name) {
        ArrayList<HashMap<String, Object>> items = new ArrayList<>();
        final PackageManager pm = context.getPackageManager();

        List<PackageInfo> packs = pm.getInstalledPackages(0);
        for (PackageInfo pi : packs) {
            if (pi.packageName.toLowerCase().contains(name)) { //"stockcal"
                HashMap<String, Object> map = new HashMap<>();
                map.put("appName", pi.applicationInfo.loadLabel(pm));
                map.put("packageName", pi.packageName);
                items.add(map);
            }
        }
        if (items.size() >= 1) {

            Log.i("TAG", "checkName: OK");
            Log.i("TAG", "checkName: OK");
            btnDown.setEnabled(true);
            btnShare.setEnabled(true);
            btnCom.setEnabled(true);
            btnHelp.setEnabled(true);
            findViewById(R.id.myket_table).setBackgroundResource(android.R.color.transparent);
        } else {
            Log.i("TAG", "checkName: NOT OK");
            btnDown.setEnabled(false);
            btnShare.setEnabled(false);
            btnCom.setEnabled(false);
            btnHelp.setEnabled(false);
            findViewById(R.id.myket_table).setBackgroundResource(android.R.drawable.progress_indeterminate_horizontal);

        }
    }
/*
    public Intent onIntent(String url){
        return new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    }

*/
}
