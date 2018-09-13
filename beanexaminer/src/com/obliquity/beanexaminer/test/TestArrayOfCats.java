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

package com.obliquity.beanexaminer.test;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import com.obliquity.beanexaminer.DefaultHandler;
import com.obliquity.beanexaminer.Examiner;
import com.obliquity.beanexaminer.ExaminerMaximumDepthReachedException;
import com.obliquity.beanexaminer.Handler;

public class TestArrayOfCats {
	public static void main(String[] args) {
		Cat dinah = new Cat();
		
		dinah.setName("Dinah");
		dinah.setMale(false);
		dinah.setColour("Brown Tabby");

		Cat molly = new Cat();
		
		molly.setName("Molly");
		molly.setMale(false);;
		molly.setColour("Black");

		Cat[] cats = {dinah, molly};
		
		Handler h = new DefaultHandler();
		
		try {
			Examiner.examine(cats, 0, h);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | IntrospectionException | ExaminerMaximumDepthReachedException e) {
			e.printStackTrace();
		}
	}
}
