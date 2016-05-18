package com.just.weike.Service.impl;

import java.util.ArrayList;
import java.util.List;

import com.just.weike.Service.IUIupgdate;

public class ObserverService {
	
	private static List<IUIupgdate> list = new ArrayList<IUIupgdate>();
	
	public static void Add(IUIupgdate object)
	{
		list.add(object);
	}
	
	public static void Remove(IUIupgdate object)
	{
		list.remove(object);
	}
	
	public static void Notify()
	{
		for(IUIupgdate object:list)
			object.defaultupdate();
	}
	
}
