package com.chuidiang.ejemplos;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExample {

   private static final int NUMBER_OF_THREADS = 2;

   public static void main(String[] args) {
      ExecutorService executor = Executors
            .newFixedThreadPool(NUMBER_OF_THREADS);

      executor.execute(new Work("Work 1"));
      executor.execute(new Work("Work 2"));
      Future<String> future = executor.submit(new OtherWork("Work 3"));

      try {
         executor.shutdown();

         while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("Waiting for the works to finish");
         }

         System.out.println("All works finished");

         System.out.println("work 3 result " + future.get());

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

}

class Work implements Runnable {
   private String workName;

   public Work(String workName) {
      this.workName = workName;
   }

   public void run() {
      System.out.println("Starting Thread " + Thread.currentThread().getName()
            + ", work " + workName);
      try {
         Thread.sleep(4000);
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      System.out.println("Stopping Thread " + Thread.currentThread().getName()
            + ", work " + workName);
   }

}

class OtherWork implements Callable<String> {
   private String workName;

   public OtherWork(String callableName) {
      this.workName = callableName;
   }

   public String call() throws Exception {
      System.out.println("Starting Thread " + Thread.currentThread().getName()
            + ", work " + workName);
      try {
         Thread.sleep(4000);
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      System.out.println("Stopping Thread " + Thread.currentThread().getName()
            + ", work " + workName);
      return "ya";
   }

}
