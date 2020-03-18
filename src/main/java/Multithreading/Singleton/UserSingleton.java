package Multithreading.Singleton;

public class UserSingleton implements Runnable {

  private boolean isStop = false;
  private String name;
  private Singleton instance;

  public UserSingleton(String name) {
    this.name = name;
  }

  public Singleton getSingleton() {
    return instance;
  }

  public boolean isStop() {
    return isStop;
  }

  public void setStop(boolean stop) {
    isStop = stop;
  }

  @Override
  public void run() {
    while (!isStop) {
      instance = Singleton.getInstance();
      if (instance != null) {
        System.out.println(name + " create singleton" + instance.toString());
      }
    }
  }
}
