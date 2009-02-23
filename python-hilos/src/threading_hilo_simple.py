'''
Created on 23/02/2009
@author: Chuidiang

Ejemplo simple de hilo usando la clase Thread del
modulo threading
'''

from threading import Thread
import time

# Hacemos una clase que herede de Thread.
# Si definimos un constructor __init__(), debemos
# obligatoriamente llamar al metodo __init__() de 
# la clase Thread. Si no hacemos constructor, no
# es necesario.
class MiHilo(Thread):
    def __init__ (self, inicio, fin):
        Thread.__init__(self)
        self.inicio = inicio
        self.fin = fin
    
    # Metodo que se ejecutara en un hilo    
    def run (self):
        for i in range(self.inicio,self.fin):
            print "contador = "+str(i)
            time.sleep(0.2)
            
# Crea el hilo y lo arranca. Luego se pone a contar
if __name__ == '__main__':
    # Creacion de la clase MiHilo y arranque de la misma
    hilo = MiHilo(0,10)
    hilo.start()
    # Un bucle
    for i in range (10,20):
       print "main = "+str(i)
       time.sleep(0.1)