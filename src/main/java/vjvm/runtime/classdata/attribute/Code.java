package vjvm.runtime.classdata.attribute;

import lombok.var;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.utils.UnimplementedError;

import java.io.DataInput;

@Getter
public class Code extends Attribute {
  private final int maxStack;
  private final int maxLocals;
  private final byte[] code; // the bytecode represented as raw bytes
  private final Attribute[] attributes;

  @SneakyThrows
  Code(DataInput input, ConstantPool constantPool) {
    // construct code
    maxStack = input.readUnsignedShort();
    maxLocals = input.readUnsignedShort();
    int codelength = input.readInt();
    code = new byte[codelength];
    for (int i = 0; i < codelength; i++) {
      code[i] = input.readByte();
    }
    int exceptionTableLength = input.readUnsignedShort();
    for (int i = 0; i < exceptionTableLength; i++) {
      input.readByte();
    }
    int attributesNum = input.readUnsignedShort();
    attributes = new Attribute[attributesNum];
    for (int i = 0; i < attributesNum; i++) {
      attributes[i] = Attribute.constructFromData(input, constantPool);
    }
  }
}
