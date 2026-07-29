package day06;


import java.util.ArrayList;
import java.util.Random;

public class Ex15_숫자야구게임 {

	public static void main(String[] args) {
		/* 숫자 야구 게임을 구현하세요.
		 * 1~9사이의 중복되지 않은 3개의 랜덤한 수를 생성 : com
		 * 이 수를 맞추는 게임
		 * 
		 * 규칙
		 * S : 숫자가 있고 위치가 같은 경우
		 * B : 숫자가 있지만 위치가 다른 경우
		 * O : 일치하는 숫자가 하나도 없는 경우
		 * 3S : 게임 종료
		 * 
		 * 예시
		 * 랜덤 : 1 9 4
		 * 입력 : 1 2 3
		 * 결과 : 1S
		 * 입력 : 4 5 6
		 * 결과 : 1B
		 * 입력 : 1 4 9
		 * 결과 : 1S2B
		 * 입력 : 6 7 8
		 * 결과 : O
		 * 입력 : 1 9 4
		 * 결과 : 3S
		 * */
		
		Random random = new Random();
		ArrayList<Integer>list = new ArrayList<Integer>();
		int min = 1, max = 9;
		
		//3개 1~9사이의 중복되지 않은 3개의 랜덤한 수를 생성
		for(;list.size()<3;) {
			int num = random.nextInt(min,max + 1);
			list.add(num);
			//만약 생성된 넘버 중 중복된 숫자가 있다면
			if(!list.contains(num)) {
				// 중복된 숫자를 remove(번지) : 번지에 있는 객체를 삭제
				list.
				//다시 for로 돌아가라
				continue;
			}
			
			//contains(값) : 값이 있으면 true, 없으면 false를 반환
			//indexOf(값) : 값이 있으면 위치를 없으면 -1를 반환 
			
			
		}
		System.out.println(list);

		
	}

	}

