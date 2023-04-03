/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HP
 */
public class FuncionalidadesTest {
    private String operacion;
    
    public FuncionalidadesTest() {
        operacion = "( - 1 + 2 ) * ( ( 4 + 5 / 2 ) ) - 7";
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of revisaExpresion method, of class Funcionalidades.
     */
    @Test
    public void testRevisaExpresion() {
        System.out.println("Revisa Expresion");
        Funcionalidades.revisaExpresion(operacion);
    }
    
    /**
     * Test of infixPosfix method, of class Funcionalidades.
     */
    @Test
    public void testInfixPosfix() {
        System.out.println("infixPosfix");
        String expResult = "-1.0 2 + 4 5 2 / + * 7 -";
        String result = Funcionalidades.infixPosfix(operacion);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calculaResultado method, of class Funcionalidades.
     */
    @Test
    public void testCalculaResultado() {
        System.out.println("calculaResultado");
        String posfija = "-1.0 2 + 4 5 2 / + * 7 -";
        Double expResult = 0.0;
        Double result = Funcionalidades.calculaResultado(posfija);
        assertEquals(expResult, result);
    }
}
