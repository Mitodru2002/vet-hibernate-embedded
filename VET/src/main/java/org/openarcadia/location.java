package org.openarcadia;
import javax.persistence.*;
@Embeddable
public class location {
	private String loc;
	private int year;
	public location() {};
	public location (String loc, int year) {
		this.loc = loc;
		this.year = year;
	}
	public String getloc() {
		return loc;
	}
	public void setname(String loc) {
		this.loc=loc;
	}
	public int getyear() {
		return year;
	}
	public void setyear(int year) {
		this.year=year;
	}
	

}
