package com.chuidiang.ejemplos.data

import com.chuidiang.ejemplos.data.ProtocolConstants._
import java.nio.ByteBuffer
import java.util.Arrays

/**
 * @author JAVIER
 */
class Protocol(var data: Data) {

  def getBytes() = {
    var bytes = Array[Byte](MAGIC_NUMBER, data.getLength().toByte)
    bytes = bytes ++ data.getBytes()
    bytes = bytes ++ Array(getChecksum(data.getBytes()))
    bytes
  }

  def fromBytes(bytes: Array[Byte]) = {
    val dataBytes = bytes.slice(2, bytes(1) + 2) // skip magic number and length
    data = new Data(0, null)
    data.fromBytes(dataBytes)
    // TODO: Verify checksum
  }
}

object ProtocolConstants {
  val MAGIC_NUMBER: Byte = 33

  def getChecksum(bytes: Array[Byte]): Byte = {
    var checksum = 0.toByte;
    for (byte <- bytes) {
      checksum = (checksum + byte).toByte
    }
    return checksum.toByte
  }

  def searchMessage(byteBuffer: ByteBuffer): (Option[Int], Option[Int]) = {
    var index = byteBuffer.position()
    while (byteBuffer.hasRemaining() && byteBuffer.get() != MAGIC_NUMBER) {
      index = byteBuffer.position()
    }

    if (!byteBuffer.hasRemaining()) {
      return (None, None)
    }

    val length = byteBuffer.get
    var checksum = 0.toByte

    for (i <- 1 to length) {
      if (byteBuffer.hasRemaining()) {
        checksum = (checksum + byteBuffer.get).toByte
      }
    }

    if (byteBuffer.hasRemaining()) {
      if (checksum == byteBuffer.get) {
        return (Option.apply(index), Option.apply(byteBuffer.position() - index))
      }
    }
    
    byteBuffer.position(index)
    return (None, None)
  }
}

object TestProtocol extends App {
  val data = new Data(11, "hola")
  val protocol = new Protocol(data)

  val bytes = protocol.getBytes()
  println("bytes="+Arrays.toString(bytes))
  protocol.fromBytes(bytes)
  println("data="+protocol.data)

  val byteBuffer = ByteBuffer.wrap(bytes)

  println(ProtocolConstants.searchMessage(byteBuffer))
  byteBuffer.position(0)

  var indices = ProtocolConstants.searchMessage(byteBuffer)
  
  println("indices="+indices)
  
  protocol.fromBytes(bytes.slice(indices._1.get, indices._2.get))
  println("data="+protocol.data)

}