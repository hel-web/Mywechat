package com.example.mywechat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link settingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class settingFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView sname,name;
    private View mView;
    private Button mButton,mButton1,mButton2;
    int status=0;

    String[] title ={"Legends Never Die", "约定", "美丽新世界"};
    String[] author ={"英雄联盟", "周蕙", "伍佰"};
    ActivityReceiver activityReceiver;

    public static final String CTL_ACTION =
            "org.ly.action.CTL_ACTION";
    public static final String UPDATE_ACTION =
            "org.ly.action.UPDATE_ACTION";



    public settingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment weixinFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static settingFragment newInstance(String param1, String param2) {
        settingFragment fragment = new settingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.tab04, container, false);
        initdatas();
        mButton.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);

        activityReceiver = new ActivityReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPDATE_ACTION);
        getActivity().registerReceiver(activityReceiver, filter);

        Intent intent = new Intent(getActivity(), MusicService.class);
        getActivity().startService(intent);

        return mView;
    }
    public void initdatas(){
        sname = mView.findViewById(R.id.med_sname);
        name = mView.findViewById(R.id.med_name);
        mButton = mView.findViewById(R.id.button);
        mButton1 = mView.findViewById(R.id.button1);
        mButton2 = mView.findViewById(R.id.button2);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(CTL_ACTION);
        switch (v.getId())
        {
            case R.id.button:
                System.out.println(v.getId());
                intent.putExtra("control", 0);
                break;

            case R.id.button1:
                intent.putExtra("control", 1);
                break;
            //
            case R.id.button2:
                intent.putExtra("control", 2);
                break;
        }
        // 发送广播，将被Service组件中的BroadcastReceiver接收到
        getActivity().sendBroadcast(intent);

    }
    public class ActivityReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            // 获取Intent中的update消息，update代表播放状态
            int update = intent.getIntExtra("update", -1);
            // 获取Intent中的current消息，current代表当前正在播放的歌曲
            int current = intent.getIntExtra("current", -1);
            System.out.println(update);
            if (current >= 0)
            {

                sname.setText(title[current]);
                name.setText(author[current]);
            }


            switch (update)
            {
                case 0:
                    //play.setImageResource(R.drawable.play);
                    System.out.println(123);
                    status = 0;
                    break;
                // 控制系统进入播放状态
                case 1:
                    // 播放状态下设置使用暂停图标
                    //play.setImageResource(R.drawable.pause);
                    // 设置当前状态
                    status = 1;
                    break;
                // 控制系统进入暂停状态
            }
        }
    }
}