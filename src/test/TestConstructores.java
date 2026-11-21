package test;

import clases.Alien;

public class TestConstructores {

	public static void main(String[] args) {
		Alien a1 = new Alien(20, "Verde");
		Alien a2 = new Alien(1000, "Rojo");

		a1.imprimir();
		a2.imprimir();

	}

}
