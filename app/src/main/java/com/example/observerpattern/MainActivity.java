package com.example.observerpattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ObserverTest {
    private Subject mUserDataRepository;
    private TextView mTextName;
    private TextView mTextAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        initComponent();
        intitData();
    }

    private void intitData() {
        mUserDataRepository = Repository.getInstance();
        mUserDataRepository.registerObserver(this);
    }

    private void initComponent() {
        mTextAge = findViewById(R.id.text_pass);
        mTextName = findViewById(R.id.text_name);
    }

    public int getLayoutResource(){
        return R.layout.activity_main;
    }

    @Override
    public void onUserDataChanged(String fullname, int age) {
        mTextName.setText(fullname);
        mTextAge.setText(age+"");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserDataRepository.removeObserver(this);
    }
}
