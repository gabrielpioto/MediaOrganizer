/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testejava.fbhack;

/**
 *
 * @author Henrique
 */
public class Hackaton {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(resolve("11111000"));
    }
    
    public static String resolve(String s){
        int peso = 0;
        int inicioInversao = 0, fimInversao = 0;
        
        char[] cArray = s.toCharArray();
        
        for(int range = 1; range <= s.length(); range++){            
            
            for(int inicio = 0; inicio+range < s.length(); inicio++){
                if(peso(s.substring(inicio, inicio + range)) > peso){
                    inicioInversao = inicio;
                    fimInversao = inicio+range;
                }
            }
            
        }
        
        for(int i = inicioInversao; i<=fimInversao; i++){
            if(cArray[i] == '0'){
                cArray[i] = '1';
            }else{
                cArray[i] = '0';
            }
        }
        
        return new String(cArray);
    }
    
    public static int peso(String s){
        char[] array = s.toCharArray();
        int result = 0;
        
        for(char c : array){
            if(c == '0')
                result++;
            else{
                result--;
            }
        }
        
        return result;
    }
    
}
