package com.chuidiang.ejemplos.jasper;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Data {
   public static Collection<TheData > createBeanCollection() {
      List<TheData> result = new LinkedList<TheData>();
      result.add(new TheData(1,"uno"));
      result.add(new TheData(2,"dos"));
      result.add(new TheData(3,"tres"));
      result.add(new TheData(4,"cuatro"));
      result.add(new TheData(5,"cinco"));
      result.add(new TheData(6,"seis"));
      return result;
   }
}
