'''
Created on 20/02/2009
@author: Chuidiang


Ejemplo de socket en python. Un servidor que acepta clientes.
Si el cliente envia "hola", el servidor contesta "pues hola".
Si el cliente envia "adios", el servidor contesta "pues adios" y
cierra la conexion.
El servidor no acepta multiples clientes simultaneamente.
'''

import socket

if __name__ == '__main__':
   # Se prepara el servidor
   server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
   server.bind(("", 8000))
   server.listen(1)
   print "Esperando clientes..."
   
   # bucle para atender clientes
   while 1:
       
      # Se espera a un cliente
      add, cliente = server.accept()
      
      # Se escribe su informacion
      print "conectado "+str(cliente)
      
      # Bucle indefinido hasta que el cliente envie "adios"
      seguir = True
      while seguir:
         # Espera por datos
         peticion = add.recv(1000)
         
         # Contestacion a "hola"
         if ("hola"==peticion):
             print str(cliente)+ "envia hola: contesto"
             add.send("pues hola")
             
         # Contestacion y cierre a "adios"
         if ("adios"==peticion):
             print str(cliente)+ "envia adios: contesto y desconecto"
             add.send("pues adios")
             add.close()
             print "desconectado "+str(cliente)
             seguir = False
