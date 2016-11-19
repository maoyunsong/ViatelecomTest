package com.viatelecom;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Test {

	private static final Map<Integer, String> map;
	static {
		map = new HashMap<Integer, String>();
		map.put(3, "Foo");
		map.put(5, "Bar");
		map.put(7, "Qix");
	}

	public static void main(String[] args) {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			System.out.println(convert(i));
		});
	}

	/**
	 * convert numbers from 1 to 100 by specified string with the rules below :
	 * if n % 3 == 0 or n contains 3, replace to "Foo"
	 * if n % 5 == 0 or n contains 5, replace to "Bar" 
	 * if n % 7 == 0 or n contains 7, replace to "Qix"
	 * priority : divide > contains (ex : 51 -> "FooBar" )
	 * contains: keep the original numeric order (ex : 53 -> "BarFoo")
	 * divide : multiples in order of Foo, Bar then Qix (ex:21 -> FooQix)
	 * 
	 * @param source
	 * @return
	 */
	public static String convert(Integer source) {
		StringBuffer buffer = new StringBuffer();
		if (match(source)) {
			changeByDivide(source, buffer);
			changeByContains(source, buffer);
		} else {
			buffer.append(source.toString());
		}
		return buffer.toString();
	}

	/**
	 * check if the source contains the keys or source % key == 0
	 * 
	 * @param source
	 * @return
	 */
	private static boolean match(Integer source) {
		return map.keySet().stream().anyMatch(k -> source.toString().contains(k.toString()) || source % k == 0);
	}
	
	/**
	 * replace number to specified string by divide
	 * 
	 * @param source
	 * @param map
	 * @param buffer
	 */
	private static void changeByDivide(Integer source, StringBuffer buffer) {
		map.forEach((k, v) -> {
			if (source % k == 0) {
				buffer.append(v);
			}
		});
	}

	/**
	 * replace number to specified string by contains
	 * 
	 * @param source
	 * @param buffer
	 */
	private static void changeByContains(Integer source, StringBuffer buffer) {
		source.toString().chars().forEach(c -> {
			map.forEach((k, v) -> {
				if (k.toString().equals(Character.toString((char) c))) {
					buffer.append(v);
				}
			});
		});
	}
}