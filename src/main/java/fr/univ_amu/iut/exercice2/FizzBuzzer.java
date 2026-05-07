package fr.univ_amu.iut.exercice2;

/**
 * Exercice 2 - FizzBuzz.
 *
 * <p>Classique des entretiens d'embauche et exercice parfait pour pratiquer la <b>triangulation</b>
 * : commencez par un retour en dur, puis généralisez dès qu'un deuxième test vous y force.
 *
 * <p>Règles : pour chaque entier {@code n},
 *
 * <ul>
 *   <li>si {@code n} est multiple de 3 <em>et</em> de 5, retourner {@code "FizzBuzz"}
 *   <li>sinon si {@code n} est multiple de 3, retourner {@code "Fizz"}
 *   <li>sinon si {@code n} est multiple de 5, retourner {@code "Buzz"}
 *   <li>sinon retourner la représentation textuelle de {@code n}
 * </ul>
 */
public class FizzBuzzer {

  /**
   * Retourne la représentation FizzBuzz d'un entier.
   *
   * @param n entier strictement positif
   * @return la chaîne FizzBuzz correspondante
   */
  public String fizzBuzz(int n) {
    // TODO exercice 2 : ajouter les cas Fizz, Buzz et FizzBuzz avant le
    // return par défaut. Activez les tests dans l'ordre : 1 et 2 passent
    // directement (fake it via le return final), 3 demande d'introduire
    // un premier cas, etc.
    if (n % 3 == 0 && n % 5 == 0) return "FizzBuzz";
    if (n % 3 == 0) return "Fizz";
    if (n % 5 == 0) return "Buzz";
    return String.valueOf(n);
  }

  /**
   * Retourne la séquence FizzBuzz des entiers de 1 à {@code n} inclus.
   *
   * @param n taille de la séquence demandée (positif)
   * @return tableau de {@code n} chaînes FizzBuzz dans l'ordre croissant
   */
  public String[] fizzBuzzJusquA(int n) {
    String[] sequence = new String[n];
    // TODO exercice 2 : remplir sequence[i] en réutilisant fizzBuzz(i+1).
    // Ne dupliquez pas la logique : appelez fizzBuzz !
    for (int i = 1; i < n + 1; i += 1) {
      if (i % 3 == 0 && i % 5 == 0) {
        sequence[i - 1] = "FizzBuzz";
        continue;
      }
      if (i % 3 == 0) {
        sequence[i - 1] = "Fizz";
        continue;
      }
      if (i % 5 == 0) {
        sequence[i - 1] = "Buzz";
        continue;
      }
      sequence[i - 1] = String.valueOf(i);
    }
    return sequence;
  }
}
