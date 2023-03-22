import Stacks.ArrayStack;
import java.util.StringTokenizer;
public class Funcionalidades {
    String expresionStr = "";
    
    public static void convertirInfix()
    {
        
    }
    
    public  boolean revisaParentesis()
    {
        String expreAritStr= expresionStr.replace(" ", "");
        System.out.println("    esParenteBalan() " + expreAritStr);
        int tam = expreAritStr.length();    //Tamaño de la expresionStr.
        ArrayStack <Character> stackParen= new ArrayStack(tam);
        boolean resp = true;
        int i= 0;
        char c;

        //Recorrido de la expresionStr, caracter por caracter.
        while( (i < tam) && resp ) {
            c = expreAritStr.charAt(i);
            switch(c)
            {
                case '(':
                    stackParen.push(c);
                    break;
                case ')':
                    if(!stackParen.isEmpty())
                    {
                        stackParen.pop();
                    }
                    else
                    {
                        resp=false;
                    }
                    break;
                default:
                    break;
            }
            i= i+1;
        }
        
        while(!stackParen.isEmpty())
        {
            stackParen.pop();
            resp=false;
            
        }
        return resp;
        
    }
    
    /*
    private static <T> boolean elemEnPila(ArrayStack<T> pila1, T elem2) {
        if (pila1 == null) throw new
                RuntimeException("(elemEnPila) pila1 o pila2: null.\n");

        boolean estap1= false;
        T elem1 = null;
        ArrayStack<T> paux1= new ArrayStack<T>();

        //"Ve" si el elem2 esta en la pila1.
        while ( !pila1.isEmpty() && estap1==false){
            elem1 = pila1.pop();
            paux1.push(elem1);  // Vaciando pila1
            estap1=elem2.equals(elem1);
        }

        // Regresa pila1 a su estado original.
        while ( !paux1.isEmpty() ) {
            pila1.push(paux1.pop());  // Rellena pila1 completa
        } 
        
        return estap1;
    }
    */
}
