package com.example.mywechat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link contactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class contactFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private  View mView;
    private RecyclerView mRecy;
    private ArrayList<Club> mList = new ArrayList<Club>();



    public contactFragment() {
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
    public static contactFragment newInstance(String param1, String param2) {
        contactFragment fragment = new contactFragment();
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




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.tab03, container, false);
        initRecyclerView();
        initView();;
        return  mView;
    }
    private void initRecyclerView(){
        mRecy = mView.findViewById(R.id.recyview);
        mRecy.setAdapter(new linearAdapter(getActivity(),mList));
        mRecy.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));

    }
    String[] List ={"https://n.sinaimg.cn/sports/transform/292/w650h442/20190224/nM1S-htknpmi1873924.jpg",
            "里奥·梅西",
            "18603566530",
            "https://himg2.huanqiu.com/attachment2010/2018/0711/20180711090224114.jpg",
            "克里斯蒂亚诺·罗纳尔多",
            "13265468954",
            "https://n.sinaimg.cn/spider20191027/0/w2048h1152/20191027/eb12-ihqyuyk2984953.jpg",
            "莱万多夫斯基",
            "18644895466"

    };


    private void initView(){
        for(int i=0;i<List.length;i=i+3){
            Club club = new Club();
            club.setmPath(List[i]);
            club.setmName(List[i+1]);
            club.setmMess(List[i+2]);
            mList.add(club);
        }

    }
}