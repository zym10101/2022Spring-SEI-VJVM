package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;

import java.io.DataInput;

/**
 * @author zym
 * @date 2022/05/11 14:16
 **/
public class StringConstant extends Constant {
  @Getter
  private final int strIdx;
  private final JClass jClass;
  private String value;


  @SneakyThrows
  StringConstant(DataInput input, JClass jClass) {
    strIdx = input.readUnsignedShort();
    this.jClass = jClass;
  }

  public String getValue() {
    if (value == null) {
      UTF8Constant constant = (UTF8Constant) jClass.constantPool().constant(strIdx);
      value = constant.value();
    }
    return value;
  }

  @Override
  public String toString() {
    return String.format("String: \"%s\"", getValue());
  }
}
