package com.example.newsbuddy;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newsbuddy.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem mhome,mtech,msports,mhealth,mentertainment,mscience;
    MyPagerAdapter pagerAdapter;

    String api = "59991cf8600e49379c97a414310ea921";

    AppCompatDialog dialog;

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // set toolbar
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        // set tab layout items id
        mhome = findViewById(R.id.homeTab);
        mtech = findViewById(R.id.technologyTab);
        msports = findViewById(R.id.sportsTab);
        mhealth = findViewById(R.id.healthTab);
        mentertainment = findViewById(R.id.entertainmentTab);
        mscience = findViewById(R.id.scienceTab);

        // Now set fragment views in view pager using adapter

        tabLayout = findViewById(R.id.include);
        ViewPager viewPager = findViewById(R.id.fragmentContainer);

        FragmentManager fragmentManager = getSupportFragmentManager();
        pagerAdapter = new MyPagerAdapter(fragmentManager,6);  // tab count or behavior is 6
        viewPager.setAdapter(pagerAdapter);

        // code for tapping directly on random tab item
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2 || tab.getPosition() == 3 || tab.getPosition() == 4 || tab.getPosition() == 5){
                    pagerAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Now code for swipe the fragment in viewpager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        // Dialog Box code :
        dialog = new AppCompatDialog(MainActivity.this);
        dialog.setContentView(R.layout.profile_dialog_box);

        AppCompatButton logout = dialog.findViewById(R.id.btnLogout);
        ImageView profile = dialog.findViewById(R.id.imgProfile);
        TextView name = dialog.findViewById(R.id.txtName);
        TextView email = dialog.findViewById(R.id.txtEmail);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        GoogleSignInClient gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
            assert name != null;
            name.setText(account.getDisplayName());
            assert email != null;
            email.setText(account.getEmail());
            assert profile != null;
            Glide.with(this).load(account.getPhotoUrl()).into(profile);
        }

        assert logout != null;
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(MainActivity.this,SignInActivity.class));
                        finish();
                    }
                });

            }
        });

        // Is connected with internet dialog box code
        broadcastReceiver = new NetworkBroadcast();

        registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


    }

    // this is also a internet code part


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    public void GoToProfile(View view) {
        dialog.show();
    }

    // On Back press click from home

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}