package day09;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RamdomNum {
	
	public static void main(String[] args) {
		System.out.print("중복되지 않은 정수 7개 입력 : ");
		ArrayList<Integer> list = inputNums(1,45,7);
		System.out.println(list);
		
		
	}

	static ArrayList<Integer> inputNums(int min, int max, int size) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		Scanner scan = new Scanner(System.in);
		
		for(;list.size() < size;) {
			int num = scan.nextInt();
			if(!list.contains(num)) {
				list.add(num);			
			}
		}
		return list;
	}
}
	



