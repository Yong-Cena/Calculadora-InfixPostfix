import Stacks.ArrayStack;
import java.util.StringTokenizer;
public class Funcionalidades {
    String expresionStr = "";
    
    public static String infixPosfix(String expresion)
    {
        StringBuilder cad = new StringBuilder();
        ArrayStack<String> operadores = new ArrayStack();
        StringTokenizer tokenizer = new StringTokenizer(expresion);
        String token;
        
        while(tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            if(operador(token))
                cad.append(token + " ");
            else if(token.equals("("))
                operadores.push(token);
            else if(token.equals(")")) {
                while(!operadores.isEmpty() && !operadores.peek().equals("("))
                    cad.append(operadores.pop() + " ");
                try {
                operadores.pop();
                } catch(RuntimeException e) {
                    throw new RuntimeException("Error");
                }
            } else if(operador(token)){
                while(!operadores.isEmpty() && !operadores.peek().equals("(") && jerarquiaOperador(token) <= jerarquiaOperador(operadores.peek()))
                    cad.append(operadores.pop() + " ");
                operadores.push(token);
            }
        }
        while(!operadores.isEmpty())
            cad.append(operadores.pop() + " ");
        return cad.toString();
    }
    
    public static boolean revisaParentesis(String expreAritStr)
    {
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
    
    public static boolean operador(String texto)
    {
        boolean resp= true;
        String sinEsp= texto.trim();
        if(sinEsp.length()!=1 || (sinEsp.charAt(0)>='0' && sinEsp.charAt(0)<='9'))
        {
            resp=false;
        }
    
        return resp;
    }
    
    public static int jerarquiaOperador(String operando)
    {
        int resp=0;
        
        if(operando.equals("+")|| operando.equals("-"))
        {
            resp= 1;
        }
        else
        {
            if(operando.equals("*") || operando.equals("/"))
            {
                resp=2;
            }
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
