package bai2;
import java.util.*;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	static String s;
	static int n;
	static HashMap<Character, Integer> map = new HashMap<>();
	

	public static void main(String[] args) {

		// a) Nhap vao mot chuoi ky tu khong dau co chieu dai bat ky, khong phan biet chu hoa, chu thuong
		input();
		showInput();
		
		// b) Ma hoa Huffman
		HuffmanCode huf = new HuffmanCode(map.size(), map);
		huf.displayResult();
		
		// c) Ma hoa Shannon-Fano, tinh hieu suat ma hoa va tinh du thua
		ShannonFanoCode fano = new ShannonFanoCode(n, map);
		fano.printCode();
		fano.showPerform();
		fano.showRedundancy();
	}
	
	static void input() {
		System.out.print("Nhap mot chuoi ky tu khong dau: ");
		s = sc.nextLine().toLowerCase();
		n = s.length();
		for (int i = 0; i < n; i++) {
			if (map.containsKey(s.charAt(i))) 
			{
				map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
			}
			else
			{
				map.put(s.charAt(i), 1);
			}
		}
	}
	
	static void showInput() {
		System.out.println("Chuoi ban vua nhap la: \"" + s + "\".");
		System.out.println(map);
	}
	
}
