'''
Created on 24/02/2009
@author: Chuidiang

Ejemplo de hilo usando Condition para esperar que haya datos
diponibles. El main genera esos datos y despierta al hilo cada vez.
'''

from threading import Thread, Condition
import time

# Hilo que espera los datos.
class MiHilo (Thread):
    # Se le pasa una lista de datos y la Condition
    # para espera de los mismos.
    def __init__(self,lista,condicion):
        Thread.__init__(self)
        self.lista = lista
        self.condicion = condicion
        # Para saber cuando terminar el hilo
        self.fin=False
        
    def run(self):
        self.condicion.acquire()
        # Mientras no haya que terminar
        while not self.fin:
            # Esperar por datos
            self.condicion.wait()
            # Si no hay que terminar se escriben los datos
            # que haya en la lista
            if not self.fin:
               while len(lista)>0:
                  print self.lista.pop(0)
        self.condicion.release()

if __name__ == '__main__':
    # Creacion de la lista, la Condition y el hilo
    lista =[]
    condicion = Condition()
    hilo = MiHilo(lista, condicion)
    hilo.start()
    
    # Bucle
    for i in range (0,10):
       # Cogemos la condicion
       condicion.acquire()
       # Ponemos un par de datos en la lista
       lista.append(i)
       lista.append("numero "+str(i))
       # Avisamos que estan listos
       condicion.notify()
       # Y dejamos al hilo continuar
       condicion.release()
       time.sleep(1)
    # Mandamos al hilo que termine
    hilo.fin=True
    condicion.acquire()
    condicion.notify()
    condicion.release()
    