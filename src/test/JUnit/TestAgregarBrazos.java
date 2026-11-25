package test.JUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import clases.Alien;

public class TestAgregarBrazos {

	@Test
	public void condicionNormal() {
		Alien a1 = new Alien(18, "Verde");

		boolean resultado = a1.agregarBrazos(5);

		assertTrue(resultado);
		assertEquals(a1.getNumeroBrazos(), 5 );
		assertEquals(12.6, a1.getPrecioTotal(), 0.001);
	}


	@Test
	public void condicionExcesoLimite() {
		Alien a1 = new Alien(18, "Verde");

		boolean resultado = a1.agregarBrazos(11);

		assertFalse(resultado);
		assertEquals(a1.getNumeroBrazos(), 0 );
		assertEquals(0.0, a1.getPrecioTotal(), 0.001);
	}
	
	@Test
	public void condicionInputNegativo() {
		Alien a1 = new Alien(18, "Verde");

		boolean resultado = a1.agregarBrazos(-1);

		assertFalse(resultado);
		assertEquals(a1.getNumeroBrazos(), 0 );
		assertEquals(0.0, a1.getPrecioTotal(), 0.001);
	}
	
	@Test
	public void condicionNormalConPiernas() {
		Alien a1 = new Alien(18, "Verde");
		
		a1.agregarPiernas(5);
		boolean resultado = a1.agregarBrazos(5);

		assertTrue(resultado);
		assertEquals(a1.getNumeroBrazos(), 5 );
		assertEquals(21.6, a1.getPrecioTotal(), 0.001);
	}
	
	@Test
	public void condicionExcesoLimiteConPiernas() {
		Alien a1 = new Alien(18, "Verde");
		
		a1.agregarPiernas(5);
		boolean resultado = a1.agregarBrazos(6);

		assertFalse(resultado);
		assertEquals(a1.getNumeroBrazos(), 0);
		assertEquals(12.6, a1.getPrecioTotal(), 0.001);
	}

}