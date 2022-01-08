package fnc;
import java.util.ArrayList;
import java.util.Scanner;

public class FNC {
    final char NOT;
    ArrayList<Character> variables;
    ArrayList<String> selectedVariables;
    ArrayList<String> blackListTerms;
    int count;
    int maxTerms;

    public FNC(int n){
        NOT='~';
        clear(n);
    }

    public void selectVariables(int n){
        int number;
        while(n --> 0){
            number = (int)Math.round(Math.random()*(variables.size()-1));
            if(count==0){
                selectedVariables.add(variables.remove(number)+"");
            }else{
                selectedVariables.add((""+variables.remove(number))+""+count);
            }
            if(variables.isEmpty()){
                count++;
                completeVariables();
            }
                
        }

    }
    
    public void completeVariables(){
        for ( int i=0; i<26; i++) {
            variables.add((char) ('a' + i ));
        }
        
    }

    public void clear(int n){
        variables = new ArrayList<Character>();
        selectedVariables = new ArrayList<String>();
        blackListTerms = new ArrayList<String>();
        count = 0;
        maxTerms=(int)Math.round(Math.pow(2, n));
        completeVariables();
        selectVariables(n);
    }

    public String generateTerm(){
        String term;
        do{
            term="";
            for(int i = 0; i<selectedVariables.size(); i++){
                if((int)Math.round(Math.random())==1)
                    term+=NOT+selectedVariables.get(i);
                else
                    term+=selectedVariables.get(i);
                if(i!=selectedVariables.size()-1)
                    term+="+";
            }
        }while(blackListTerms.indexOf(term)!=-1);
        blackListTerms.add(term);
        return term;
    }

    public String generateStatement(){
        int n = (int)Math.round(Math.random()*maxTerms);
        if(n==0)
            n=1;
        String statement = "";
        while(n --> 0){
            statement+="("+generateTerm()+")";
        }
        return statement;
    }


}

class Main{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        FNC f = new FNC(n);
        System.out.println(f.generateStatement());
    }
}
