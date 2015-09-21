package org.spawl.bungeepackets.nbt;

import io.netty.buffer.ByteBufInputStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class NBTCompressedStreamTools
{
	public static NBTTagCompound a(InputStream inputstream)
	{
		try
		{
			DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(inputstream)));
			NBTTagCompound nbttagcompound;
			try
			{
				nbttagcompound = a(datainputstream, NBTReadLimiter.a);
			}
			finally
			{
				datainputstream.close();
			}

			return nbttagcompound; } catch (IOException ex) {
				ex.printStackTrace(); } return null;
	}

	public static void a(NBTTagCompound nbttagcompound, OutputStream outputstream) {
		try {
			DataOutputStream dataoutputstream = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(outputstream)));
			try
			{
				a(nbttagcompound, (DataOutput)dataoutputstream);
			} finally {
				dataoutputstream.close();
			}
		} catch (IOException ex) { ex.printStackTrace(); }
	}

	public static NBTTagCompound a(DataInputStream datainputstream) {
		return a(datainputstream, NBTReadLimiter.a);
	}

	public static NBTTagCompound a(DataInput datainput, NBTReadLimiter nbtreadlimiter)
	{
		try {
			if ((datainput instanceof ByteBufInputStream))
			{
				datainput = new DataInputStream(new LimitStream((InputStream)datainput, nbtreadlimiter));
			}

			NBTBase nbtbase = a(datainput, 0, nbtreadlimiter);

			if ((nbtbase instanceof NBTTagCompound)) {
				return (NBTTagCompound)nbtbase;
			}
			throw new IOException("Root tag must be a named compound tag");
		} catch (IOException ex) {
			ex.printStackTrace(); } return null;
	}

	public static void a(NBTTagCompound nbttagcompound, DataOutput dataoutput) {
		a((NBTBase)nbttagcompound, dataoutput);
	}

	private static void a(NBTBase nbtbase, DataOutput dataoutput) {
		try {
			dataoutput.writeByte(nbtbase.getTypeId());
			if (nbtbase.getTypeId() != 0) {
				dataoutput.writeUTF("");
				nbtbase.write(dataoutput);
			}
		} catch (IOException ex) { ex.printStackTrace(); }
	}

	private static NBTBase a(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) {
		try {
			byte b0 = datainput.readByte();

			if (b0 == 0) {
				return new NBTTagEnd();
			}
			datainput.readUTF();
			NBTBase nbtbase = NBTBase.createTag(b0);
			try
			{
				nbtbase.load(datainput, i, nbtreadlimiter);
				return nbtbase;
			} catch (IOException ioexception) {
				ioexception.printStackTrace();
			}
		} catch (IOException ex) {
			ex.printStackTrace(); } return null;
	}
}