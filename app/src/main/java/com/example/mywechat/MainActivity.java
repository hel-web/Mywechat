package com.example.mywechat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Fragment mTab01=new weixinFragment();
    private  Fragment mTab02=new friFragment();
    private Fragment mTab03= new contactFragment();
    private  Fragment mTab04=new settingFragment();
    private FragmentManager fm;


    private LinearLayout mTabWeixin;
    private LinearLayout mTabFri;
    private LinearLayout mTabCon;
    private LinearLayout mTabSet;

    private ImageButton mImaWei;
    private ImageButton mImaFri;
    private ImageButton mImaCon;
    private ImageButton mImaSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initview();
        initEvent();
        initFragment();
        setSelect(0);



    }
    private void initFragment(){
        fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        transaction.add(R.id.id_content,mTab01);
        transaction.add(R.id.id_content,mTab02);
        transaction.add(R.id.id_content,mTab03);
        transaction.add(R.id.id_content,mTab04);
        transaction.commit();


    }
    private void initview(){
        mTabWeixin=findViewById(R.id.id_tap_weixin);
        mTabFri=findViewById(R.id.id_tap_fri);
        mTabCon=findViewById(R.id.id_tap_contact);
        mTabSet=findViewById(R.id.id_tap_setting);

        mImaWei= findViewById(R.id.imagewixin);
        mImaFri=findViewById(R.id.imagefri);
        mImaCon=findViewById(R.id.imagecontact);
        mImaSet=findViewById(R.id.imagesetting);

    }



    private void hideFrament(FragmentTransaction transaction){
        transaction.hide(mTab01);
        transaction.hide(mTab02);
        transaction.hide(mTab03);
        transaction.hide(mTab04);

    }
    private  void setSelect(int i){
        FragmentTransaction transaction=fm.beginTransaction();
        hideFrament(transaction);
        switch (i){
            case 0:
                Log.d("setSelect","1");
                transaction.show(mTab01);
                mImaWei.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case 1:

                transaction.show(mTab02);
                mImaFri.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case 2:

                transaction.show(mTab03);
                mImaCon.setImageResource(R.drawable.tab_address_pressed);
                break;
            case 3:
                transaction.show(mTab04);
                mImaSet.setImageResource(R.drawable.tab_settings_pressed);
                break;
            default: break;
        }
        transaction.commit();

    }


    @Override
    public void onClick(View v) {
        Log.d("onClick","1");
        resetImgs();
        switch (v.getId()){
            case R.id.id_tap_weixin:
                Log.d("onClink","2");
                setSelect(0);
                break;
            case R.id.id_tap_fri:
                setSelect(1);
                break;
            case R.id.id_tap_contact:
                setSelect(2);
                break;
            case R.id.id_tap_setting:
                setSelect(3);
                break;
            default:
                break;
        }

    }
    public void resetImgs(){
        mImaWei.setImageResource(R.drawable.tab_weixin_normal);
        mImaFri.setImageResource(R.drawable.tab_find_frd_normal);
        mImaCon.setImageResource(R.drawable.tab_address_normal);
        mImaSet.setImageResource(R.drawable.tab_settings_normal);

    }
    private  void initEvent(){
        mTabWeixin.setOnClickListener(this);
        mTabFri.setOnClickListener(this);
        mTabCon.setOnClickListener(this);
        mTabSet.setOnClickListener(this);



    }
}