package day06;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex14_List연습예제2 {

	public static void main(String[] args) {
		/* 3개의 중복되지 않은 정수의 입력 받는 코드를 작성하세요.
		 * 단, 1~9사이의 정수
		 * 중복 입력하면 다시 입력
		 * 리스트.contains(숫자)
		 * */
	}

	public static ArrayList<Integer> inputNums(int size, int min, int max){
		
		ArrayList<Integer>list = new ArrayList<Integer>();
		
		Scanner scan = new Scanner(System.in);
		
		// 3개의 정수값이 입력될 경우
		for(;list.size() < size;) {
			System.out.print("입력 : ");
			int num = scan.nextInt();
			
			if(!list.contains(num)) {
				list.add(num);
			}
			System.out.println(list);
		}
		
	}

}
