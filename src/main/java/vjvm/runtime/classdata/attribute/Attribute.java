package vjvm.runtime.classdata.attribute;

import lombok.var;
import lombok.SneakyThrows;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.runtime.classdata.constant.Constant;
import vjvm.runtime.classdata.constant.UTF8Constant;

import java.io.DataInput;

public abstract class Attribute {

  @SneakyThrows
  public static Attribute constructFromData(DataInput input, ConstantPool constantPool) {
    var nameIndex = input.readUnsignedShort();
    var attrLength = Integer.toUnsignedLong(input.readInt());

    // detect and construct Code attribute
    String name = ((UTF8Constant) constantPool.constant(nameIndex)).value();
    if (name.equals("Code")) {
      return new Code(input, constantPool);
    }
    return new UnknownAttribute(input, attrLength);
  }
}
