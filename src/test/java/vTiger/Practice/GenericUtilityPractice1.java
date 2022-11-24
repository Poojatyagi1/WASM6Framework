package vTiger.Practice;

import java.util.Random;

public class GenericUtilityPractice1 {

	public static void main(String[] args)
	{
		Random ran = new Random();
		int random = ran.nextInt(500);
		System.out.println(random);
		
		int random1 = ran.nextInt(250);
		System.out.println(random1);
		
		add(55,68);
		add(random, random1);

	}
	
	public static void add(int a, int b)
	{
		int c = a+b;
		System.out.println(c);
	}

}
