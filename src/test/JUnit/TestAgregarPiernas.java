package test.JUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import clases.Alien;

public class TestAgregarPiernas {

	@Test
	public void condicionNormal() {
		Alien a1 = new Alien(18, "Verde");

		boolean resultado = a1.agregarPiernas(5);

		assertTrue(resultado);
		assertEquals(a1.getNumeroPies(), 5 );
	}


	@Test
	public void condicionExcesoLimite() {
		Alien a1 = new Alien(18, "Verde");

		boolean resultado = a1.agregarPiernas(11);

		assertFalse(resultado);
		assertEquals(a1.getNumeroPies(), 0 );
	}
	
	@Test
	public void condicionInputNegativo() {
		Alien a1 = new Alien(18, "Verde");

		boolean resultado = a1.agregarPiernas(-1);

		assertFalse(resultado);
		assertEquals(a1.getNumeroPies(), 0 );
	}
	
	@Test
	public void condicionNormalConBrazos() {
		Alien a1 = new Alien(18, "Verde");
		
		a1.agregarBrazos(5);
		boolean resultado = a1.agregarPiernas(5);

		assertTrue(resultado);
		assertEquals(a1.getNumeroPies(), 5 );
	}
	
	@Test
	public void condicionExcesoLimiteConPiernas() {
		Alien a1 = new Alien(18, "Verde");
		
		a1.agregarBrazos(5);
		boolean resultado = a1.agregarPiernas(6);

		assertFalse(resultado);
		assertEquals(a1.getNumeroPies(), 0);
	}

}
