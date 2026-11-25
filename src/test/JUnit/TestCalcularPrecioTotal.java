package test.JUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import clases.Alien;

public class TestCalcularPrecioTotal {

	@Test
	public void testSoloPrecioCuerpo() {
		Alien a1 = new Alien(20, "Verde");
		a1.calcularPrecioTotal();
		assertEquals(4.0, a1.getPrecioTotal(), 0.001);
	}

	@Test
	public void testCuerpoYExtremidades() {
		Alien a1 = new Alien(20, "Verde");
		
		a1.agregarBrazos(2); 
		a1.agregarPiernas(2);
		assertEquals(12.0, a1.getPrecioTotal(), 0.001);
	}

	@Test
	public void testCuerpoYOjos() {
		Alien a1 = new Alien(20, "Verde");
		
		a1.agregarOjos(3);
		assertEquals(7.0, a1.getPrecioTotal(), 0.001);
	}

	@Test
	public void testCalculoTotalCompleto() {
		Alien a1 = new Alien(20, "Verde");
		
		a1.agregarBrazos(3);
		a1.agregarPiernas(2);
		a1.agregarOjos(5);
		assertEquals(19.0, a1.getPrecioTotal(), 0.001);
	}

}