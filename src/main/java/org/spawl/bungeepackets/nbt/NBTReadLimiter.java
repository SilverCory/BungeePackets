package org.spawl.bungeepackets.nbt;

public class NBTReadLimiter
{
  public static final NBTReadLimiter a = new NBTReadLimiterUnlimited(0L);
  private final long b;
  private long c;

  public NBTReadLimiter(long paramLong)
  {
    this.b = paramLong;
  }

  public void a(long paramLong) {
    this.c += paramLong / 8L;
    if (this.c > this.b)
      throw new RuntimeException("Tried to read NBT tag that was too big; tried to allocate: " + this.c + "bytes where max allowed: " + this.b);
  }
}