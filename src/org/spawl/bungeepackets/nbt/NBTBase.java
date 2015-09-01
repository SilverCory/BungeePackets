package org.spawl.bungeepackets.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class NBTBase
{
  public static final String[] a = { "END", "BYTE", "SHORT", "INT", "LONG", "FLOAT", "DOUBLE", "BYTE[]", "STRING", "LIST", "COMPOUND", "INT[]" };

  abstract void write(DataOutput paramDataOutput)
    throws IOException;

  abstract void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) throws IOException;

  public abstract String toString();

  public abstract byte getTypeId();

  protected static NBTBase createTag(byte b0)
  {
    switch (b0) {
    case 0:
      return new NBTTagEnd();
    case 1:
      return new NBTTagByte();
    case 2:
      return new NBTTagShort();
    case 3:
      return new NBTTagInt();
    case 4:
      return new NBTTagLong();
    case 5:
      return new NBTTagFloat();
    case 6:
      return new NBTTagDouble();
    case 7:
      return new NBTTagByteArray();
    case 8:
      return new NBTTagString();
    case 9:
      return new NBTTagList();
    case 10:
      return new NBTTagCompound();
    case 11:
      return new NBTTagIntArray();
    }

    return null;
  }

  public abstract NBTBase clone();

  public boolean isEmpty()
  {
    return false;
  }

  public boolean equals(Object object) {
    if (!(object instanceof NBTBase)) {
      return false;
    }
    NBTBase nbtbase = (NBTBase)object;

    return getTypeId() == nbtbase.getTypeId();
  }

  public int hashCode()
  {
    return getTypeId();
  }

  protected String a_() {
    return toString();
  }
}