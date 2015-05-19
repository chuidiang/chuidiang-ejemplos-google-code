package com.chuidiang.ejemplos.string

import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.channel.Channel
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.handler.codec.frame.FrameDecoder
import java.nio.charset.Charset
import org.jboss.netty.channel.ChannelHandler
import org.apache.camel.component.netty.ChannelHandlerFactory

class CustomFrameDecoder(val name:String) extends FrameDecoder with ChannelHandlerFactory {
  override def decode(ctx: ChannelHandlerContext, channel: Channel, buffer: ChannelBuffer): Object = {
    println(name+" -> "+buffer)
    var eolPosition = buffer.indexOf(buffer.readerIndex(), buffer.writerIndex(), '\n'.toByte)
    if (eolPosition > -1) {
      val length = eolPosition - buffer.readerIndex()
      val frame = extractFrame(buffer, buffer.readerIndex(), length - 1)
      buffer.skipBytes(length + 1)
      println(name + " Sending " + frame.toString(Charset.defaultCharset()))
      return frame.toString(Charset.defaultCharset())
    }
    return null
  }
  
  override  def newChannelHandler() : ChannelHandler = {
     new CustomFrameDecoder(name)
 }
  
}