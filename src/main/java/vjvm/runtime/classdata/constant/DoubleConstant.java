package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

/**
 * @author zym
 * @date 2022/05/11 14:14
 **/
public class DoubleConstant extends Constant {
  @Getter
  private final double value;

  @SneakyThrows
  DoubleConstant(DataInput input) {
    value = input.readDouble();
  }

  @Override
  public String toString() {
    return String.format("Double: %a", value);
  }
}
