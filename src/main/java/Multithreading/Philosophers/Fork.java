package Multithreading.Philosophers;

public class Fork {

  private String name;
  private Boolean onTable = true;

  public Fork(String name) {
    this.name = name;
  }

  String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  Boolean getOnTable() {
    return onTable;
  }

  void setOnTable(Boolean onTable) {
    this.onTable = onTable;
  }
}
