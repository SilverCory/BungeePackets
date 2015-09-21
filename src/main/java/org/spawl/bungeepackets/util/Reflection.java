package org.spawl.bungeepackets.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

public class Reflection {
	
	public static void set(Object main_object, String field, Object value)
	{
		try{
			Field f = main_object.getClass().getDeclaredField(field);
			f.setAccessible(true);
			f.set(main_object, value);
		}catch(Exception e)
		{e.printStackTrace();}
	}

	public static void set(Class<?> c, Object main_object, String field, Object value)
	{
		try{
			Field f = c.getDeclaredField(field);
			f.setAccessible(true);
			f.set(main_object, value);
		}catch(Exception e)
		{e.printStackTrace();}
	}

	public static Object get(Object c, String field)
	{
		try{
			Field f = c.getClass().getDeclaredField(field);
			f.setAccessible(true);
			return f.get(c);
		}catch(Exception e)
		{e.printStackTrace();}
		return null;
	}

	public static Object get(Class<?> cl, Object c, String field)
	{
		try{
			Field f = cl.getDeclaredField(field);
			f.setAccessible(true);
			return f.get(c);
		}catch(Exception e)
		{e.printStackTrace();}
		return null;
	}

	public static void printFields(Class<?> c) {
		printFieldsWithValues(c, null);
	}

	public static void printFieldsWithValues(Class<?> c, Object parent) {
		System.out.println("==========================================");
		System.out.println("Printing fields for '"+c.getSimpleName()+"'");
		System.out.println(" ");
		System.out.println("NAME | TYPE | VALUE");
		for(Field f : c.getDeclaredFields()) {
			f.setAccessible(true);
			try {
				Object value = "Unknown";
				if(parent != null)
				{
					if(f != null) {
						Object o = f.get(parent);
						if(o != null)
							value = o;
					}
				}
				String append = "";
				if(f.getType().getSimpleName().equalsIgnoreCase("byte[]")) {
					append = " (Decompiled: "+new String(((byte[])value), "UTF-8")+")";
				}
				System.out.println(f.getName()+" | "+f.getType().getSimpleName()+" | "+value.toString()+append);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("==========================================");
	}

}
