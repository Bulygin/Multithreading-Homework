package Multithreading.Consumer;

public class Producer implements Runnable {

  private boolean isStop = false;

  @Override
  public void run() {
    while (!isStop) {
      Object object = new Object();
      QueueEntity.concurrentLinkedQueue.addLast(object);
      System.out.println("Was created: " + object.toString());
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void stopProducer() {
    isStop = !isStop;
  }
}
