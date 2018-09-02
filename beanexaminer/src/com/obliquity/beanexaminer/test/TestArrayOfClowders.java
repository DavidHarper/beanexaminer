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
import com.obliquity.beanexaminer.Handler;

public class TestArrayOfClowders {

	public static void main(String[] args) {
		Handler h = new DefaultHandler();

		Clowder clowder1 = new Clowder();
		
		Cat dinah = new Cat();
		
		dinah.setName("Dinah");
		dinah.setMale(false);
		dinah.setColour("Brown Tabby");
		
		clowder1.addCat(dinah);

		Cat molly = new Cat();
		
		molly.setName("Molly");
		molly.setMale(false);;
		molly.setColour("Black");
		
		clowder1.addCat(molly);

		Clowder clowder2 = new Clowder();
		
		Cat reggie = new Cat();
		
		reggie.setName("Reggie");
		reggie.setMale(true);
		reggie.setColour("Black and white");
		
		clowder2.addCat(reggie);

		Cat tigger = new Cat();
		
		tigger.setName("Tigger");
		tigger.setMale(true);
		tigger.setColour("Brown Tabby");
		
		clowder2.addCat(tigger);
		
		Clowder[] clowders = { clowder1, clowder2 };
		
		try {
			Examiner.examine(clowders, 0, h);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | IntrospectionException e) {
			e.printStackTrace();
		}
	}

}
