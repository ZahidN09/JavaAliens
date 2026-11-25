package test.JUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import clases.Alien;

public class TestConstructoresJUnit {
    @Test
    public void testAsignacionCorrecta() {
        Alien alien = new Alien(10, "Verde");
        
        assertEquals(10, alien.getTamanio(), 0.0001);
        assertEquals("Verde", alien.getColor());
        assertEquals(0, alien.getNumeroOjos(), 0.0001);
        assertEquals(0, alien.getNumeroBrazos(), 0.0001);
        assertEquals(0, alien.getNumeroPies(), 0.0001);
    }

    @Test
    public void testRestriccionTamanioMenor() {
    	Alien alien = new Alien(4, "Verde");
        assertEquals(18, alien.getTamanio(), 0.0001);
    }

    @Test
    public void testRestriccionTamanioMayor() {
    	Alien alien = new Alien(31, "Verde");
        assertEquals(18, alien.getTamanio(), 0.0001);
    }


    @Test
    public void testCalculoPreciosBase() {
    	Alien alien = new Alien(20, "Verde");

        assertEquals(4.0, alien.getPrecioCuerpo(), 0.0001);
        assertEquals(2.0, alien.getPrecioExtremidad(), 0.0001);
        assertEquals(1.0, alien.getPrecioOjo(), 0.0001);
    }

    @Test
    public void testCalculoPreciosConRestriccion() {
    	Alien alien = new Alien(4, "Verde");
        
        assertEquals(3.6, alien.getPrecioCuerpo(), 0.0001);
        assertEquals(1.8, alien.getPrecioExtremidad(), 0.0001);
        assertEquals(0.9, alien.getPrecioOjo(), 0.0001);
    }
}