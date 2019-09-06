import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
public class InfixtoPostfix {

	 public static void main(String[] args) throws FileNotFoundException {
	        File file = new File("input.txt");
	        Scanner SC = new Scanner(file);
	        

	        ArrayList<String> st = new ArrayList<String>();
	        ArrayList<Integer> val = new ArrayList<Integer>();

	        int var_line = SC.nextInt();
	        for (int i = 0; i < var_line; i++) {
	            st.add(SC.next());
	            SC.next();
	            val.add(SC.nextInt());
	        }

	      
	        var_line = SC.nextInt();
	        SC.nextLine();
	        String postFix="";
	        for (int i = 0; i < var_line; i++) {
	            String line = SC.nextLine();
	            postFix = makePostfix(line);
	            calculatePostfix(st, val, postFix);
	        }


	    }

	    public static String makePostfix(String line) {
	        Stack<String> stack = new Stack<String>();
	        stack.push("");
	        String postfix = "";
	        for (int i = 0; i < line.length(); i++) {
	            String x = Character.toString(line.charAt(i));
	            if(!x.equals(" ")){
	                if (x.equals("+") || x.equals("-") || x.equals("x") || x.equals("/") || x.equals("(") || x.equals(")")) {

	                    if (x.equals("+") && (stack.peek().equals("-") || stack.peek().equals("/") || stack.peek().equals("x"))) {
	                        while (!(stack.isEmpty() || stack.peek().equals("("))) {
	                            postfix += stack.pop();
	                        }
	                        stack.push(x);
	                    }else if (x.equals("-") && (stack.peek().equals("/") || stack.peek().equals("x")||stack.peek().equals("+"))) {
	                        while (!(stack.isEmpty() || stack.peek().equals("("))) {
	                            postfix += stack.pop();
	                        }
	                        stack.push(x);
	                    } else { if (x.equals("/") && stack.peek().equals("x")) {
	                            postfix+=stack.pop();
	                            stack.push(x);
	                        } else if (x.equals(")")) {
	                            while(!stack.peek().equals("(")){
	                                postfix+=stack.pop();
	                            }
	                            stack.pop();
	                        } else { stack.push(x);
	                        }
	                    }
	                } else postfix+=x;
	            }
	        }
	        while(!stack.isEmpty()){
	            postfix+=stack.pop();
	        }
	        return postfix;
	    }

	    public static void calculatePostfix(ArrayList<String> st, ArrayList<Integer> val, String postFix) {

	        Stack<Integer> aux = new Stack<Integer>();

	        for (int i = 0; i < postFix.length(); i++) {
	            if(postFix.charAt(i)=='+' || postFix.charAt(i)=='-' || postFix.charAt(i)=='x' || postFix.charAt(i)=='/'){
	                int b = aux.pop();
	                int a = aux.pop();

	                if (postFix.charAt(i)=='+') {
	                    aux.push(a+b);
	                } else if (postFix.charAt(i)=='-') {
	                    aux.push(a-b);
	                } else if (postFix.charAt(i)=='x') {
	                    aux.push(a*b);
	                } else  if (postFix.charAt(i)=='/') {
	                    aux.push(a/b);
	                }
	            }else{
	              try{
	                aux.push(val.get(st.indexOf(Character.toString(postFix.charAt(i)))));
	              }
	              catch (Exception e){
	                System.out.println("Compiler error");
	                return;
	              }
	            }
	        }
	        System.out.println(aux.pop());

	    }
}
