package testejava.fbhack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		StringBuilder b = new StringBuilder();
		for(int i=1;i<=100000;i++){
			b.append(Math.random()>0.5?'0':'1');
		}
		System.out.println(resolve(b.toString()));

	}
	
	private static String resolve (String input){
		String in = input.replace(" ", "");
		int[] aux = new int[in.length()];
		int soma = 0;
		int indiceMaiorSoma = 0;
		int maiorSoma = 0;
		for(int i=0;i<in.length();i++){
			if(in.charAt(i)=='1'){
				soma--;
			} else {
				if(soma<0)
					soma=0;
				soma++;
			}
			aux[i]=soma;
			if(soma > maiorSoma){
				indiceMaiorSoma = i;
				maiorSoma = soma;
			}
					
		}
//		for(int a : aux){
//			System.out.print(a+" ");
//		}
//		System.out.println();
		int j = indiceMaiorSoma;
		while(aux[j]>=0 && j>0)
			j--;
		if(aux[j]<0) j++;
		String inv = inv(j,indiceMaiorSoma,in);
		return ""+input+"]\n["+inv+"]\n("+f1(inv)+", "+j+", "+indiceMaiorSoma+")";
		
	}
	
	private static int f1(String s){
		char[] chars = s.toCharArray();
		List<Character> list = new ArrayList<>(s.length());
		for(char c : chars){
			list.add(c);
		}
		return Collections.frequency(list, '1');
	}
	
	private static String inv(int i, int f, String s){
		StringBuilder b = new StringBuilder();
		for(int c=0;c<i;c++){
			b.append(s.charAt(c));
		}
		
		for(int c=i;c<=f;c++){
			if(s.charAt(c)=='0')
				b.append('1');
			else b.append('0');
			//b.append(' ');
		}
		
		for(int c=f+1;c<s.length();c++){
			b.append(s.charAt(c));
		}
		return b.toString();
		
	}
	
	

}
