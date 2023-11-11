package com.chuidiang.ejemplos.junit45;

import com.chuidiang.ejemplos.semi_mazacote.Calculator;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chuidiang
 * @date 10/11/2023
 */
public class CalculatorTest {
    @Test
    public void mustAdd (){
        Assert.assertEquals("Add test", 4, Calculator.add(2,2));
    }

    @Test
    public void mustSubstract(){
        Assert.assertEquals("Substract test", 3, Calculator.substract(6,3));
    }

    @Test
    public void mustMultiply(){
        Assert.assertEquals("Multiply test", 6, Calculator.multiply(2,3));
    }

    @Test
    public void mustDivide(){
        Assert.assertEquals("Divide test", 3, Calculator.divide(6,2));
    }

}
