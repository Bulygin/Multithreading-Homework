package Multithreading.Consumer;

public class Consumer implements Runnable {

  private boolean isStop = false;

  @Override
  public void run() {
    while (!isStop) {
      Object removed = QueueEntity.concurrentLinkedQueue.pollFirst();
      if (removed != null) {
        System.out.println("Was removed: " + removed.toString());
      } else {
        System.out.println("Nothing to remove");
      }
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void stopConsumer() {
    isStop = !isStop;
  }
}
