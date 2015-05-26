package com.chuidiang.ejemplos;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       new App().startExample();
    }

   private void startExample() {
      Object objectForSynchrnize = new Object();
      
      synchronized (objectForSynchrnize) {
         System.out.println("I'm inside a synchronized block");
      }
      
      Lock aLock = new ReentrantLock();
      
      aLock.lock();
      
      try {
         System.out.println("I'm inside a synchronized block");
      } catch (Exception e){
         System.err.println("Error! "+e.getMessage());
      } finally {
         aLock.unlock();
      }
     
   }
    
    
}
