package com.example.mywechat;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class linearAdapter extends RecyclerView.Adapter<linearAdapter.LinearViewHolder> {

    private Context mContext;
    private ArrayList<Club> mList;

    private Handler handler;

    public linearAdapter(Context context,ArrayList<Club> list){
        this.mContext = context;
        this.mList = list;
    }
    @NonNull
    @Override
    public linearAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull final LinearViewHolder holder, int position) {
        Club data = mList.get(position);
        String url = data.mPath;
        Glide.with(mContext).load(url).into(holder.mImg);

        holder.mtextView1.setText(data.mName);
        holder.mtextView2.setText(data.mMess);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class LinearViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImg;
        private TextView mtextView1;
        private TextView mtextView2;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.it_img);
            mtextView1 = itemView.findViewById(R.id.weixin_name);
            mtextView2 = itemView.findViewById(R.id.weixin_content);
        }
    }
}
