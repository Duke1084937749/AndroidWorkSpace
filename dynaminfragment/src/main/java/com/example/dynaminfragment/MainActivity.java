package com.example.dynaminfragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ib_weixin, ib_contact, ib_find, ib_me;
    private WeiXinFragMent weiXinFragMent;
    private ContactFragMent contactFragMent;
    private FindFragMent findFragMent;
    private MeFragMent meFragMent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ib_weixin = findViewById(R.id.ib_weixin);
        ib_contact = findViewById(R.id.ib_contact);
        ib_find = findViewById(R.id.ib_find);
        ib_me = findViewById(R.id.ib_me);
        ib_weixin.setOnClickListener(this);
        ib_contact.setOnClickListener(this);
        ib_find.setOnClickListener(this);
        ib_me.setOnClickListener(this);
        ib_weixin.performClick();
    }

    @Override
    public void onClick(View v) {
        clearIcon();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.ib_weixin:
                if (weiXinFragMent == null){
                    weiXinFragMent = new WeiXinFragMent();
                }
                transaction.replace(R.id.ll_container,weiXinFragMent);
                ib_weixin.setImageResource(R.drawable.weixin_pressed);
                break;

            case R.id.ib_contact:
                if (contactFragMent == null){
                    contactFragMent = new ContactFragMent();
                }
                transaction.replace(R.id.ll_container,contactFragMent);
                ib_contact.setImageResource(R.drawable.contact_list_pressed);
                break;

            case R.id.ib_find:
                if(findFragMent == null){
                    findFragMent = new FindFragMent();
                }
                transaction.replace(R.id.ll_container,findFragMent);
                ib_find.setImageResource(R.drawable.find_pressed);
                break;
            case R.id.ib_me:
                if (meFragMent == null){
                    meFragMent = new MeFragMent();
                }
                transaction.replace(R.id.ll_container,meFragMent);
                ib_me.setImageResource(R.drawable.profile_pressed);
                break;
        }
        transaction.commit();
    }

    private void clearIcon(){
        ib_weixin.setImageResource(R.drawable.weixin_normal);
        ib_contact.setImageResource(R.drawable.contact_list_normal);
        ib_find.setImageResource(R.drawable.find_normal);
        ib_me.setImageResource(R.drawable.profile_normal);
    }
}
