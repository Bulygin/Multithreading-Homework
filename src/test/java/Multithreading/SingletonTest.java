package Multithreading;

import Multithreading.Singleton.Singleton;
import Multithreading.Singleton.UserSingleton;
import org.junit.Assert;
import org.junit.Test;

public class SingletonTest {

  /**
   * Answers:
   * <p>
   * Not lazy: simple speed can't use deferred initialization Lazy: low performance in synchronized
   * block or uses volatile only after 1,5 version java can use deferred initialization
   */

  @Test
  public void Singleton_FiveUsersTryCreateSingleton_OnlyOneSingletonCreated() {
    final int COUNT = 5;
    Thread[] threads = new Thread[COUNT];
    UserSingleton[] users = new UserSingleton[COUNT];

    for (int i = 0; i < COUNT; i++) {
      users[i] = new UserSingleton("User " + i);
      threads[i] = new Thread(users[i]);
    }

    for (Thread t : threads) {
      t.start();
    }

    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    for (UserSingleton user : users) {
      user.setStop(true);
    }

    try {
      for (Thread t : threads) {
        t.join();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Singleton singleton = users[0].getSingleton();
    for (UserSingleton user : users) {
      Assert.assertEquals(singleton, user.getSingleton());
    }
  }
}