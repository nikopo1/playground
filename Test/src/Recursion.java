import java.util.ArrayList;
import java.util.Arrays;


public class Recursion {

	private static int counter = 0;
	
	// 8.3
	public static void getAllSubsets(int current, ArrayList<Integer> acc, 
			ArrayList<Integer> set, ArrayList<ArrayList<Integer>> result) {
		
		if( current >= set.size() )
			return;
		
		counter++;
		ArrayList<Integer> setwith = new ArrayList<Integer>(acc);
		setwith.add(set.get(current));
		result.add(setwith);
		getAllSubsets(current + 1, acc, set, result);
		getAllSubsets(current + 1, setwith, set, result);
	}
	
	public static void testGetAllSubsets() {
		int n = 8;
		ArrayList<Integer> set = new ArrayList<Integer>();
		for(int i = 0; i < n; ++i)
			set.add(i);
		
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		getAllSubsets(0, new ArrayList<Integer>(), set, result);
		for(ArrayList<Integer> list : result)
			System.out.println(list);
		System.out.println(counter);
	}
	
	// 8.4
	public static void getAllPermutations(String str, ArrayList<Integer> result) {
		
		if( result.size() == str.length() ) {
			String s = new String();
			for(int i = 0; i < result.size(); i++)
				s += str.charAt(result.get(i));
			System.out.println(s);
		}
		
		for(int i = 0; i < str.length(); i++)
			if( !result.contains(i) ) {
				result.add(i);
				getAllPermutations(str, result);
				result.remove(result.size() - 1);
			}
	}
	
	public static void testGetAllPermutations() {
		getAllPermutations("abcd", new ArrayList<Integer>());
	}
	
	// Decodificare un set de cifre in a->1, b->2, ... , z->26
	public static int countDecode(int n, int last, int[] input) {
		
		if( last > 26 )
			return 0;
		if( n == input.length )
			return 1;
		if( n == 0)
			return countDecode( n + 1, input[n], input);
		
		int with = last * 10 + input[n];
		int wout = input[n];
		
		return countDecode( n + 1, wout, input ) + countDecode( n + 1, with, input );
	}
	
	public static void testCountDecode() {
		int[] array = { 1, 1, 2, 2, 3 };
		System.out.println(countDecode(0,0,array));
	}

	// 8.7
	public static int splitChange(int sum, int nom) {
		int nextnom = 0;
		switch( nom ) {
			case 25:
				nextnom = 10;
				break;
			case 10:
				nextnom = 5;
				break;
			case 5:
				nextnom = 1;
				break;
			case 1:
				return 1;
		}
		
		int total = 0;
		int noCoins = sum / nom;
		for(int i = 0; i <= noCoins; i++)
			total += splitChange(sum - i * nom, nextnom);
		return total;
	}
	
	public static void testSplitChange() {
		System.out.println(splitChange(20 , 25));
	}

	// 8.8
	public static boolean nonAttacking(int[] queens, int row, int column) {
		boolean test = true;
		for(int i = 0; i < column; i++) {
			// Verificare acelasi rand, oblic paralel cu a doua diag, oblic paralel cu prima diag
			if( queens[i] == row || i + queens[i] == row + column || column - i == row - queens[i]) {
				test = false;
				break;
			}
		}
		return test;
	}
	
	public static void doBKT(int[] queens, int current) {
		for(int i = 0; i < 8; i++) {
			queens[current] = i;
			if(nonAttacking(queens, i, current)) {
				if( current == 7 ) {
					for(int j = 0; j < 8; j++)
						System.out.print(queens[j] + " ");
					System.out.println();
				}
				else
					doBKT(queens, current + 1);
			}
		}
	}
	
	public static void testDoBKT() {
		doBKT(new int[8], 0);
	}
	
	public static void swap(int a, int b) {
		System.out.println("Before: a = "+a+" , b = "+b);
		a = a + b - (b = a);
		System.out.println("After:  a = "+a+" , b = "+b);
	}
	
	public static void main(String[] args) {
		
	}
}
