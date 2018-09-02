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

import java.io.PrintStream;

public class DefaultHandler implements Handler {
	private PrintStream ps = System.out;
	
	public void setPrintStream(PrintStream ps) {
		this.ps = ps;
	}
	
	public PrintStream getPrintStream() {
		return ps;
	}
	
	private void indent(int depth) {
		for (int i = 0; i < depth; i++)
			ps.print('\t');
	}
	
	public void beginObject(String name, Object o, int depth) {
		indent(depth);
		
		if (name != null)
			ps.print(name + " = ");
		
		Class<? extends Object> c = o.getClass();
		
		String className = c.isArray() ? "Array of " + c.getComponentType().getName() : c.getName();
		
		ps.println(className + (c.isArray() ? " [" : " {"));
	}

	public void endObject(Object o, int depth) {
		indent(depth);

		Class<? extends Object> c = o.getClass();
		
		ps.println(c.isArray() ? "]" : "}");
	}

	public void beanProperty(Object o, String name, Object value, int depth) {
		indent(depth);
		
		ps.println(name + " = " + (value == null ? "null" : value));
	}

	public void beginCollection(Object o, int depth) {
		indent(depth);
		
		ps.println("elements: {");
	}

	public void endCollection(Object o, int depth) {
		indent(depth);
		
		ps.println("}");
	}

	public void beginArray(Object o, int depth) {
	}

	public void endArray(Object o, int depth) {
	}
}
