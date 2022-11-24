package vTiger.Practice;

import java.util.Random;

public class GenericUtilityPractice {

	public static void main(String[] args)
	{
		Random ran = new Random();
		int random = ran.nextInt(300);
		System.out.println(random);
		
		int random1 = ran.nextInt(100);
		System.out.println(random1);
		 
		int value = add(random, random1);
		System.out.println(value);
		
		int value1 = GenericUtilityPractice.add(20, 30); // also call method without classname like line no. 16
		System.out.println(value1);
		
	}
		
		public static int add(int a, int b)
		{
			int c = a+b;
			return c;
		}
}
