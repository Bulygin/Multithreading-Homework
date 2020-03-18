package Multithreading.Bank;

public class Bank {

  private volatile int moneyAmount;

  public Bank(int moneyAmount) {
    this.moneyAmount = moneyAmount;
  }

  public synchronized int getMoneyAmount() {
    return moneyAmount;
  }

  synchronized void transferMoney(int amount) throws NotEnoughMoneyException {
    if (moneyAmount < amount) {
      throw new NotEnoughMoneyException();
    }
    moneyAmount -= amount;
  }

  synchronized boolean hasEnoughMoney(int amount) {
    return moneyAmount > amount;
  }
}


