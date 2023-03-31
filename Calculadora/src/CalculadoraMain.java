
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
        ArrayStack<Double> e1= new ArrayStack<Double>();
        String expresion = " ( - 1 + 2 ) * ( ( 4 + 5 / 2 ) ) - 7 ";
        Double resultado;
        
       
        System.out.println(Funcionalidades.hayMasOperador(expresion));
        
        String posfija= Funcionalidades.infixPosfix(expresion);
        System.out.println("Posfija: "+posfija);
        
        resultado= Funcionalidades.calculaResultado(posfija);
        System.out.println("Resultado: "+resultado);

    }
    
}
