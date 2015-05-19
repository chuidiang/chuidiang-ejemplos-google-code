package com.chuidiang.ejemplos.string

import java.nio.ByteOrder
import org.jboss.netty.buffer.BigEndianHeapChannelBuffer
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.LittleEndianHeapChannelBuffer
import org.jboss.netty.channel.Channel
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder
import org.jboss.netty.channel.ChannelHandler
import org.apache.camel.component.netty.ChannelHandlerFactory

class CustomEncoder(val name:String) extends OneToOneEncoder with ChannelHandlerFactory {
   override def encode(
            ctx:ChannelHandlerContext, channel:Channel, msg:Object):Object  = {
     println(name+" -> "+msg)
     val endian = ctx.getChannel().getConfig().getBufferFactory().getDefaultOrder()
     msg match {
       case string:String => createChannel(string,endian)
       case bigEndian:BigEndianHeapChannelBuffer => bigEndian
       case _ => throw new NullPointerException("string")
     }
   }
   
   def createChannel(string:String, endian:ByteOrder): ChannelBuffer = {
      if (endian == ByteOrder.BIG_ENDIAN){
        return new BigEndianHeapChannelBuffer((string+"\r\n").getBytes)
      }
      return new LittleEndianHeapChannelBuffer((string+"\r\n").getBytes)
   }
   override  def newChannelHandler() : ChannelHandler = {
     new CustomEncoder(name)
 }
}