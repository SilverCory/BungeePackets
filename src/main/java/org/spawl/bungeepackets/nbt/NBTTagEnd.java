package org.spawl.bungeepackets.nbt;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagEnd extends NBTBase
{
  void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter)
  {
  }

  void write(DataOutput paramDataOutput)
  {
  }

  public byte getTypeId()
  {
    return 0;
  }

  public String toString()
  {
    return "END";
  }

  public NBTBase clone()
  {
    return new NBTTagEnd();
  }
}