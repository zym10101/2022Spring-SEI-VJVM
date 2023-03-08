package vjvm.runtime;

import java.util.Optional;

/**
 * Slots represents an array of JVM slots as defined in the specification. It
 * supports getting and putting primitive data types, including address.
 */
public class Slots {
  private int size;
  private Object[] slots;
  private String[] types;

  public Slots(int slotSize) {
    // TODO: initialize data structures
    size = slotSize;
    slots = new Object[size];
    types = new String[size];
  }

  public int int_(int index) {
    // TODO: return the int at index
    assert index >= 0 && index < size;
    assert types[index].equals("int");
    return (int) slots[index];
  }

  public void int_(int index, int value) {
    // TODO: set the int at index
    assert index >= 0 && index < size;
    types[index] = "int";
    slots[index] = value;
  }

  public long long_(int index) {
    // TODO: return the long at index
    assert index >= 0 && index < size;
    assert types[index].equals("long");
    return (long) slots[index];
  }

  public void long_(int index, long value) {
    // TODO: set the long at index
    assert index >= 0 && index < size;
    types[index] = "long";
    slots[index] = value;
  }

  public float float_(int index) {
    // TODO: return the float at index
    assert index >= 0 && index < size;
    assert types[index].equals("float");
    return (float) slots[index];
  }

  public void float_(int index, float value) {
    // TODO: set the float at index
    assert index >= 0 && index < size;
    types[index] = "float";
    slots[index] = value;
  }

  public double double_(int index) {
    // TODO: return the double at index
    assert index >= 0 && index < size;
    assert types[index].equals("double");
    return (double) slots[index];
  }


  public void double_(int index, double value) {
    // TODO: set the double at index
    assert index >= 0 && index < size;
    types[index] = "double";
    slots[index] = value;
  }

  public byte byte_(int index) {
    // TODO: return the byte at index
    assert index >= 0 && index < size;
    assert types[index].equals("int");
    return (byte) slots[index];
  }

  public void byte_(int index, byte value) {
    // TODO: set the byte at index
    assert index >= 0 && index < size;
    types[index] = "int";
    slots[index] = (int) value;
  }

  public char char_(int index) {
    // TODO: return the char at index
    assert index >= 0 && index < size;
    assert types[index].equals("int");
    return (char) (int) slots[index];
  }

  public void char_(int index, char value) {
    // TODO: set the char at index
    assert index >= 0 && index < size;
    types[index] = "int";
    slots[index] = (int) value;
  }

  public short short_(int index) {
    // TODO: return the short at index
    assert index >= 0 && index < size;
    assert types[index].equals("int");
    return (short) slots[index];
  }

  public void short_(int index, short value) {
    // TODO: set the short at index
    assert index >= 0 && index < size;
    types[index] = "int";
    slots[index] = (int) value;
  }

  public Optional<Object> value(int index) {
    // TODO(optional): return the value at index, or null if there is no value stored at index
    return Optional.empty();
  }

  public int size() {
    // TODO: return the size in the number of slots
    return size;
  }

  public void copyTo(int begin, int length, Slots dest, int destBegin) {
    // TODO: copy from this:[begin, begin+length) to dest:[destBegin,destBegin+length)
    System.arraycopy(slots, begin, dest.slots, destBegin, length);
    System.arraycopy(types, begin, dest.types, destBegin, length);
  }
}
