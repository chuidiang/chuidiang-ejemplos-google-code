package com.chuidiang.ejemplos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="La_Clase")
public class UnaClase {
   @XmlElement(name="El_Atributo")
   private String unAtributo;
    
   
   public void setUnAtributo(String unAtributo) {
      this.unAtributo = unAtributo;
   }


   String getUnAtributo() {
      return this.unAtributo;
   }
}