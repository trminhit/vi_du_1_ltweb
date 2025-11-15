package vidu_2.model;

import java.io.Serializable;

public class Category implements Serializable{


	private static final long serialVersionUID = 1L;
	private int cateid;
	private String catename;
	private String icon;
	@Override
	public String toString() {
		return "Category [cateid=" + cateid + ", catename=" + catename + ", icon=" + icon + "]";
	}
	public int getCateid() {
		return cateid;
	}
	public void setCateid(int cateid) {
		this.cateid = cateid;
	}
	public String getCatename() {
		return catename;
	}
	public void setCatename(String catename) {
		this.catename = catename;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

}
