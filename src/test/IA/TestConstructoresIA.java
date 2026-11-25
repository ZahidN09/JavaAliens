package test.IA;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import clases.Alien;

public class TestConstructoresIA {

    private static final double DELTA = 0.0001;

    // -----------------------------------------------------------------
    // 1) Asignación correcta de atributos básicos (tamanio, color, resto en 0)
    // -----------------------------------------------------------------

    @Test
    public void constructorAsignaTamanioYColorCorrectamente() {
        // Valida que el constructor guarda el tamaño y color recibidos.
        Alien alien = new Alien(12, "Azul");

        assertEquals(12, alien.getTamanio());
        assertEquals("Azul", alien.getColor());
    }

    @Test
    public void constructorInicializaContadoresEnCero() {
        // El resto de atributos (ojos, brazos, pies) deben quedar en 0.
        Alien alien = new Alien(10, "Verde");

        assertEquals(0, alien.getNumeroOjos());
        assertEquals(0, alien.getNumeroBrazos());
        assertEquals(0, alien.getNumeroPies());
    }

    // -----------------------------------------------------------------
    // 2) Aplicación de restricciones de tamaño (rango [5, 30])
    // -----------------------------------------------------------------

    @Test
    public void constructorAceptaTamanioMinimoPermitido() {
        // Borde inferior: 5 es válido, no debe reemplazarse por el valor por defecto.
        Alien alien = new Alien(5, "Rojo");
        assertEquals(5, alien.getTamanio());
    }

    @Test
    public void constructorAceptaTamanioMaximoPermitido() {
        // Borde superior: 30 es válido, no debe reemplazarse por el valor por defecto.
        Alien alien = new Alien(30, "Amarillo");
        assertEquals(30, alien.getTamanio());
    }

    @Test
    public void constructorReemplazaTamanioMenorAlMinimoPor18() {
        // Tamaño 4 < 5 → debe asignarse el valor por defecto 18.
        Alien alien = new Alien(4, "Verde");
        assertEquals(18, alien.getTamanio());
        assertEquals("Verde", alien.getColor());
    }

    @Test
    public void constructorReemplazaTamanioMayorAlMaximoPor18() {
        // Tamaño 31 > 30 → debe asignarse el valor por defecto 18.
        Alien alien = new Alien(31, "Morado");
        assertEquals(18, alien.getTamanio());
        assertEquals("Morado", alien.getColor());
    }

    @Test
    public void constructorReemplazaTamanioNegativoPor18() {
        // Tamaño negativo → debe asignarse el valor por defecto 18.
        Alien alien = new Alien(-5, "Gris");
        assertEquals(18, alien.getTamanio());
    }

    // -----------------------------------------------------------------
    // 3) Cálculo correcto de precios (20%, 10%, 5% del tamaño)
    // -----------------------------------------------------------------

    @ParameterizedTest(name = "tamanio={0} -> cuerpo={1}, extremidad={2}, ojo={3}")
    @CsvSource({
        // tamanio, precioCuerpo (20%), precioExtremidad (10%), precioOjo (5%)
        "5,   1.0, 0.5,  0.25",
        "10,  2.0, 1.0,  0.5",
        "12,  2.4, 1.2,  0.6",
        "18,  3.6, 1.8,  0.9",
        "25,  5.0, 2.5,  1.25",
        "30,  6.0, 3.0,  1.5"
    })
    public void constructorCalculaPreciosSegunTamanio(
            int tamanio, double precioCuerpoEsperado,
            double precioExtremidadEsperado, double precioOjoEsperado) {

        // Para cada tamaño válido, se verifica que:
        //   precioCuerpo      = 0.20 * tamanio
        //   precioExtremidad  = 0.10 * tamanio
        //   precioOjo         = 0.05 * tamanio
        Alien alien = new Alien(tamanio, "Test");

        assertEquals(tamanio, alien.getTamanio());
        assertEquals(precioCuerpoEsperado,     alien.getPrecioCuerpo(),     DELTA);
        assertEquals(precioExtremidadEsperado, alien.getPrecioExtremidad(), DELTA);
        assertEquals(precioOjoEsperado,        alien.getPrecioOjo(),        DELTA);
    }

    @Test
    public void constructorRecalculaPreciosConTamanioPorDefectoCuandoEntradaInvalida() {
        // Cuando el tamaño es inválido se usa 18 y los precios deben calcularse
        // sobre 18, NO sobre el valor ingresado.
        Alien alien = new Alien(100, "Rosado");

        assertEquals(18, alien.getTamanio());
        assertEquals(3.6, alien.getPrecioCuerpo(),     DELTA); // 20% de 18
        assertEquals(1.8, alien.getPrecioExtremidad(), DELTA); // 10% de 18
        assertEquals(0.9, alien.getPrecioOjo(),        DELTA); // 5% de 18
    }

    @Test
    public void constructorMantieneProporcionEntrePrecios() {
        // Verifica la proporción implícita entre precios:
        // precioCuerpo = 2 * precioExtremidad = 4 * precioOjo
        Alien alien = new Alien(20, "Cian");

        double cuerpo     = alien.getPrecioCuerpo();
        double extremidad = alien.getPrecioExtremidad();
        double ojo        = alien.getPrecioOjo();

        assertEquals(2 * extremidad, cuerpo, DELTA);
        assertEquals(4 * ojo,        cuerpo, DELTA);
        assertEquals(2 * ojo,        extremidad, DELTA);
    }
}