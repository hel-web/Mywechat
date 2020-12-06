package com.example.mywechat;

public class Club{
    public String mPath;
    public String mName;
    public String mMess;

    public Club(){

    }

    public Club(String path,String name,String mess){
        this.mMess = mess;
        this.mName = name;
        this.mPath = path;
    }

    @Override
    public String toString() {
        return "People{" +
                "mPath='" + mPath + '\'' +
                ", mName='" + mName + '\'' +
                ", mMess='" + mMess + '\'' +
                '}';
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmMess(String mMess) {
        this.mMess = mMess;
    }

    public String getmPath() {
        return mPath;
    }

    public String getmName() {
        return mName;
    }

    public String getmMess() {
        return mMess;
    }
}


