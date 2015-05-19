package com.chuidiang.ejemplos.data

import org.jboss.netty.buffer.ChannelBuffer
import com.chuidiang.ejemplos.data.ProtocolConstants._
import org.jboss.netty.channel.Channel
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder
import com.chuidiang.ejemplos.data.ProtocolConstants._

/** It converts bytes to Data */
class DataDecoder() extends OneToOneDecoder {

  

  override def decode(
    ctx: ChannelHandlerContext, channel: Channel, msg: Object): Object = {

    msg match {
      case channelBuffer: ChannelBuffer =>

        val firstIndex = channelBuffer.readerIndex()

        var aByte = channelBuffer.readByte()
        while (aByte != MAGIC_NUMBER && channelBuffer.readable()) {
          aByte = channelBuffer.readByte()
        }
        if (aByte != MAGIC_NUMBER || channelBuffer.readableBytes() < 1) {
          // channelBuffer.readerIndex(firstIndex)
          return null;
        }
        val length = channelBuffer.readByte()
        if (channelBuffer.readableBytes() < length + 1) {
          channelBuffer.readerIndex(firstIndex)
          return null
        }

        val data = new Data(0, "")
        data.fromBytes(extractFrame(channelBuffer, channelBuffer.readerIndex(), length).array())

        val checksum = channelBuffer.readByte()
        if (checksum != getChecksum(data.getBytes())) {
          channelBuffer.readerIndex(firstIndex+1)
          return decode(ctx,channel,msg)
        }
        
        return data

      case _ => msg
    }
    return null
  }

  def extractFrame(buffer: ChannelBuffer, index: Int, length: Int): ChannelBuffer = {
    val frame = buffer.factory().getBuffer(length);
    frame.writeBytes(buffer, index, length);
    return frame;
  }
}

