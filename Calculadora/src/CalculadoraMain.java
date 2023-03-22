
import Stacks.ArrayStack;
import java.util.StringTokenizer;

public class CalculadoraMain {

    /**
     * Retrives the count of...
     * @author 
     * @version
     * @param args 
     * @return the number of...
     * 
     */
    public static void main(String[] args) {
        
        //ArrayStack<String> expresion= new ArrayStack<String>();
        String expresion = "(1+2)*((4+5/2)) -7";
        
        StringTokenizer tokenizer = new StringTokenizer(expresion,"()");
        while(tokenizer.hasMoreTokens())
        {
            System.out.println(tokenizer.nextToken());
        }
        
 /*       ArrayStack<Integer> pilaInt1 = new ArrayStack<Integer>();
        ArrayStack<Integer> pilaInt2 = new ArrayStack<Integer>();

        pilaInt1.push(10);    pilaInt1.push(-20);
        pilaInt1.push(100);   pilaInt1.push(40);
        
        pilaInt2.push(40);    pilaInt2.push(-20);
*/        
 
 
    }
    
}
