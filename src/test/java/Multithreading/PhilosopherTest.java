package Multithreading;

import Multithreading.Philosophers.Fork;
import Multithreading.Philosophers.Philosopher;
import org.junit.Assert;
import org.junit.Test;

public class PhilosopherTest {

  @Test
  public void Philosopher_FiveThreads_AllMustAte() {
    try {
      final int COUNT = 5;
      Philosopher[] philosophers = new Philosopher[COUNT];
      Fork last = new Fork(String.valueOf(5));
      Fork left = last;

      for (int i = 0; i < COUNT; i++) {
        Fork right = (i == COUNT - 1) ? last : new Fork(String.valueOf(i + 1));
        philosophers[i] = new Philosopher("Philosopher" + (i + 1), left, right);
        left = right;
      }

      Thread[] threads = new Thread[COUNT];

      for (int i = 0; i < COUNT; i++) {
        threads[i] = new Thread(philosophers[i]);
        threads[i].start();
      }

      Thread.sleep(5000);

      for (Philosopher phil : philosophers) {
        phil.setStopFlag(true);
      }

      for (Thread thread : threads) {
        thread.join();
      }

      int ateCount = 5;
      for (Philosopher phil : philosophers) {
        System.out.printf("%s ate %d times%n", phil.getName(), phil.getEatCount());
        phil.setStopFlag(true);
        ateCount--;
      }
      Assert.assertEquals(0, ateCount);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
