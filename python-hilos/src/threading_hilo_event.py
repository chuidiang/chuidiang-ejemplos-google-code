'''
Created on 24/02/2009
@author: Chuidiang

Ejemplo de hilos con evento.
El main crea un Event y un hilo al que le pasa el Event.
El hilo se queda esperando por el Event.
El main, tras dos segundos, activa el Event, entrando el hilo
'''

from threading import Thread, Event
import time

# Hilo a arrancar
class MiHilo(Thread):
    # Se le pasa un numero identificador del hilo y un event
    def __init__(self, evento):
        Thread.__init__(self)
        self.evento=evento
    
    # Espera al event
    def run(self):
        self.evento.wait()
        print "Entra hilo "
        
if __name__ == '__main__':
    # Crea el evento
    evento = Event()
    # Arranca el hilo
    hilo=MiHilo(evento)
    hilo.start()
    # Espera dos segundos y activa el evento
    time.sleep(2)
    print "Hago evento.set()"
    evento.set()