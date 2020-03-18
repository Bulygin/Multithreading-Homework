package Multithreading.Singleton;

public class Singleton {

  private static volatile Singleton instance;

  static Singleton getInstance() {
    Singleton singleton = instance;
    if (singleton == null) {
      synchronized (Singleton.class) {
        singleton = instance;
        if (singleton == null) {
          instance = singleton = new Singleton();
        }
      }
    }
    return singleton;
  }
}
