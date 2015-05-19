package com.chuidiang.ejemplos.data

import java.nio.ByteBuffer

class Data(var value:Int, var string:String) {
  
  def getBytes() : Array[Byte] = {
    
    val buffer = ByteBuffer.allocate(4+string.getBytes.length)
    buffer.putInt(value)
    for (byte <- string.getBytes){
      buffer.put(byte)
    }
    println(new String(buffer.array()))
    buffer.array()
  }
  
  def fromBytes(bytes:Array[Byte]) = {
    val buffer = ByteBuffer.wrap(bytes)
    value = buffer.getInt
    string = new String(bytes,4,bytes.length-4)
    
    println ("value="+value)
    println ("string="+string)
  }
  
  override def toString():String = {
   value + " "+string
  }
  
  def getLength() : Int = {
    return 4 + string.getBytes().length
  }
}

object TestData extends App {
  val originalData = new Data(33,"adios")
  val constructedData = new Data(0,"")
  constructedData.fromBytes(originalData.getBytes())
  println ( constructedData)
}