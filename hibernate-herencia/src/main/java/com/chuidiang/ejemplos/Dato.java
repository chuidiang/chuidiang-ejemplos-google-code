package com.chuidiang.ejemplos;

import java.util.Date;


public abstract class Dato {
	public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    private long id;
    
	private Date date;
	private String title;
	
	public abstract String diQuienEres();
}
