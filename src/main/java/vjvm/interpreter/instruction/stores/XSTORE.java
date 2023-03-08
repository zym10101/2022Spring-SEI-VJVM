package vjvm.interpreter.instruction.stores;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;


/**
 * @author zym
 * @date 2022/06/05 09:32
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XSTORE extends Instruction {
  private int index;
  private String name;

  public static final XSTORE ISTORE(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(pc.byte_(), "istore");
  }

  public static final XSTORE LSTORE(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(pc.byte_(), "lstore");
  }

  public static final XSTORE FSTORE(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(pc.byte_(), "fstore");
  }

  public static final XSTORE DSTORE(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(pc.byte_(), "dstore");
  }

  public static final XSTORE ISTORE_0(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(0, "istore_0");
  }

  public static final XSTORE ISTORE_1(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(1, "istore_1");
  }

  public static final XSTORE ISTORE_2(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(2, "istore_2");
  }

  public static final XSTORE ISTORE_3(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(3, "istore_3");
  }

  public static final XSTORE LSTORE_0(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(0, "lstore_0");
  }

  public static final XSTORE LSTORE_1(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(1, "lstore_1");
  }

  public static final XSTORE LSTORE_2(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(2, "lstore_2");
  }

  public static final XSTORE LSTORE_3(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(3, "lstore_3");
  }

  public static final XSTORE FSTORE_0(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(0, "fstore_0");
  }

  public static final XSTORE FSTORE_1(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(1, "fstore_1");
  }

  public static final XSTORE FSTORE_2(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(2, "fstore_2");
  }

  public static final XSTORE FSTORE_3(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(3, "fstore_3");
  }

  public static final XSTORE DSTORE_0(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(0, "dstore_0");
  }

  public static final XSTORE DSTORE_1(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(1, "dstore_1");
  }

  public static final XSTORE DSTORE_2(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(2, "dstore_2");
  }

  public static final XSTORE DSTORE_3(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(3, "dstore_3");
  }


  @Override
  public void run(JThread thread) {
    var top = thread.top();
    var stack = top.stack();
    Slots slots = top.vars();
    if (name.charAt(0) == 'i') {
      slots.int_(index, stack.popInt());
    } else if (name.charAt(0) == 'l') {
      slots.long_(index, stack.popLong());
    } else if (name.charAt(0) == 'f') {
      slots.float_(index, stack.popFloat());
    } else if (name.charAt(0) == 'd') {
      slots.double_(index, stack.popDouble());
    }
  }

  @Override
  public String toString() {
    return String.format("%s %d", name, index);
  }
}
