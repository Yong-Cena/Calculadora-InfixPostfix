import Stacks.ArrayStack;
import java.util.StringTokenizer;
public class Funcionalidades {
    
    /**
     * Método que revisa que la expresion escrita por el usuario sea valida 
     * @param expreAritStr expresión 
     * @return regresa una excepcion si la expresion esta mal escrita
     **/
    public static void revisaExpresion(String expreAriStr)
    {
        if(expreAriStr==null || expreAriStr.trim().length()==1)
        {
            throw new RuntimeException("Syntax error");
        }
        
        if(revisaParentesis(expreAriStr))
        {
            if(hayMasOperador(expreAriStr))
            {
                throw new RuntimeException("Syntax error");
            }
        }
        else
        {
            throw new RuntimeException("Syntax error");
        }
    }
    
     /**
     * Método que revisa que los parentesis estén bien colocados
     * @param expreAritStr expresión 
     * @return Regresa true si los parentesis estan bien colocados. False en caso de que no se cierre un parentesis.
     **/
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
    
        /**
     * Método que convierte una cadena de texto a notación posfija.
     * @param expresion Cadena de texto en notación infija
     * @return Regresa la cadena de texto en notación posfija.
     **/
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

    
    
        /**
     * Método que revisa si una cadena de texto es un operador.
     * @param texto Cadena de texto
     * @return Regresa verdadero si el texto es un operador ('+', '-', '*', ´/´). 
     * Regresa falso si el texto ingresado no es un operador.
     **/
    public static boolean operador(String texto)
    {
        boolean resp= false;
        String sinEsp= texto.trim();
        if(sinEsp.equals("+") || sinEsp.equals("-") || sinEsp.equals("*") || sinEsp.equals("^") || sinEsp.equals("/"))
        {
            resp=true;
        }
        return resp;
    }
    
        /**
     * Métodoque revisa que una cadena de texto sea un numero
     * @param texto Cadena de texto
     * @return Regresa verdadero si el texto es un número, regresa falso en caso contrario
     **/
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
    
        /**
     * Método que asigna de jerarquía de operaciones 
     * @param operador operador ('+', '-', '*', ´/´).
     * @return Regresa 1 si los operadores son '+' o '-'. Regresa 2 si el operador es
     *  '*' o '/'. Regresa 0, si no es ninguno de los operadores anteriores
     **/
    public static int jerarquiaOperador(String operando)
    {
        int resp=0;
        
        if(operando.equals("+")|| operando.equals("-"))
        {
            resp= 1;
        }
        else if(operando.equals("*") || operando.equals("/"))
            {
                resp=2;
            }
        
        else if(operando.equals("^"))
            {
                resp=3;
            }
        return resp;
    }
    
        /**
     * Método que calcula el resultado a partir de una cadena en notación posfija
     * @param posfija Expresión a calcular en notación posfija
     * @return Regresa el resultado de la operación
     **/
    
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
    
        /**
     * Método que realiza una operación entre dos números enteros.
     * @param operador Operador que realizara el método: '+', '-', '*', ´/´
     * @param n1 Primer numerod de la operación
     * @param n2 Segundo numero de la operación
     * @return Resultado de la operación entre n1 y n2
     * @throws RuntimeException Error: division sobre 0
     **/
    private static Double operacion(String operador, Double n1, Double n2)
    {
        Double resp=0.0;
        
        switch(operador)
        {
            case "+":
            resp=n1+n2;
            break;
            case "-":
            resp=n1-n2;
            break;
            case "*":
            resp=n1*n2;
            break;
            case "/": {
                if(n2!=0)
                {
                    resp= n1/n2;
                }
                else
                {
                    throw new ArithmeticException("Error: division sobre 0");
                }
            }
            break;
            case "^":
            resp= Math.pow(n1, n2);
        }
        return resp;
    }

    
    /**
     * Método que revisa que no haya más de un operador consecutivo en la operación.
     * @param notacion Cadena en notación infija.
     * @return Regresa verdadero, en caso que no haya más de un operador consecutivo. 
     * Regresa falso si hay más de un operador consecutivo.
     **/    
     public static boolean hayMasOperador(String notacion) {
        
        StringTokenizer tokenizer = new StringTokenizer(notacion);
        boolean resp = false;
        String elemento = tokenizer.nextToken();

        while (tokenizer.hasMoreTokens() && resp == false) {
            String elemento2 = tokenizer.nextToken();
            if(elemento.equals("-"))
            {
                if(operador(elemento2))
                {
                    resp=true;
                }
            }
            else
            {
                if(operador(elemento))
                {
                    if (elemento2.equals("+") || 
                            elemento2.equals("*")|| elemento2.equals("/") || 
                            elemento2.equals("^")) {
                        resp=true;
                    }
                }
            }
            elemento = elemento2;
        }
        return resp;
    }

}
