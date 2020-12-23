package com.boat.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoatUtilTest {

	@BeforeAll
    static void setUpBeforeClass() throws Exception {
        
        System.out.println("Before each testcases");
    }

 

    @AfterAll
    static void tearDownAfterClass() throws Exception {
          System.out.println("After all testcases");
    }

 

    @BeforeEach
    void setUp() throws Exception {
         System.out.println("Before each testcases");
    }

 

    @AfterEach
    void tearDown() throws Exception {
          System.out.println("After all testcases");
    }

 

    @Test
    void testGetRandomNumber() {
          int min = 90;
            int max = 140;
            Random random = new Random();
Integer randomNumber = (int)(Math.random() * (max - min + 1) + min);
     
            if (randomNumber >= 90 && randomNumber <= 140) {
                System.out.println("passed");
            } else
                fail("out of range");
    }

 

    @Test
void testGetRandomNumberDouble() {
        Double min=120.6;
        Double max=500.55;
        DecimalFormat df = new DecimalFormat("###.##");
        Double randomNumber = (Math.random() * (max - min + 1) + min);
        Double.parseDouble(df.format(randomNumber));
        if (randomNumber >= 120.6 && randomNumber <= 500.55) {
            System.out.println("passed");
        } else
            fail("out of range");
    }

 

    @Test
    void testGetRandomNumberLong() {
        int min = 90;
        int max = 140;
        Random random = new Random();
Integer randomNumber = (int)(Math.random() * (max - min + 1) + min);
 
        if (randomNumber >= 90 && randomNumber <= 140) {
            System.out.println("passed");
        } else
            fail("out of range");
    }

 

    /*@Test
    void  testCalculateSpeedFromRpm(Long double1) {
        Integer diameter =12 ;   
      int speed= (int) (((Math.PI ) * (double1 )* (diameter)/1056) * 1.609344);
      if(speed<=200.00)
      {
          System.out.println("passed");
      }
      else
          fail("out of range");
    }
*/
}
