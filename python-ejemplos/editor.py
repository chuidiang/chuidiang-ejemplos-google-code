'''
Created on 28/04/2012

@author: Chuidiang
'''
from ScrolledText import ScrolledText
from Tkconstants import INSERT, W, N, E, S, END
from Tkinter import Button, StringVar, Tk, Label
import tkFileDialog

def cargar():
    fichero = tkFileDialog.askopenfilename()
    nombreFichero.set(fichero)
    f = open(fichero)
    contenido.delete(1.0, END)
    contenido.insert(INSERT, f.read())
    f.close()

def salvar():
    fichero = tkFileDialog.asksaveasfilename()
    f = open(fichero,"w")
    f.write(contenido.get(1.0, END))
    f.close()
    
if __name__ == '__main__':
    
    ventana = Tk()
    ventana.title("Editor tonto de textos")
    
    t = Label(ventana)
    t.grid(row=0, column=2, sticky=W + E, columnspan=2)
    
    nombreFichero = StringVar()
    t["textvariable"] = nombreFichero

    botonLoad = Button(ventana, text="Load", command=cargar)
    botonLoad.grid(row=0, column=0, sticky=W)
    
    botonSave = Button(ventana, text="Save", command=salvar)
    botonSave.grid(row=0, column=1, sticky=W)
    
    contenido = ScrolledText(ventana)
    contenido.grid(row=1, columnspan=3, sticky=W + E + N + S)
    
    ventana.grid_columnconfigure(2, weight=1)
    ventana.grid_rowconfigure(1, weight=1)
    ventana.mainloop()
