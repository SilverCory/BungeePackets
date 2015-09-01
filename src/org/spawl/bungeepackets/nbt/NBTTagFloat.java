package org.spawl.bungeepackets.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagFloat extends NBTNumber
{
  private float data;

  NBTTagFloat()
  {
  }

  public NBTTagFloat(float paramFloat)
  {
    this.data = paramFloat;
  }

  void write(DataOutput paramDataOutput)
  {
    try {
		paramDataOutput.writeFloat(this.data);
	} catch (IOException e) {
		e.printStackTrace();
	}
  }

  void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter)
  {
    paramNBTReadLimiter.a(32L);
    try {
		this.data = paramDataInput.readFloat();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }

  public byte getTypeId()
  {
    return 5;
  }

  public String toString()
  {
    return "" + this.data + "f";
  }

  public NBTBase clone()
  {
    return new NBTTagFloat(this.data);
  }

  public boolean equals(Object paramObject)
  {
    if (super.equals(paramObject)) {
      NBTTagFloat localNBTTagFloat = (NBTTagFloat)paramObject;
      return this.data == localNBTTagFloat.data;
    }
    return false;
  }

  public int hashCode()
  {
    return super.hashCode() ^ Float.floatToIntBits(this.data);
  }

  public long c()
  {
    return (long) this.data;
  }

  public int d()
  {
    return (int) this.data;
  }

  public short e()
  {
    return (short) this.data;
  }

  public byte f()
  {
    return (byte) this.data;
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