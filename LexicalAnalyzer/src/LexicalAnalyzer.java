import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class LexicalAnalyzer {
public static void main(String[] args) throws Exception {
        
        String[] allkeywords = {"if","else","int","float","double"};
       
        
         ArrayList<String> identifiers = new ArrayList<String>();
         ArrayList<String> math = new ArrayList<String>();
          ArrayList<String> logic = new ArrayList<String>();
           ArrayList<String> numeric = new ArrayList<String>();
            ArrayList<String> others = new ArrayList<String>();
             ArrayList<String> keywords = new ArrayList<String>();
            
        
        
    File file= new File("./input.txt");
    Scanner sc= new Scanner(file);
    
    while(sc.hasNextLine()){
     
        String line = sc.nextLine();
        String [] s = line.split("\\s");
        for (int i=0; i<s.length;i++){
        boolean keycheck=false;
        for(int j=0; j<allkeywords.length;j++){
           if(allkeywords[j].equals(s[i])){
            keywords.add(s[i]);
            keycheck=true;
            break;
           }        
        }
        
        if(!keycheck){
            
            if(s[i].matches("[a-zA-Z]+")){  //checks for variables A to Z or a-z
                identifiers.add(s[i]);
            }
            
            else if(s[i].matches("([0-9]+)|((.)[0-9][0-9]*)|([0-9][0-9]*(.[0-9]+))")){ //checks for numbers 0-9
                numeric.add(s[i]);
            }
            else if(s[i].matches("[*/+-]|(=)")){ //checks for mathematical operators
                math.add(s[i]);
            }
            else if(s[i].matches("[><]")){ //checks for logical operators
                logic.add(s[i]);   
            }
            else if(s[i].matches("[)({}:;,]")){ //checks for others
                others.add(s[i]);
            }
        }
        //System.out.println( math.add(s[i]));
        }
       
    }
   
    Set<String> key = new HashSet<String>(keywords);
    Set<String> id = new HashSet<String>(identifiers);
    Set<String> num = new HashSet<String>(numeric);
    Set<String> maths = new HashSet<String>(math);
    Set<String> logicop = new HashSet<String>(logic);
    Set<String> oth = new HashSet<String>(others);
    
    System.out.print("Keywords : ");
    toprint(key);
    
    System.out.print("Identifiers : ");
    toprint(id);
   
    System.out.print("Numeric Values : ");
    toprint(num);
   
    System.out.print("Math Operators : ");
    toprint(maths);
    
    System.out.print("Logical Operators : ");
    toprint(logicop);
    
    System.out.print("Others : ");
    toprint(oth);
    
}
    
    public static void toprint(Set<String> s){
      for (String printer :s )
      {
          System.out.print(printer+" ");
      
      }
      System.out.println();
    }
}
