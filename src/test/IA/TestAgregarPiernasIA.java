package test.IA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import clases.Alien;

public class TestAgregarPiernasIA {

    // =================================================================
    // 1) Casos válidos SIN brazos: retorna true y actualiza el contador
    // =================================================================

    @Test
    public void agregarPiernasDentroDelLimiteRetornaTrueYActualizaContador() {
        // Caso base: alien nuevo (0 brazos, 0 piernas). Agregar 5 no supera el límite.
        Alien alien = new Alien(10, "Verde");
        assertEquals(0, alien.getNumeroPies());

        boolean resultado = alien.agregarPiernas(5);

        assertTrue(resultado);
        assertEquals(5, alien.getNumeroPies());
    }

    @Test
    public void agregarCeroPiernasRetornaTrueYNoCambiaElContador() {
        // Agregar 0 no supera el máximo: operación válida que no altera el contador.
        Alien alien = new Alien(10, "Verde");

        boolean resultado = alien.agregarPiernas(0);

        assertTrue(resultado);
        assertEquals(0, alien.getNumeroPies());
    }

    @Test
    public void agregarExactamenteHastaElLimiteRetornaTrue() {
        // Borde superior exacto: 10 + 0 + 0 = 10 NO es > 10 → debe permitirse.
        Alien alien = new Alien(10, "Verde");

        boolean resultado = alien.agregarPiernas(10);

        assertTrue(resultado);
        assertEquals(10, alien.getNumeroPies());
    }

    // =================================================================
    // 2) Casos inválidos SIN brazos: retorna false y NO modifica estado
    // =================================================================

    @Test
    public void agregarPiernasQueExcedeElLimiteRetornaFalseYNoModificaElContador() {
        // 11 + 0 + 0 = 11 > 10 → false y contador intacto en 0.
        Alien alien = new Alien(10, "Verde");

        boolean resultado = alien.agregarPiernas(11);

        assertFalse(resultado);
        assertEquals(0, alien.getNumeroPies());
    }

    @Test
    public void agregarPiernasNegativoRetornaFalseYNoModificaElContador() {
        // El método rechaza valores negativos: false y contador sin cambios.
        Alien alien = new Alien(10, "Verde");

        boolean resultado = alien.agregarPiernas(-3);

        assertFalse(resultado);
        assertEquals(0, alien.getNumeroPies());
    }

    // =================================================================
    // 3) Acumulación de piernas sobre el propio contador
    // =================================================================

    @Test
    public void agregarPiernasAcumuladasRespetanElLimiteTotal() {
        // Se construye el estado 6 → 10 y se verifica que el siguiente intento falla
        // dejando el contador intacto en 10.
        Alien alien = new Alien(10, "Verde");

        assertTrue(alien.agregarPiernas(6));
        assertEquals(6, alien.getNumeroPies());

        assertTrue(alien.agregarPiernas(4));
        assertEquals(10, alien.getNumeroPies());

        assertFalse(alien.agregarPiernas(1)); // 10 + 1 = 11 > 10
        assertEquals(10, alien.getNumeroPies());
    }

    @Test
    public void agregarPiernasTrasUnIntentoFallidoSigueSiendoPosibleDentroDelLimite() {
        // Tras un fallo, el estado no debe quedar corrupto: aún se pueden agregar
        // piernas que sí quepan dentro del límite.
        Alien alien = new Alien(10, "Verde");

        assertFalse(alien.agregarPiernas(15)); // falla, no agrega
        assertEquals(0, alien.getNumeroPies());

        assertTrue(alien.agregarPiernas(4));   // ahora sí cabe
        assertEquals(4, alien.getNumeroPies());
    }

    // =================================================================
    // 4) Casos con brazos preexistentes: el límite es piernas + brazos + piernas
    // =================================================================

    @Test
    public void agregarPiernasContabilizaBrazosPreexistentesDentroDelLimite() {
        // Con 5 brazos, agregar 5 piernas: 5 + 5 + 0 = 10 → permitido.
        Alien alien = new Alien(10, "Verde");
        assertTrue(alien.agregarBrazos(5));
        assertEquals(5, alien.getNumeroBrazos());
        assertEquals(0, alien.getNumeroPies());

        boolean resultado = alien.agregarPiernas(5);

        assertTrue(resultado);
        assertEquals(5, alien.getNumeroPies());
    }

    @Test
    public void agregarPiernasFallaCuandoLaSumaConBrazosSuperaElLimite() {
        // Con 5 brazos, agregar 6 piernas: 6 + 5 + 0 = 11 > 10 → false y sin cambios.
        Alien alien = new Alien(10, "Verde");
        assertTrue(alien.agregarBrazos(5));

        boolean resultado = alien.agregarPiernas(6);

        assertFalse(resultado);
        assertEquals(0, alien.getNumeroPies());
        assertEquals(5, alien.getNumeroBrazos()); // los brazos no deben alterarse
    }

    @Test
    public void agregarPiernasEnElBordeExactoConBrazosEsPermitido() {
        // Con 10 brazos (límite lleno), agregar 0 piernas: 0 + 10 + 0 = 10 → permitido.
        Alien alien = new Alien(10, "Verde");
        assertTrue(alien.agregarBrazos(10));

        boolean resultado = alien.agregarPiernas(0);

        assertTrue(resultado);
        assertEquals(0, alien.getNumeroPies());
        assertEquals(10, alien.getNumeroBrazos());
    }

    @Test
    public void agregarCualquierPiernaFallaCuandoBrazosYaOcupanElLimite() {
        // Con 10 brazos no cabe ninguna pierna adicional: 1 + 10 + 0 = 11 > 10 → false.
        Alien alien = new Alien(10, "Verde");
        assertTrue(alien.agregarBrazos(10));

        boolean resultado = alien.agregarPiernas(1);

        assertFalse(resultado);
        assertEquals(0, alien.getNumeroPies());
        assertEquals(10, alien.getNumeroBrazos());
    }

    @Test
    public void agregarPiernasConsideraBrazosYPiernasYaExistentesSimultaneamente() {
        // Estado previo: 4 brazos + 3 piernas = 7 extremidades.
        // Agregar 3 piernas: 3 + 4 + 3 = 10 → permitido.
        // Intentar agregar 1 más: 1 + 4 + 6 = 11 → rechazado.
        Alien alien = new Alien(10, "Verde");
        assertTrue(alien.agregarBrazos(4));
        assertTrue(alien.agregarPiernas(3));

        assertTrue(alien.agregarPiernas(3));
        assertEquals(4, alien.getNumeroBrazos());
        assertEquals(6, alien.getNumeroPies());

        assertFalse(alien.agregarPiernas(1));
        assertEquals(4, alien.getNumeroBrazos());
        assertEquals(6, alien.getNumeroPies());
    }
}