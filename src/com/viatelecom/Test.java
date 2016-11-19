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
		IntStream.rangeClosed(1, 100).forEach(n -> {
			System.out.println(convert(n));
		});
	}

	/**
	 * convert numbers from 1 to 100 by specified string with the rules below :
	 * * if n % 3 == 0 or n contains 3, replace to "Foo" * if n % 5 == 0 or n
	 * contains 5, replace to "Bar" * if n % 7 == 0 or n contains 7, replace to
	 * "Qix" * division > contains (ex : 51 -> "FooBar" ) * contains with old
	 * order (ex : 53 -> "BarFoo") * multiple with order Foo, Bar then Qix
	 * (ex:21 -> FooQix)
	 * 
	 * @param source
	 * @return
	 */
	public static String convert(Integer source) {
		StringBuffer result = new StringBuffer();
		if (source % 3 == 0 || source % 5 == 0 || source % 7 == 0 || checkContains(source)) {
			changeByModulus(source, result);
			changeByContains(source, result);
		} else {
			result.append(source.toString());
		}
		return result.toString();
	}

	/**
	 * check if the source contains the keys or not
	 * 
	 * @param source
	 * @return
	 */
	private static boolean checkContains(Integer source) {
		return map.keySet().stream().anyMatch(k -> source.toString().contains(k.toString()));
	}

	/**
	 * replace number to specified string by modulus
	 * 
	 * @param source
	 * @param map
	 * @param s
	 */
	private static void changeByModulus(Integer source, StringBuffer s) {
		map.forEach((k, v) -> {
			if (source % k == 0) {
				s.append(v);
			}
		});
	}

	/**
	 * replace number to specified string by contains
	 * 
	 * @param source
	 * @param result
	 */
	private static void changeByContains(Integer source, StringBuffer result) {
		source.toString().chars().forEach(c -> {
			map.forEach((k, v) -> {
				// System.out.println(Character.toString((char) c) + "->" + k +
				// "->" + v);
				if (k.toString().equals(Character.toString((char) c))) {
					result.append(v);
				}
			});
		});
	}

}
