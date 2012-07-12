package com.chuidiang.ejemplos;

import java.util.Date;


public abstract class Padre {
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
    public String getAtributoPadre() {
        return atributoPadre;
    }
    public void setAtributoPadre(String atributoPadre) {
        this.atributoPadre = atributoPadre;
    }
    
    private long id;
    
	private Date date;
	private String atributoPadre;
	
	public abstract String diQuienEres();
}
