package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;

import java.io.DataInput;

/**
 * @author zym
 * @date 2022/05/11 14:53
 **/
public class ClassConstant extends Constant {
  @Getter
  private final int nameIdx;
  private final JClass jClass;
  private String clazz;

  @SneakyThrows
  ClassConstant(DataInput input, JClass jClass) {
    nameIdx = input.readUnsignedShort();
    this.jClass = jClass;
  }

  public String getName() {
    if (clazz == null) {
      clazz = ((UTF8Constant) jClass.constantPool().constant(nameIdx)).value();
    }
    return clazz;
  }

  @Override
  public String toString() {
    return String.format("Class: \"%s\"", getName());
  }
}
