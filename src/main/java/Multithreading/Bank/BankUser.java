package Multithreading.Bank;

import java.util.Random;

public class BankUser extends Thread {

  private Bank bank;
  private String name;
  private Random rnd = new Random();


  public BankUser(Bank bank, String name) {
    super(name);
    this.bank = bank;
    this.name = name;
  }

  @Override
  public void run() {
    try {
      while (true) {
        Thread.sleep(1000);
        int amount = rnd.nextInt(1000);
        if (bank.hasEnoughMoney(amount)) {
          bank.transferMoney(amount);
          System.out.printf("%s took: %d, Stay in bank: %d%n", name, amount, bank.getMoneyAmount());
        } else {

          System.out
              .printf("%s try to take: %d, but not enough money, thread will be stopped%n", name,
                  amount);
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

