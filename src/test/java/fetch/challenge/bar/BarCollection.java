package fetch.challenge.bar;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BarCollection {

  public enum OneThirdNumber {
    FIRST,
    SECOND,
    THIRD
  }

  private List<Bar> bars;

  public BarCollection(int barCount) {
    init(barCount);
  }

  private void init(int barCount) {
    bars = IntStream.range(0, barCount).boxed().map(Bar::new).toList();
  }

  public List<Bar> getOneThirdOfBars(OneThirdNumber oneThirdNumber) {
    int size = bars.size() / 3;
    int skipCount = oneThirdNumber.ordinal() * size;

    return bars.stream().skip(skipCount).limit(size).toList();
  }

  public void keepOneThirdOfBars(OneThirdNumber oneThirdNumber) {
    bars = getOneThirdOfBars(oneThirdNumber);
    log.info("Bars kept: {}", bars);
  }

  public int getBarIndex(int i) {
    return bars.get(i).getIndex();
  }

  @Override
  public String toString() {
    return bars.stream().map(Bar::toString).collect(Collectors.joining(", "));
  }


}
