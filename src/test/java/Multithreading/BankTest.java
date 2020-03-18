package Multithreading;

import static org.junit.Assert.assertTrue;

import Multithreading.Bank.Bank;
import Multithreading.Bank.BankUser;
import org.junit.Test;

public class BankTest {

  @Test
  public void Bank_FiveThreads_NotException() throws InterruptedException {
    Bank bank = new Bank(10000);
    BankUser user = null;
    for (int i = 0; i < 5; i++) {
      user = new BankUser(bank, "User" + i);
      user.start();
    }
    user.join();
    assertTrue(bank.getMoneyAmount() < 1000);
  }
}
