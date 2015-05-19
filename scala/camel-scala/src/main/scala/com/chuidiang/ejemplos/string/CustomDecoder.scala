package com.chuidiang.ejemplos.string

import org.jboss.netty.handler.codec.oneone.OneToOneDecoder
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.Channel
import org.jboss.netty.buffer.ChannelBuffer
import java.nio.charset.Charset
import org.apache.camel.component.netty.ChannelHandlerFactory
import org.jboss.netty.channel.ChannelHandler

/** It converts bytes to String */
class CustomDecoder(val name: String) extends OneToOneDecoder with ChannelHandlerFactory {
  override def decode(
    ctx: ChannelHandlerContext, channel: Channel, msg: Object): Object = {
    println(name + " -> " + msg)
    msg match {
      case channel: ChannelBuffer =>
        var eolPosition = channel.indexOf(channel.readerIndex(), channel.writerIndex(), '\n'.toByte)
        if (eolPosition > -1) {
          val length = eolPosition - channel.readerIndex()
          val frame = extractFrame(channel, channel.readerIndex(), length - 1)
          channel.skipBytes(length + 1)
          println(name + " Sending " + frame.toString(Charset.defaultCharset()))
          return frame.toString(Charset.defaultCharset())
        }
        return null

        channel.toString(Charset.defaultCharset())
      case _ => msg
    }
  }

  override def newChannelHandler(): ChannelHandler = {
    new CustomDecoder(name)
  }

  def extractFrame(buffer: ChannelBuffer, index: Int, length: Int): ChannelBuffer = {
    val frame = buffer.factory().getBuffer(length);
    frame.writeBytes(buffer, index, length);
    return frame;
  }
}

