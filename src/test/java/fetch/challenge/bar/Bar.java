package fetch.challenge.bar;

import lombok.Getter;

@Getter
public class Bar {

  private int index;

  public Bar(int index) {
    this.index = index;
  }

  @Override
  public String toString() {
    return String.format("Bar-%d", index);
  }
}
