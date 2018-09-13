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

public class ExaminerMaximumDepthReachedException extends Exception {
	private final String name;
	private final Object target;
	private final int maxdepth;
	
	private final String message;
	
	public ExaminerMaximumDepthReachedException(String name, Object target, int maxdepth) {
		this.name = name;
		this.target = target;
		this.maxdepth = maxdepth;
		
		message = "Maximum depth " + maxdepth + " reached whilst examining an object of class " + target.getClass().getName() +
				(name == null ? " with no name" : " named " + name);
	}
	
	public String getName() { return name; }
	
	public Object getTarget() { return target; }
		
	public int getMaximumDepth() { return maxdepth; }

	public String getMessage() { return message; }
}
