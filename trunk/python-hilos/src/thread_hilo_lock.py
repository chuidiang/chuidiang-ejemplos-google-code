'''
Created on 20/02/2009
@author: Chuidiang

Ejemplo de un hilo con bloqueo. El main crea un lock y lo adquiere.
Luego lanza un hilo, que se quedara a la espera del lock. El main,
un tiempo despues, liberara el lock.
'''

import thread
import time

# Numero de hilos que han terminado
hilo_terminado = False

# Funcion que se ejecuta en un hilo

def funcion (inicio, fin, bloqueo):
    global hilo_terminado
    # El hilo espera el bloqueo
    print "Hilo: Pido bloqueo"
    bloqueo.acquire()
    print "Hilo: Empiezo "+str(inicio)+" "+str(fin)
    hilo_terminado = True
    print "Hilo: y termino"
    bloqueo.release()
        
        
if __name__ == '__main__':
    # Pedimos un lock y lo bloqueamos
    bloqueo = thread.allocate_lock()
    bloqueo.acquire()
    # Lanzamos un hilo, que se quedara en espera
    thread.start_new_thread(funcion, (3,11,bloqueo))
    # Hacemos una espera de dos segundos para asegurarnos que
    # entra el hilo
    time.sleep(2)
    #Liberamos el bloqueo
    print "Principal: Libero bloqueo"
    bloqueo.release()
    # Espera a que termine el hilo
    while (not hilo_terminado):
        pass
    
