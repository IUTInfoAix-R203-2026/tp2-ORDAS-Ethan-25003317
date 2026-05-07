package fr.univ_amu.iut.exercice1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests de l'exercice 1 : HelloWorld.
 *
 * <p>Les tests sont organisés pour vous faire vivre le cycle TDD :
 *
 * <ul>
 *   <li>Tests 1 à 2 : la salutation par défaut (null et chaîne vide) - fake it {@code return
 *       "Hello, World!"} passe
 *   <li>Tests 3 à 5 : plusieurs noms distincts - force à introduire l'interpolation (triangulation)
 *   <li>Tests 6 à 7 : noms plus exotiques (accentués, avec espace) - régression pour vérifier que
 *       la concaténation marche toujours
 * </ul>
 *
 * <p>Activez-les un par un en retirant l'annotation {@code @Disabled} du test courant, faites-le
 * passer au vert, puis passez au suivant.
 */
class HelloWorldTest {

  // ========= Salutation par défaut =========

  @Test
  void saluer_sans_nom_retourne_hello_world() {
    assertThat(HelloWorld.saluer(null)).isEqualTo("Hello, World!");
  }

  @Test
  void saluer_chaine_vide_retourne_hello_world() {
    assertThat(HelloWorld.saluer("")).isEqualTo("Hello, World!");
  }

  // ========= Triangulation : plusieurs noms =========

  @Test
  void saluer_alice_retourne_hello_alice() {
    assertThat(HelloWorld.saluer("Alice")).isEqualTo("Hello, Alice!");
  }

  @Test
  void saluer_bob_retourne_hello_bob() {
    assertThat(HelloWorld.saluer("Bob")).isEqualTo("Hello, Bob!");
  }

  @Test
  void saluer_nom_une_lettre_retourne_hello_lettre() { // Force à ne pas confondre "nom court" avec
    // "chaîne vide".
    assertThat(HelloWorld.saluer("X")).isEqualTo("Hello, X!");
  }

  // ========= Régression : noms plus exotiques =========

  @Test
  void saluer_eric_retourne_hello_eric() {
    assertThat(HelloWorld.saluer("Éric")).isEqualTo("Hello, Éric!");
  }

  @Test
  void saluer_jean_dupont_retourne_hello_jean_dupont() {
    assertThat(HelloWorld.saluer("Jean Dupont")).isEqualTo("Hello, Jean Dupont!");
  }
}
