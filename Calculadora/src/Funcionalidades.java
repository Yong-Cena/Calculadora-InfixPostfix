import Stacks.ArrayStack;
import java.util.StringTokenizer;
public class Funcionalidades {
    
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
    
    public static boolean hayMasOperador(String notacion) {
        StringTokenizer tokenizer = new StringTokenizer(notacion);
        boolean resp = false;
        String elemento = tokenizer.nextToken();
        while (tokenizer.hasMoreTokens() && resp == false) {
            String elemento2 = tokenizer.nextToken();
            
            if(operador(elemento))
            {
                if(operador(elemento2))
                {
                    resp=true;
                }
            }
            elemento=elemento2;
        }
        return resp;
    }
    
    public static String infixPosfix(String expresion)
    {
        ArrayStack<String> operadores = new ArrayStack();
        ArrayStack<Double> numeros= new ArrayStack();
        String resp="";
        StringTokenizer tokenizer = new StringTokenizer(expresion);
        String token;
        
        while(tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            if(esNumero(token))
            {
                resp+=token+" ";
                numeros.push(Double.parseDouble(token));
            }
            
            else if(token.equals("("))
            {
                operadores.push(token);
                while(!numeros.isEmpty())
                {
                    numeros.pop();
                }
            }
            else if(token.equals(")")) 
            {
                while(!operadores.isEmpty() && !operadores.peek().equals("("))
                    resp+= operadores.pop() + " ";
                try {
                operadores.pop();
                }catch(RuntimeException e) {
                    throw new RuntimeException("Error");
                }
            }
            else if(token.equals("-"))
            {
                if(numeros.isEmpty())
                {
                    double neg= Double.parseDouble(tokenizer.nextToken())*-1;
                    resp+= neg+ " ";
                    numeros.push(neg);
                }
                else
                {
                    while(!operadores.isEmpty() && !operadores.peek().equals("(") && jerarquiaOperador(token) <= jerarquiaOperador(operadores.peek()))
                    {
                    resp+= operadores.pop() + " ";
                    }
                    operadores.push(token);
                }
            }
            else if(operador(token))
            {
                while(!operadores.isEmpty() && !operadores.peek().equals("(") && jerarquiaOperador(token) <= jerarquiaOperador(operadores.peek()))
                {
                    resp+= operadores.pop() + " ";
                }
                numeros.pop();
                operadores.push(token);
            }
            
        }
        while(!operadores.isEmpty())
            resp+= operadores.pop() + " ";
        return resp;
    }
    
    public static boolean operador(String texto)
    {
        boolean resp= false;
        String sinEsp= texto.trim();
        if(sinEsp.equals("+") || sinEsp.equals("-") || sinEsp.equals("*") || sinEsp.equals("^") || sinEsp.equals("-"))
        {
            resp=true;
        }
        return resp;
    }
    
    public static boolean esNumero(String texto)
    {
        boolean resp=true;
        
        try{
            Double.parseDouble(texto);
        }catch(NumberFormatException error)
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
    
    public static Double calculaResultado(String posfija)
    {
        ArrayStack<Double> elem= new ArrayStack<Double>();
        Double num1, num2, resp;
        StringTokenizer tokenizer= new StringTokenizer(posfija);
        String token;
        
        while(tokenizer.hasMoreTokens())
        {
            token= tokenizer.nextToken();
            if(esNumero(token))
            {
                elem.push(Double.parseDouble(token));
            }
            else
            {
                num2= elem.pop();
                num1= elem.pop();
                resp= operacion(token, num1,num2);
                elem.push(resp);
            }
        }
        return elem.pop();
    }
    
    private static Double operacion(String operador, Double n1, Double n2)
    {
        Double resp=0.0;
        
        switch(operador)
        {
            case "+" -> resp= n1+n2;
            case "-" -> resp= n1-n2;
            case "*" -> resp= n1*n2;
            case "/" -> {
                if(n2!=0)
                {
                    resp= n1/n2;
                }
                else
                {
                    throw new RuntimeException("Error: division sobre 0");
                }
            }
            default -> throw new RuntimeException("Error: operador no valido");
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
