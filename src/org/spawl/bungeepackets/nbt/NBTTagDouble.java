package org.spawl.bungeepackets.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagDouble extends NBTNumber
{
  private double data;

  NBTTagDouble()
  {
  }

  public NBTTagDouble(double paramDouble)
  {
    this.data = paramDouble;
  }

  void write(DataOutput paramDataOutput)
  {
    try {
		paramDataOutput.writeDouble(this.data);
	} catch (IOException e) {
		e.printStackTrace();
	}
  }

  void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter)
  {
    paramNBTReadLimiter.a(64L);
    try {
		this.data = paramDataInput.readDouble();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }

  public byte getTypeId()
  {
    return 6;
  }

  public String toString()
  {
    return "" + this.data + "d";
  }

  public NBTBase clone()
  {
    return new NBTTagDouble(this.data);
  }

  public boolean equals(Object paramObject)
  {
    if (super.equals(paramObject)) {
      NBTTagDouble localNBTTagDouble = (NBTTagDouble)paramObject;
      return this.data == localNBTTagDouble.data;
    }
    return false;
  }

  public int hashCode()
  {
    long l = Double.doubleToLongBits(this.data);
    return super.hashCode() ^ (int)(l ^ l >>> 32);
  }

  public double g()
  {
    return this.data;
  }

  public float h()
  {
    return (float)this.data;
  }

@Override
public long c() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int d() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public short e() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public byte f() {
	// TODO Auto-generated method stub
	return 0;
}
}