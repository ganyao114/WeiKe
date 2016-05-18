package com.just.weike.Dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Message implements Parcelable {

	
	
	public String Sender;
	public String Content;
	public String Data;
	public String Receiver;
	
	public Message(String Sender,String Content,String Data,
			String Receiver) {
		// TODO Auto-generated constructor stub
		this.Sender = Sender;
		this.Content = Content;
		this.Data = Data;
		this.Receiver = Receiver;
	}

	public String getSender() {
		return Sender;
	}

	public void setSender(String sender) {
		Sender = sender;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}

	public String getReceiver() {
		return Receiver;
	}

	public void setReceiver(String receiver) {
		Receiver = receiver;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() 
		     {
		         public Message createFromParcel(Parcel in) 
		         {
		             String Sender = in.readString();
		             String Content = in.readString();
		             String Data = in.readString();
		             String Receiver = in.readString();
		        	 return new Message(Sender,Content,Data,Receiver);
		         }

		         public Message[] newArray(int size) 
		         {
		             return new Message[size];
		         }
		     };
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(Sender);
		dest.writeString(Content);
		dest.writeString(Data);
		dest.writeString(Receiver);
	}

}
