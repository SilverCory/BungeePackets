package org.spawl.bungeepackets.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagByte extends NBTNumber
{
  private byte data;

  NBTTagByte()
  {
  }

  public NBTTagByte(byte paramByte)
  {
    this.data = paramByte;
  }

  void write(DataOutput paramDataOutput)
  {
    try {
		paramDataOutput.writeByte(this.data);
	} catch (IOException e) {
		e.printStackTrace();
	}
  }

  void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter)
  {
    paramNBTReadLimiter.a(8L);
    try {
		this.data = paramDataInput.readByte();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }

  public byte getTypeId()
  {
    return 1;
  }

  public String toString()
  {
    return "" + this.data + "b";
  }

  public NBTBase clone()
  {
    return new NBTTagByte(this.data);
  }

  public boolean equals(Object paramObject)
  {
    if (super.equals(paramObject)) {
      NBTTagByte localNBTTagByte = (NBTTagByte)paramObject;
      return this.data == localNBTTagByte.data;
    }
    return false;
  }

  public int hashCode()
  {
    return super.hashCode() ^ this.data;
  }

  public long c()
  {
    return this.data;
  }

  public int d()
  {
    return this.data;
  }

  public short e()
  {
    return (short)this.data;
  }

  public byte f()
  {
    return this.data;
  }

  public double g()
  {
    return this.data;
  }

  public float h()
  {
    return this.data;
  }
}