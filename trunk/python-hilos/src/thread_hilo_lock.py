'''
Created on 20/02/2009
@author: Chuidiang

Ejemplo de un hilo con bloqueo.
Un main lanza un hilo y un lock sirve para hacer que el hilo espere
el permiso del main para continuar y para que el main espere que el hilo
termine antes de terminar el programa
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
    # Espera a que termine el hilo, esperando la liberacion
    # del bloqueo
    bloqueo.acquire()
    bloqueo.release()
    print "Fin de programa"
    
