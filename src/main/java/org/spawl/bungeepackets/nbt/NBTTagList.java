package org.spawl.bungeepackets.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.List;

import com.google.common.collect.Lists;

public class NBTTagList extends NBTBase
{
	private List<NBTBase> list = Lists.newArrayList();
	private byte type = 0;

	void write(DataOutput paramDataOutput)
	{
		if (!this.list.isEmpty())
			this.type = ((NBTBase)this.list.get(0)).getTypeId();
		else {
			this.type = 0;
		}

		try{
		paramDataOutput.writeByte(this.type);
		paramDataOutput.writeInt(this.list.size());
		for (int i = 0; i < this.list.size(); i++)
			((NBTBase)this.list.get(i)).write(paramDataOutput);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter)
	{
		if (paramInt > 512) {
			throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
		}
		paramNBTReadLimiter.a(8L);
		try{
		this.type = paramDataInput.readByte();
		int i = paramDataInput.readInt();

		this.list = Lists.newArrayList();
		for (int j = 0; j < i; j++) {
			NBTBase localNBTBase = NBTBase.createTag(this.type);
			localNBTBase.load(paramDataInput, paramInt + 1, paramNBTReadLimiter);
			this.list.add(localNBTBase);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public byte getTypeId()
	{
		return 9;
	}

	public String toString()
	{
		String str = "[";
		int i = 0;
		for (NBTBase localNBTBase : this.list) {
			str = str + "" + i + ':' + localNBTBase + ',';
			i++;
		}
		return str + "]";
	}

	public void add(NBTBase paramNBTBase)
	{
		if (this.type == 0) {
			this.type = paramNBTBase.getTypeId();
		} else if (this.type != paramNBTBase.getTypeId()) {
			return;
		}
		this.list.add(paramNBTBase);
	}

	public void a(int paramInt, NBTBase paramNBTBase) {
		if ((paramInt < 0) || (paramInt >= this.list.size())) {
			return;
		}
		if (this.type == 0) {
			this.type = paramNBTBase.getTypeId();
		} else if (this.type != paramNBTBase.getTypeId()) {
			return;
		}
		this.list.set(paramInt, paramNBTBase);
	}

	public NBTBase a(int paramInt) {
		return (NBTBase)this.list.remove(paramInt);
	}

	public boolean isEmpty()
	{
		return this.list.isEmpty();
	}

	public NBTTagCompound get(int paramInt) {
		if ((paramInt < 0) || (paramInt >= this.list.size())) {
			return new NBTTagCompound();
		}
		NBTBase localNBTBase = (NBTBase)this.list.get(paramInt);
		if (localNBTBase.getTypeId() == 10) {
			return (NBTTagCompound)localNBTBase;
		}
		return new NBTTagCompound();
	}

	public int[] c(int paramInt) {
		if ((paramInt < 0) || (paramInt >= this.list.size())) {
			return new int[0];
		}
		NBTBase localNBTBase = (NBTBase)this.list.get(paramInt);
		if (localNBTBase.getTypeId() == 11) {
			return ((NBTTagIntArray)localNBTBase).c();
		}
		return new int[0];
	}

	public double d(int paramInt) {
		if ((paramInt < 0) || (paramInt >= this.list.size())) {
			return 0.0D;
		}
		NBTBase localNBTBase = (NBTBase)this.list.get(paramInt);
		if (localNBTBase.getTypeId() == 6) {
			return ((NBTTagDouble)localNBTBase).g();
		}
		return 0.0D;
	}

	public float e(int paramInt) {
		if ((paramInt < 0) || (paramInt >= this.list.size())) {
			return 0.0F;
		}
		NBTBase localNBTBase = (NBTBase)this.list.get(paramInt);
		if (localNBTBase.getTypeId() == 5) {
			return ((NBTTagFloat)localNBTBase).h();
		}
		return 0.0F;
	}

	public String getString(int paramInt) {
		if ((paramInt < 0) || (paramInt >= this.list.size())) {
			return "";
		}
		NBTBase localNBTBase = (NBTBase)this.list.get(paramInt);
		if (localNBTBase.getTypeId() == 8) {
			return localNBTBase.a_();
		}
		return localNBTBase.toString();
	}

	public NBTBase g(int paramInt) {
		if ((paramInt < 0) || (paramInt >= this.list.size())) {
			return new NBTTagEnd();
		}
		return (NBTBase)this.list.get(paramInt);
	}

	public int size() {
		return this.list.size();
	}

	public NBTBase clone()
	{
		NBTTagList localNBTTagList = new NBTTagList();
		localNBTTagList.type = this.type;
		for (NBTBase localNBTBase1 : this.list) {
			NBTBase localNBTBase2 = localNBTBase1.clone();
			localNBTTagList.list.add(localNBTBase2);
		}
		return localNBTTagList;
	}

	public boolean equals(Object paramObject)
	{
		if (super.equals(paramObject)) {
			NBTTagList localNBTTagList = (NBTTagList)paramObject;
			if (this.type == localNBTTagList.type) {
				return this.list.equals(localNBTTagList.list);
			}
		}
		return false;
	}

	public int hashCode()
	{
		return super.hashCode() ^ this.list.hashCode();
	}

	public int f() {
		return this.type;
	}
}