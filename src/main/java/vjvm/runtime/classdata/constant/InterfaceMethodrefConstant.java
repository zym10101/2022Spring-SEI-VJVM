package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;
import vjvm.runtime.JClass;

import java.io.DataInput;

/**
 * @author zym
 * @date 2022/05/17 20:12
 **/
public class InterfaceMethodrefConstant extends Constant {
  private final JClass jClass;
  private final int classIdx;
  private final int nameAndTypeIdx;
  private String clazz;
  private String name;
  private String type;

  @SneakyThrows
  InterfaceMethodrefConstant(DataInput dataInput, JClass jClass) {
    classIdx = dataInput.readUnsignedShort();
    nameAndTypeIdx = dataInput.readUnsignedShort();
    this.jClass = jClass;
  }

  public String className() {
    if (clazz == null) {
      ClassConstant constant = (ClassConstant) jClass.constantPool().constant(classIdx);
      clazz = constant.getName();
    }
    return clazz;
  }

  public String getName() {
    if (name == null) {
      NameAndTypeConstant constant = (NameAndTypeConstant) jClass.constantPool().constant(nameAndTypeIdx);
      name = constant.name();
    }
    return name;
  }

  public String getType() {
    if (type == null) {
      NameAndTypeConstant constant = (NameAndTypeConstant) jClass.constantPool().constant(nameAndTypeIdx);
      type = constant.type();
    }
    return type;
  }

  @Override
  public String toString() {
    return String.format("InterfaceMethodref: %s.%s:%s", className(), getName(), getType());
  }
}
