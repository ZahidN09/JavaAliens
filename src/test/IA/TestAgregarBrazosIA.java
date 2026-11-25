package test.IA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import clases.Alien;

public class TestAgregarBrazosIA {

    // =================================================================
    // 1) Casos válidos SIN piernas: retorna true y actualiza el contador
    // =================================================================

    @Test
    public void agregarBrazosDentroDelLimiteRetornaTrueYActualizaContador() {
        // Caso base: alien nuevo (0 brazos, 0 pies). Agregar 5 no supera el límite.
        Alien alien = new Alien(10, "Verde");
        assertEquals(0, alien.getNumeroBrazos());

        boolean resultado = alien.agregarBrazos(5);

        assertTrue(resultado);
        assertEquals(5, alien.getNumeroBrazos());
    }

    @Test
    public void agregarCeroBrazosRetornaTrueYNoCambiaElContador() {
        // Agregar 0 no supera el máximo: operación válida que no altera el contador.
        Alien alien = new Alien(10, "Verde");

        boolean resultado = alien.agregarBrazos(0);

        assertTrue(resultado);
        assertEquals(0, alien.getNumeroBrazos());
    }

    @Test
    public void agregarExactamenteHastaElLimiteRetornaTrue() {
        // Borde superior exacto: 10 + 0 + 0 = 10 NO es > 10 → debe permitirse.
        Alien alien = new Alien(10, "Verde");

        boolean resultado = alien.agregarBrazos(10);

        assertTrue(resultado);
        assertEquals(10, alien.getNumeroBrazos());
    }

    // =================================================================
    // 2) Casos inválidos SIN piernas: retorna false y NO modifica estado
    // =================================================================

    @Test
    public void agregarBrazosQueExcedeElLimiteRetornaFalseYNoModificaElContador() {
        // 11 + 0 + 0 = 11 > 10 → false y contador intacto en 0.
        Alien alien = new Alien(10, "Verde");

        boolean resultado = alien.agregarBrazos(11);

        assertFalse(resultado);
        assertEquals(0, alien.getNumeroBrazos());
    }

    @Test
    public void agregarBrazosNegativoRetornaFalseYNoModificaElContador() {
        // El método rechaza valores negativos: false y contador sin cambios.
        Alien alien = new Alien(10, "Verde");

        boolean resultado = alien.agregarBrazos(-3);

        assertFalse(resultado);
        assertEquals(0, alien.getNumeroBrazos());
    }

    // =================================================================
    // 3) Acumulación de brazos sobre el propio contador
    // =================================================================

    @Test
    public void agregarBrazosAcumuladosRespetanElLimiteTotal() {
        // Se construye el estado 6 → 10 y se verifica que el siguiente intento falla
        // dejando el contador intacto en 10.
        Alien alien = new Alien(10, "Verde");

        assertTrue(alien.agregarBrazos(6));
        assertEquals(6, alien.getNumeroBrazos());

        assertTrue(alien.agregarBrazos(4));
        assertEquals(10, alien.getNumeroBrazos());

        assertFalse(alien.agregarBrazos(1)); // 10 + 1 = 11 > 10
        assertEquals(10, alien.getNumeroBrazos());
    }

    @Test
    public void agregarBrazosTrasUnIntentoFallidoSigueSiendoPosibleDentroDelLimite() {
        // Tras un fallo, el estado no debe quedar corrupto: aún se pueden agregar
        // brazos que sí quepan dentro del límite.
        Alien alien = new Alien(10, "Verde");

        assertFalse(alien.agregarBrazos(15)); // falla, no agrega
        assertEquals(0, alien.getNumeroBrazos());

        assertTrue(alien.agregarBrazos(4));   // ahora sí cabe
        assertEquals(4, alien.getNumeroBrazos());
    }

    // =================================================================
    // 4) Casos con piernas preexistentes: el límite es brazos + brazos + pies
    // =================================================================

    @Test
    public void agregarBrazosContabilizaPiernasPreexistentesDentroDelLimite() {
        // Con 5 piernas, agregar 5 brazos: 5 + 0 + 5 = 10 → permitido.
        Alien alien = new Alien(10, "Verde");
        assertTrue(alien.agregarPiernas(5));
        assertEquals(5, alien.getNumeroPies());
        assertEquals(0, alien.getNumeroBrazos());

        boolean resultado = alien.agregarBrazos(5);

        assertTrue(resultado);
        assertEquals(5, alien.getNumeroBrazos());
    }

    @Test
    public void agregarBrazosFallaCuandoLaSumaConPiernasSuperaElLimite() {
        // Con 5 piernas, agregar 6 brazos: 6 + 0 + 5 = 11 > 10 → false y sin cambios.
        Alien alien = new Alien(10, "Verde");
        assertTrue(alien.agregarPiernas(5));

        boolean resultado = alien.agregarBrazos(6);

        assertFalse(resultado);
        assertEquals(0, alien.getNumeroBrazos());
        assertEquals(5, alien.getNumeroPies()); // las piernas no deben alterarse
    }

    @Test
    public void agregarBrazosEnElBordeExactoConPiernasEsPermitido() {
        // Con 10 piernas (límite lleno), agregar 0 brazos: 0 + 0 + 10 = 10 → permitido.
        Alien alien = new Alien(10, "Verde");
        assertTrue(alien.agregarPiernas(10));

        boolean resultado = alien.agregarBrazos(0);

        assertTrue(resultado);
        assertEquals(0, alien.getNumeroBrazos());
        assertEquals(10, alien.getNumeroPies());
    }

    @Test
    public void agregarCualquierBrazoFallaCuandoPiernasYaOcupanElLimite() {
        // Con 10 piernas no cabe ningún brazo adicional: 1 + 0 + 10 = 11 > 10 → false.
        Alien alien = new Alien(10, "Verde");
        assertTrue(alien.agregarPiernas(10));

        boolean resultado = alien.agregarBrazos(1);

        assertFalse(resultado);
        assertEquals(0, alien.getNumeroBrazos());
        assertEquals(10, alien.getNumeroPies());
    }

    @Test
    public void agregarBrazosConsideraBrazosYPiernasYaExistentesSimultaneamente() {
        // Estado previo: 3 brazos + 4 piernas = 7 extremidades.
        // Agregar 3 brazos: 3 + 3 + 4 = 10 → permitido.
        // Intentar agregar 1 más: 1 + 6 + 4 = 11 → rechazado.
        Alien alien = new Alien(10, "Verde");
        assertTrue(alien.agregarBrazos(3));
        assertTrue(alien.agregarPiernas(4));

        assertTrue(alien.agregarBrazos(3));
        assertEquals(6, alien.getNumeroBrazos());
        assertEquals(4, alien.getNumeroPies());

        assertFalse(alien.agregarBrazos(1));
        assertEquals(6, alien.getNumeroBrazos());
        assertEquals(4, alien.getNumeroPies());
    }
}