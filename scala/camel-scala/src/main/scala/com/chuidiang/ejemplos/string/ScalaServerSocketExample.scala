package com.chuidiang.ejemplos.string

import java.net._
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

object ScalaServerSocketExample {
  def main(args: Array[String]): Unit = {
    new Server(55558).start
  }
}

class ClientThread(private val socket: Socket) extends Thread {

  override def run() = {
    val reader = new BufferedReader(new InputStreamReader(socket.getInputStream))
    val writer = new PrintWriter(socket.getOutputStream)
    val thread = new ServerLectorThread(reader, writer)
    thread.start
    thread.join
    socket.close
  }
}

class Server(val port: Int) extends Thread {
  override def run = {
    val serverSocket = new ServerSocket(port)
    while (true) {
      val socket = serverSocket.accept
      new ClientThread(socket).start
    }
  }
}

class ServerLectorThread(reader: BufferedReader, writer: PrintWriter) extends Thread {
  override def run() = {
    var line: String = null
    while ((line = reader.readLine()) != null) {
      println("Received " + line)
      writer.println("Hello " + line + "!")
      writer.flush
    }
  }
}