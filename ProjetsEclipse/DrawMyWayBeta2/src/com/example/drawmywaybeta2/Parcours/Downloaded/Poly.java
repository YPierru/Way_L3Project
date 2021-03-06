package com.example.drawmywaybeta2.Parcours.Downloaded;

import android.os.Parcel;
import android.os.Parcelable;

public class Poly  implements Parcelable{

	private String points;

	public Poly (Parcel in){
		this.points=in.readString();
	}
	
	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Poly [points=" + points + "]";
	}

	public static final Parcelable.Creator<Poly> CREATOR = new Parcelable.Creator<Poly>() {

		@Override
		public Poly createFromParcel(Parcel source) {
			return new Poly(source);
		}

		@Override
		public Poly[] newArray(int size) {
			return new Poly[size];
		}
	};
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.points);
	}
	
	
}
