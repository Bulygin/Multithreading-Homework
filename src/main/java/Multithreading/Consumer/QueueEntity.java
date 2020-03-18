package Multithreading.Consumer;

import java.util.concurrent.ConcurrentLinkedDeque;

class QueueEntity {

  static ConcurrentLinkedDeque<Object> concurrentLinkedQueue = new ConcurrentLinkedDeque<>();
}
