package Multithreading;

import Multithreading.Consumer.Consumer;
import Multithreading.Consumer.Producer;
import org.junit.Assert;
import org.junit.Test;

public class ConsumerTest {

  @Test
  public void Consumer_ProducerCreatesItemQueueConsumerDeletes_NotException() {
    Consumer consumer = new Consumer();
    Producer producer = new Producer();
    Thread consumerThread = new Thread(consumer);
    Thread producerThread = new Thread(producer);
    consumerThread.start();
    producerThread.start();

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    consumer.stopConsumer();
    producer.stopProducer();

    try {
      consumerThread.join();
      producerThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Assert.assertTrue(!consumerThread.isAlive() && !producerThread.isAlive());
  }

}