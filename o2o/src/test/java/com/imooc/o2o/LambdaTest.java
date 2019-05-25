package com.imooc.o2o;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Unruly Wind on 2019/3/5/005.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class LambdaTest {
	private static String MESSAGE = "Hello World";

	public static void main(String[] args) {
		MathOperation division = (int a, int b) -> System.out.println(a / b);

		division.operation(4, 2);
		String[] players = {"Rafael Nadal", "Novak Djokovic",
				"Stanislas Wawrinka", "David Ferrer",
				"Roger Federer", "Andy Murray",
				"Tomas Berdych", "Juan Martin Del Potro"};
		Comparator<String> sortPlayers = (String s1, String s2) -> (s1.compareTo(s2));
		// 1.1 使用匿名内部类根据 name 排序 players
		Arrays.sort(players, sortPlayers);

		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");

		features.forEach(n -> System.out.println(n));
	}

	interface MathOperation {
		void operation(int a, int b);
	}
}
