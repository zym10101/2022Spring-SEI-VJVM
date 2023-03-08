package vjvm.interpreter.instruction.loads;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JFrame;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;

/**
 * @author zym
 * @date 2022/06/05 12:36
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XLOAD extends Instruction {
  private int index;
  private String name;

  public static final XLOAD ILOAD(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(pc.ubyte(), "iload");
  }

  public static final XLOAD LLOAD(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(pc.ubyte(), "lload");
  }

  public static final XLOAD FLOAD(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(pc.ubyte(), "fload");
  }

  public static final XLOAD DLOAD(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(pc.ubyte(), "dload");
  }

  public static final XLOAD ILOAD_0(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(0, "iload_0");
  }

  public static final XLOAD ILOAD_1(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(1, "iload_1");
  }

  public static final XLOAD ILOAD_2(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(2, "iload_2");
  }

  public static final XLOAD ILOAD_3(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(3, "iload_3");
  }

  public static final XLOAD LLOAD_0(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(0, "lload_0");
  }

  public static final XLOAD LLOAD_1(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(1, "lload_1");
  }

  public static final XLOAD LLOAD_2(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(2, "lload_2");
  }

  public static final XLOAD LLOAD_3(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(3, "lload_3");
  }

  public static final XLOAD FLOAD_0(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(0, "fload_0");
  }

  public static final XLOAD FLOAD_1(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(1, "fload_1");
  }

  public static final XLOAD FLOAD_2(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(2, "fload_2");
  }

  public static final XLOAD FLOAD_3(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(3, "fload_3");
  }

  public static final XLOAD DLOAD_0(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(0, "dload_0");
  }

  public static final XLOAD DLOAD_1(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(1, "dload_1");
  }

  public static final XLOAD DLOAD_2(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(2, "dload_2");
  }

  public static final XLOAD DLOAD_3(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(3, "dload_3");
  }


  @Override
  public void run(JThread thread) {
    var top = thread.top();
    var stack = top.stack();
    Slots slots = top.vars();
    if (name.charAt(0) == 'i') {
      stack.pushInt(slots.int_(index));
    } else if (name.charAt(0) == 'l') {
      stack.pushLong(slots.long_(index));
    } else if (name.charAt(0) == 'f') {
      stack.pushFloat(slots.float_(index));
    } else if (name.charAt(0) == 'd') {
      stack.pushDouble(slots.double_(index));
    }
  }

  @Override
  public String toString() {
    if (name.contains("_")) {
      return name;
    }
    return String.format("%s %d", name, index);
  }
}
