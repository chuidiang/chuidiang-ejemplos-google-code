package com.chuidiang.ejemplos;

import java.io.Serializable;
import java.util.Date;

public class SomeData implements Serializable {
   private static final long serialVersionUID = 7137987108991450388L;
   private String aString;
   private int aInteger;
   private Date aDate;

   public SomeData(String aString, int aInteger, Date aDate) {
      this.aString = aString;
      this.aDate = aDate;
      this.aInteger = aInteger;
   }

   @Override
   public String toString() {
      return "SomeData [aString=" + aString + ", aInteger=" + aInteger
            + ", aDate=" + aDate + "]";
   }

   public String getaString() {
      return aString;
   }

   public void setaString(String aString) {
      this.aString = aString;
   }

   public int getaInteger() {
      return aInteger;
   }

   public void setaInteger(int aInteger) {
      this.aInteger = aInteger;
   }

   public Date getaDate() {
      return aDate;
   }

   public void setaDate(Date aDate) {
      this.aDate = aDate;
   }
}
