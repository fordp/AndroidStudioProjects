package com.example.gmuras.fragmentexample;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button analogButton, digitalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        analogButton = (Button) findViewById(R.id.button_analog);
        analogButton.setOnClickListener(this);
        digitalButton = (Button) findViewById(R.id.button_digital);
        digitalButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Fragment fragment;
        if (view == analogButton) {
            fragment = new FragmentAnalog();
        } else {
            fragment = new FragmentDigital();
        }
        replaceFragment(fragment);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
