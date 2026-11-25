package clases;

public class Alien {

	private int tamanio;
	private String color;
	private int numeroOjos;
	private int numeroBrazos;
	private int numeroPies;
	private double precioExtremidad;
	private double precioOjo;
	private double precioCuerpo;

	public Alien(int tamanio, String color) {
		if (tamanio < 5 || tamanio > 30) {
			tamanio = 18;
		}

		this.tamanio = tamanio;
		this.color = color;
		this.numeroOjos = 0;
		this.numeroBrazos = 0;
		this.numeroPies = 0;
		this.precioCuerpo = 0.2 * tamanio;
		this.precioExtremidad = 0.1 * tamanio;
		this.precioOjo = 0.05 * tamanio;
	}

	public int getTamanio() {
		return tamanio;
	}

	public String getColor() {
		return color;
	}

	public int getNumeroOjos() {
		return numeroOjos;
	}

	public int getNumeroBrazos() {
		return numeroBrazos;
	}

	public int getNumeroPies() {
		return numeroPies;
	}

	public double getPrecioExtremidad() {
		return precioExtremidad;
	}

	public double getPrecioOjo() {
		return precioOjo;
	}

	public double getPrecioCuerpo() {
		return precioCuerpo;
	}

	public void imprimir() {
		System.out.println("********** CARACTERISTICAS DEL ALIEN **********");
		System.out.println("Tamaño: " + this.tamanio);
		System.out.println("Color: " + this.color);
		System.out.println("Número de Ojos: " + this.numeroOjos);
		System.out.println("Número de Brazos: " + this.numeroBrazos);
		System.out.println("Número de Pies: " + this.numeroPies);
		System.out.println("Precio de Extremidad: " + this.precioExtremidad);
		System.out.println("Precio de  Ojo: " + this.precioOjo);
		System.out.println("Precio de Cuerpo: " + this.precioCuerpo);
	}

	public boolean agregarBrazos(int brazos) {
		if (brazos + this.numeroBrazos + this.numeroPies > 10 || brazos < 0)
			return false;
		this.numeroBrazos += brazos;
		return true;
	}
	
	public boolean agregarPiernas(int piernas) {
		if (piernas + this.numeroBrazos + this.numeroPies > 10 || piernas < 0)
			return false;
		this.numeroPies += piernas;
		return true;
	}
}