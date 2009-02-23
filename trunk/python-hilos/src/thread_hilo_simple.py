'''
Created on 20/02/2009
@author: Chuidiang

Ejemplo de un hilo. Dos bucles, uno en un hilo y otro en
el principal, contando simultaneamente.
'''

import thread
import time

# Indica si ha terminado el hilo
hilo_terminado = False

# Funcion que se ejecuta en un hilo
def funcion (inicio, fin):
    global hilo_terminado
    # Bucle de inicio a fin, con espera de un segundo
    for valor in range(inicio,fin):
        print "Hilo : "+str(valor)
        time.sleep(0.1)
    # marca hilo terminado
    hilo_terminado = True
    print "Terminado hilo "
        
        
if __name__ == '__main__':
    # Lanzamos un hilo
    thread.start_new_thread(funcion, (3,11))
    # y nos ponemos a contar, en paralelo con lo que haga
    # el hilo
    for i in range(1,5):
        print "Principal : "+str(i) 
        time.sleep(0.1)
    # Espera a que termine el hilo
    while (not hilo_terminado):
        pass
    
