package com.chuidiang.ejemplos.data

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.buffer.ChannelBuffers
import org.jboss.netty.channel.Channel
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder

class DataEncoder() extends OneToOneEncoder {
   override def encode(
            ctx:ChannelHandlerContext, channel:Channel, msg:Object):Object  = {
     msg match {
       case data:Data => createChannel(data)
       case _ => throw new NullPointerException("Data expected")
     }
   }
   
   def createChannel(data:Data): ChannelBuffer = {
     val bytes = Array[Byte](33.toByte, data.getLength().toByte)
     bytes :+ data.getBytes()
     bytes :+ ProtocolConstants.getChecksum(data.getBytes())
     ChannelBuffers.wrappedBuffer(bytes)
   }
}