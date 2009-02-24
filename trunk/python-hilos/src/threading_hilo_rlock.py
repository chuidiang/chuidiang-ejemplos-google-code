'''
Created on 23/02/2009
@author: Chuidiang

Ejemplo simple de hilo usando la clase Thread del
modulo threading y la clase RLock
'''

from threading import Thread, RLock
import time

# Hacemos una clase que herede de Thread.
# Si definimos un constructor __init__(), debemos
# obligatoriamente llamar al metodo __init__() de 
# la clase Thread. Si no hacemos constructor, no
# es necesario.
class MiHilo(Thread):
    # Se le pasa como ultimo parametro el Lock
    def __init__ (self, inicio, fin, bloqueo):
        Thread.__init__(self)
        self.inicio = inicio
        self.fin = fin
        self.bloqueo = bloqueo
    
    # Metodo que se ejecutara en un hilo    
    def run (self):
        # Espera por Lock
        bloqueo.acquire()
        # Comienza la cuenta
        for i in range(self.inicio,self.fin):
           print "contador = "+str(i)
           time.sleep(0.2)
        bloqueo.release()
            
# Crea el hilo y lo arranca. Luego se pone a contar
if __name__ == '__main__':
    # Creacion del Lock y bloqueo del mismo
    bloqueo = RLock()
    bloqueo.acquire()
    
    # Arranque del hilo
    hilo = MiHilo(0,10, bloqueo)
    hilo.start()
    
    # Hacemos esperar al hilo
    time.sleep(1)
    # Un bucle y llamamos a acquire() muchas veces
    for i in range (10,20):
       print "main = "+str(i)
       bloqueo.acquire()
       time.sleep(0.1)
       
    # Liberamos el bloqueo una sola vez, el hilo no
    # comienza
    bloqueo.release()
    print "El hilo todavia no ha comenzado"
    
    # LLamamos a tantos release() con acquire() antes
    for i in range(10,20):
       bloqueo.release()