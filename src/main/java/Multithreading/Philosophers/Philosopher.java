package Multithreading.Philosophers;

import java.util.Random;
import sun.awt.Mutex;

public class Philosopher implements Runnable {

  private final Fork leftFork;
  private final Fork rightFork;
  private String name;
  private Random rnd = new Random();
  private Mutex volatileMutex;
  private int eatCount = 0;
  private volatile boolean stopFlag = false;

  public Philosopher(String name, Fork leftFork, Fork rightFork) {
    this.name = name;
    this.leftFork = leftFork;
    this.rightFork = rightFork;
  }

  public String getName() {
    return name;
  }

  public int getEatCount() {
    return eatCount;
  }

  private void eat() {
    takeLeftFork();
    takeRightFork();
    System.out.printf("%s eat%n", name);
    try {
      Thread.sleep(rnd.nextInt(1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    eatCount++;
    System.out.printf("%s ate%n", name);
    downLeftFork();
    downRightFork();
  }

  private void takeLeftFork() {
    System.out.printf("%s take left fork number %s%n", name, leftFork.getName());
    leftFork.setOnTable(false);
  }

  private void takeRightFork() {
    System.out.printf("%s take right fork number %s%n", name, rightFork.getName());
    rightFork.setOnTable(false);
  }

  private void downLeftFork() {
    System.out.printf("%s put left fork number %s%n", name, rightFork.getName());
    leftFork.setOnTable(true);
  }

  private void downRightFork() {
    System.out.printf("%s put right fork number %s%n", name, leftFork.getName());
    rightFork.setOnTable(true);
  }

  private void think() {
    System.out.printf("%s thinks%n", name);
    try {
      Thread.sleep(rnd.nextInt(1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.printf("%s wanna eat%n", name);
  }

  public void run() {
    while (!getStopFlag()) {
      think();
      synchronized (leftFork) {
        synchronized (rightFork) {
          if (rightFork.getOnTable() && leftFork.getOnTable()) {
            eat();
          }
        }
      }
    }
    System.out.printf("%s stopped%n", name);
  }

  private boolean getStopFlag() {
    synchronized (mutex()) {
      return stopFlag;
    }
  }

  public void setStopFlag(boolean stopFlag) {
    this.stopFlag = stopFlag;
  }

  private Mutex mutex() {
    Mutex mutex = volatileMutex;
    if (mutex == null) {
      synchronized (this) {
        mutex = volatileMutex;
        if (mutex == null) {
          mutex = new Mutex();
          volatileMutex = mutex;
        }
      }
    }
    return mutex;
  }
}
