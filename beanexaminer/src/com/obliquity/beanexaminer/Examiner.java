/*
 * beanexaminer - a package for examining Java objects.
 *
 * Copyright (C) 2018 David Harper at obliquity.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 * 
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA.
 *
 * See the COPYING file located in the top-level-directory of
 * the archive of this library for complete text of license.
 */

package com.obliquity.beanexaminer;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

public class Examiner {	
	public static void examine(Object o, int depth, Handler h)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IntrospectionException {
		examine(null, o, depth, h, Integer.MAX_VALUE);
	}

	public static void examine(Object o, int depth, Handler h, int maxdepth)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IntrospectionException {
		examine(null, o, depth, h, maxdepth);
	}
	
	public static void examine(String name, Object o, int depth, Handler h)
			throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		examine(name, o, depth, h, Integer.MAX_VALUE);
	}

	public static void examine(String name, Object o, int depth, Handler h, int maxdepth)
			throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if (depth > maxdepth)
			return;
		
		h.beginObject(name, o, depth);
		
		examineObjectAsBean(o, depth, h);

		examineObjectAsCollection(o, depth, h);
		
		examineObjectAsArray(o, depth, h);
		
		h.endObject(o, depth);
	}
	
	private static void examineObjectAsBean(Object o, int depth, Handler h) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?> c = o.getClass();
		
		BeanInfo beaninfo = Introspector.getBeanInfo(c);

		PropertyDescriptor[] pds = beaninfo.getPropertyDescriptors();
			
		for (int i = 0; i < pds.length; i++) {
			displayProperty(o, pds[i], depth, h);
		}
	}
	
	private static void examineObjectAsCollection(Object o, int depth, Handler h) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IntrospectionException {
		if (o instanceof Collection) {
			Collection<?> coll = (Collection<?>)o;
			
			Iterator<?> iter = coll.iterator();
			
			h.beginCollection(o, depth+1);
			
			while (iter.hasNext())
				examine(iter.next(), depth+2, h);
			
			h.endCollection(o, depth+1);
		}	
	}
	
	private static void examineObjectAsArray(Object o, int depth, Handler h) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IntrospectionException {
		if (o.getClass().isArray()) {
			h.beginArray(o, depth+1);
			
			Object[] array = (Object[])o;
			
			for (Object element : array) {
				examine(element, depth+1, h);
			}
			
			h.endArray(o, depth+1);
		}
	}

	private static void displayProperty(Object o, PropertyDescriptor pd, int depth, Handler h)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IntrospectionException {
		String name = pd.getName();
		
		if (name.equalsIgnoreCase("class"))
			return;
		
		Method readMethod = pd.getReadMethod();
		
		Object value = (readMethod != null) ? readMethod.invoke(o, (Object[])null) : null;
		
		if (value == null || value.getClass().isPrimitive() || value.getClass() == String.class || hasToString(value))
			h.beanProperty(o, name, value, depth+1);
		else
			examine(name, value, depth+1, h);
	}
	
	private static boolean hasToString(Object o) {
		Class<?> c = o.getClass();

		try {
			Method m = c.getMethod("toString", (Class<?>[])null);
		
			return m.getDeclaringClass() == c;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}
}
