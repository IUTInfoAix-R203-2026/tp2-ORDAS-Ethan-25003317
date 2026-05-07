package fr.univ_amu.iut.exercice2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests de l'exercice 2 : FizzBuzz.
 *
 * <p>Progression TDD :
 *
 * <ul>
 *   <li>Les 3 premiers tests (1, 2, 3) vous font découvrir la triangulation : commencez par un
 *       {@code return "1";} en dur, puis généralisez progressivement.
 *   <li>Les tests 4 à 9 ajoutent Buzz, FizzBuzz et les multiples plus éloignés.
 *   <li>Les 3 derniers tests passent de {@code fizzBuzz(int)} (une valeur) à {@code
 *       fizzBuzzJusquA(int)} (séquence complète).
 * </ul>
 */
class FizzBuzzerTest {

  private FizzBuzzer fizzBuzz;

  @BeforeEach
  void setUp() {
    fizzBuzz = new FizzBuzzer();
  }

  @Test
  void fizz_buzz_de_1_retourne_1() {
    assertThat(fizzBuzz.fizzBuzz(1)).isEqualTo("1");
  }

  @Test
  void fizz_buzz_de_2_retourne_2() {
    assertThat(fizzBuzz.fizzBuzz(2)).isEqualTo("2");
  }

  @Test
  void fizz_buzz_de_3_retourne_fizz() {
    assertThat(fizzBuzz.fizzBuzz(3)).isEqualTo("Fizz");
  }

  @Test
  void fizz_buzz_de_4_retourne_4() {
    assertThat(fizzBuzz.fizzBuzz(4)).isEqualTo("4");
  }

  @Test
  void fizz_buzz_de_5_retourne_buzz() {
    assertThat(fizzBuzz.fizzBuzz(5)).isEqualTo("Buzz");
  }

  @Test
  void fizz_buzz_de_6_retourne_fizz() {
    assertThat(fizzBuzz.fizzBuzz(6)).isEqualTo("Fizz");
  }

  @Test
  void fizz_buzz_de_9_retourne_fizz() {
    assertThat(fizzBuzz.fizzBuzz(9)).isEqualTo("Fizz");
  }

  @Test
  void fizz_buzz_de_10_retourne_buzz() {
    assertThat(fizzBuzz.fizzBuzz(10)).isEqualTo("Buzz");
  }

  @Test
  void fizz_buzz_de_15_retourne_fizz_buzz() {
    assertThat(fizzBuzz.fizzBuzz(15)).isEqualTo("FizzBuzz");
  }

  @Test
  void fizz_buzz_jusqu_a_5_retourne_un_tableau_de_5_elements() {
    assertThat(fizzBuzz.fizzBuzzJusquA(5)).hasSize(5);
  }

  @Test
  void fizz_buzz_jusqu_a_5_retourne_1_2_fizz_4_buzz() {
    assertThat(fizzBuzz.fizzBuzzJusquA(5)).containsExactly("1", "2", "Fizz", "4", "Buzz");
  }

  @Test
  void fizz_buzz_jusqu_a_15_correspond_aux_appels_individuels() {
    String[] sequence = fizzBuzz.fizzBuzzJusquA(15);
    for (int i = 0; i < sequence.length; i++) {
      assertThat(sequence[i])
          .as("fizzBuzzJusquA(15)[%d] doit correspondre à fizzBuzz(%d)", i, i + 1)
          .isEqualTo(fizzBuzz.fizzBuzz(i + 1));
    }
  }
}
