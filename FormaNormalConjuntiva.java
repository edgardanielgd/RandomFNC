/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fnc;

import java.lang.Math;
import java.util.ArrayList;
import java.io.*;

class Disjunction{
    private Integer length;
    private Integer number;
    public Boolean[] binary_expression;
    
    public Disjunction(int length, int number){
        this.length = length;
        this.number = number;
        this.binary_expression = convertToBinary();
    }
    
    public Boolean[] convertToBinary(){
        Boolean[] arr = new Boolean[this.length];
        
        int offset = 1;
        int n = this.number;
        while(n > 0){ //Llenar con el valor binario
            arr[this.length - offset++] = getBool( n % 2 );
            n /= 2;
        }
        
        for(int i = 0; i < arr.length && arr[i] == null; i++){
            //LLenando con ceros las casillas faltantes iniciales
            arr[i] = false;
        }
        
        return arr;
    }
    private Boolean getBool(int data){
        if(data == 1)return true;
        return false;
    }
    @Override
    public String toString(){
      String ret = "( ";
      for(int i = 0; i < this.length; i++){
        if(this.binary_expression[i])
            ret += "~";
        ret += "x" + String.valueOf(i)+" ";
        if(i != this.length - 1)
            ret += "+ ";
      }
      ret += ")";
      return ret;
    }
}
class SingleConjuntion{
    int len;
    ArrayList<Disjunction> arr;
    public SingleConjuntion( ArrayList<Disjunction> arr){
        this.arr = arr;
    }
    @Override
    public String toString(){
        String ret = "";
        for(Disjunction dis : this.arr){
            ret += String.valueOf(dis) + "*\n";
        }
        return ret;
    }
    public ArrayList<Disjunction> getArr(){ return this.arr; }
}
public class FormaNormalConjuntiva {
    public static SingleConjuntion getRandomFNC( int size ){
        ArrayList<Disjunction> arr = new ArrayList<>();
        
        int bottom = 0;
        int top = (int)Math.pow(2, (double)size) - 1;
        System.out.println("Máximo numero de "+String.valueOf(size)+" bits: "+String.valueOf(top));
        
        while(bottom < top){
            int randomNumber = (int)(Math.random()*(top-bottom) + bottom);
            Disjunction newDis = new Disjunction(size, randomNumber);
            arr.add(newDis);
            
            bottom = randomNumber + 1;
        }
        SingleConjuntion sc = new SingleConjuntion(arr);
        return sc;
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        
        System.out.println("=====================================");
        System.out.println( "FORMA NORMAL CONJUNTIVA ALEATORIA" );
        System.out.println( "Matemáticas discretas II" );
        System.out.println( "Jhonatan Steven Rodriguez Ibanez" );
        System.out.println( "Edgar Daniel Gonzalez Diaz" );
        System.out.println("=====================================");
        System.out.println("\n");
        System.out.println("Digite -1 para salir del programa\n");
        
        int data = 0;
        
        do{
            String line = bf.readLine();
            try{
                data = Integer.parseInt(line);
            }catch(Exception e){
                System.out.println("Digita valores correctos");
                continue;
            }

            if( data < 0){
                if( data == -1){
                    System.out.println("Hasta luego!");
                    continue;
                }else{
                    System.out.println("Digita valores correctos");
                    continue;
                }
            }else{
                SingleConjuntion sc = getRandomFNC( data );
                System.out.println(sc); 
                
            }
        }while( data != -1);
        bf.close();
    }
    
}