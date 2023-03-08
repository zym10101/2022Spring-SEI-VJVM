package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

/**
 * @author zym
 * @date 2022/05/11 14:18
 **/
public class FloatConstant extends Constant {
  @Getter
  private final float value;

  @SneakyThrows
  FloatConstant(DataInput input) {
    value = input.readFloat();
  }

  @Override
  public String toString() {
    return String.format("Float: %a", value);
  }
}
