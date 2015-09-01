package org.spawl.bungeepackets.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

public class NBTTagCompound extends NBTBase
{
	private Map<String, NBTBase> map = Maps.newHashMap();

	void write(DataOutput paramDataOutput)
	{
		for (String str : this.map.keySet()) {
			NBTBase localNBTBase = (NBTBase)this.map.get(str);
			a(str, localNBTBase, paramDataOutput);
		}
		
		try {
			paramDataOutput.writeByte(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter)
	{
		if (paramInt > 512) {
			throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
		}
		this.map.clear();
		byte b1;
		while ((b1 = a(paramDataInput, paramNBTReadLimiter)) != 0) {
			String str = b(paramDataInput, paramNBTReadLimiter);

			paramNBTReadLimiter.a(16 * str.length());
			NBTBase localNBTBase = a(b1, str, paramDataInput, paramInt + 1, paramNBTReadLimiter);
			this.map.put(str, localNBTBase);
		}
	}

	public Set<String> c() {
		return this.map.keySet();
	}

	public byte getTypeId()
	{
		return 10;
	}

	public void set(String paramString, NBTBase paramNBTBase) {
		this.map.put(paramString, paramNBTBase);
	}

	public void setByte(String paramString, byte paramByte) {
		this.map.put(paramString, new NBTTagByte(paramByte));
	}

	public void setShort(String paramString, short paramShort) {
		this.map.put(paramString, new NBTTagShort(paramShort));
	}

	public void setInt(String paramString, int paramInt) {
		this.map.put(paramString, new NBTTagInt(paramInt));
	}

	public void setLong(String paramString, long paramLong) {
		this.map.put(paramString, new NBTTagLong(paramLong));
	}

	public void setFloat(String paramString, float paramFloat) {
		this.map.put(paramString, new NBTTagFloat(paramFloat));
	}

	public void setDouble(String paramString, double paramDouble) {
		this.map.put(paramString, new NBTTagDouble(paramDouble));
	}

	public void setString(String paramString1, String paramString2) {
		this.map.put(paramString1, new NBTTagString(paramString2));
	}

	public void setByteArray(String paramString, byte[] paramArrayOfByte) {
		this.map.put(paramString, new NBTTagByteArray(paramArrayOfByte));
	}

	public void setIntArray(String paramString, int[] paramArrayOfInt) {
		this.map.put(paramString, new NBTTagIntArray(paramArrayOfInt));
	}

	public void setBoolean(String paramString, boolean paramBoolean) {
		setByte(paramString, (byte)(paramBoolean ? 1 : 0));
	}

	public NBTBase get(String paramString) {
		return (NBTBase)this.map.get(paramString);
	}

	public byte b(String paramString) {
		NBTBase localNBTBase = (NBTBase)this.map.get(paramString);
		if (localNBTBase != null) {
			return localNBTBase.getTypeId();
		}
		return 0;
	}

	public boolean hasKey(String paramString) {
		return this.map.containsKey(paramString);
	}

	public boolean hasKeyOfType(String paramString, int paramInt) {
		int i = b(paramString);
		if (i == paramInt) {
			return true;
		}
		if (paramInt == 99) {
			return (i == 1) || (i == 2) || (i == 3) || (i == 4) || (i == 5) || (i == 6);
		}

		if (i > 0);
		return false;
	}

	public byte getByte(String paramString) {
		try {
			if (!hasKeyOfType(paramString, 99)) {
				return 0;
			}
			return ((NBTNumber)this.map.get(paramString)).f(); } catch (ClassCastException localClassCastException) {
			}
		return 0;
	}

	public short getShort(String paramString)
	{
		try {
			if (!hasKeyOfType(paramString, 99)) {
				return 0;
			}
			return ((NBTNumber)this.map.get(paramString)).e(); } catch (ClassCastException localClassCastException) {
			}
		return 0;
	}

	public int getInt(String paramString)
	{
		try {
			if (!hasKeyOfType(paramString, 99)) {
				return 0;
			}
			return ((NBTNumber)this.map.get(paramString)).d(); } catch (ClassCastException localClassCastException) {
			}
		return 0;
	}

	public long getLong(String paramString)
	{
		try {
			if (!hasKeyOfType(paramString, 99)) {
				return 0L;
			}
			return ((NBTNumber)this.map.get(paramString)).c(); } catch (ClassCastException localClassCastException) {
			}
		return 0L;
	}

	public float getFloat(String paramString)
	{
		try {
			if (!hasKeyOfType(paramString, 99)) {
				return 0.0F;
			}
			return ((NBTNumber)this.map.get(paramString)).h(); } catch (ClassCastException localClassCastException) {
			}
		return 0.0F;
	}

	public double getDouble(String paramString)
	{
		try {
			if (!hasKeyOfType(paramString, 99)) {
				return 0.0D;
			}
			return ((NBTNumber)this.map.get(paramString)).g(); } catch (ClassCastException localClassCastException) {
			}
		return 0.0D;
	}

	public String getString(String paramString)
	{
		try {
			if (!hasKeyOfType(paramString, 8)) {
				return "";
			}
			return ((NBTBase)this.map.get(paramString)).a_(); } catch (ClassCastException localClassCastException) {
			}
		return "";
	}

	public byte[] getByteArray(String paramString)
	{
		try {
			if (!hasKeyOfType(paramString, 7)) {
				return new byte[0];
			}
			return ((NBTTagByteArray)this.map.get(paramString)).c();
		} catch (ClassCastException localClassCastException) {
			localClassCastException.printStackTrace();
		}
		return null;
	}

	public int[] getIntArray(String paramString) {
		try {
			if (!hasKeyOfType(paramString, 11)) {
				return new int[0];
			}
			return ((NBTTagIntArray)this.map.get(paramString)).c();
		} catch (ClassCastException localClassCastException) {
			localClassCastException.printStackTrace();
		}
		return null;
	}

	public NBTTagCompound getCompound(String paramString) {
		try {
			if (!hasKeyOfType(paramString, 10)) {
				return new NBTTagCompound();
			}
			return (NBTTagCompound)this.map.get(paramString);
		} catch (ClassCastException localClassCastException) {
			localClassCastException.printStackTrace();
		}
		return null;
	}
	
	public NBTTagList getList(String string) {
		try {
			NBTTagList localNBTTagList = (NBTTagList)this.map.get(string);
			return localNBTTagList;
		} catch (ClassCastException localClassCastException) {
			localClassCastException.printStackTrace();
		}
		return null;
	}

	public NBTTagList getList(String paramString, int paramInt) {
		try {
			if (b(paramString) != 9) {
				return new NBTTagList();
			}
			NBTTagList localNBTTagList = (NBTTagList)this.map.get(paramString);
			if ((localNBTTagList.size() > 0) && (localNBTTagList.f() != paramInt)) {
				return new NBTTagList();
			}
			return localNBTTagList;
		} catch (ClassCastException localClassCastException) {
			localClassCastException.printStackTrace();
		}
		return null;
	}

	public boolean getBoolean(String paramString) {
		return getByte(paramString) != 0;
	}

	public void remove(String paramString) {
		this.map.remove(paramString);
	}

	public String toString()
	{
		String str1 = "{";
		for (String str2 : this.map.keySet()) {
			str1 = str1 + str2 + ':' + this.map.get(str2) + ',';
		}
		return str1 + "}";
	}

	public boolean isEmpty()
	{
		return this.map.isEmpty();
	}

	public NBTBase clone()
	{
		NBTTagCompound localNBTTagCompound = new NBTTagCompound();
		for (String str : this.map.keySet()) {
			localNBTTagCompound.set(str, ((NBTBase)this.map.get(str)).clone());
		}
		return localNBTTagCompound;
	}

	public boolean equals(Object paramObject)
	{
		if (super.equals(paramObject)) {
			NBTTagCompound localNBTTagCompound = (NBTTagCompound)paramObject;
			return this.map.entrySet().equals(localNBTTagCompound.map.entrySet());
		}
		return false;
	}

	public int hashCode()
	{
		return super.hashCode() ^ this.map.hashCode();
	}

	private static void a(String paramString, NBTBase paramNBTBase, DataOutput paramDataOutput) {
		try {
			paramDataOutput.writeByte(paramNBTBase.getTypeId());
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (paramNBTBase.getTypeId() == 0) {
			return;
		}

		try {
			paramDataOutput.writeUTF(paramString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			paramNBTBase.write(paramDataOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static byte a(DataInput paramDataInput, NBTReadLimiter paramNBTReadLimiter) {
		try {
			return paramDataInput.readByte();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private static String b(DataInput paramDataInput, NBTReadLimiter paramNBTReadLimiter) {
		try {
			return paramDataInput.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	static NBTBase a(byte paramByte, String paramString, DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) {
		NBTBase localNBTBase = NBTBase.createTag(paramByte);
		try
		{
			localNBTBase.load(paramDataInput, paramInt, paramNBTReadLimiter);
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
		}

		return localNBTBase;
	}

	public void a(NBTTagCompound paramNBTTagCompound)
	{
		for (String str : paramNBTTagCompound.map.keySet()) {
			NBTBase localNBTBase = (NBTBase)paramNBTTagCompound.map.get(str);

			if (localNBTBase.getTypeId() == 10) {
				if (hasKeyOfType(str, 10)) {
					NBTTagCompound localNBTTagCompound = getCompound(str);
					localNBTTagCompound.a((NBTTagCompound)localNBTBase);
				} else {
					set(str, localNBTBase.clone());
				}
			}
			else set(str, localNBTBase.clone());
		}
	}
}