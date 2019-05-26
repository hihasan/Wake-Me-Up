package com.hihasan.wakemeup.model;

public class ContentModel
{
    public String phone;
    public String time;
    public String cancel;


    public ContentModel(String phone, String time, String cancel){
        this.phone=phone;
        this.time=time;
        this.cancel=cancel;
    }

    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone=phone;}
    public String getTime(){return time;}
    public void setTime(String time){this.time=time;}
    public String getCancel(){return cancel;}
    public void setCancel(String cancel){this.cancel=cancel;}
}
