package fr.univ_amu.iut.exercice1;

/**
 * Exercice 1 - Hello World.
 *
 * <p>Premier contact avec le cycle TDD "RED - GREEN - REFACTOR". Écrivez le minimum de code pour
 * faire passer chaque test, en suivant la stratégie de Kent Beck :
 *
 * <ol>
 *   <li><b>Fake it</b> - renvoyez une valeur en dur (ex: {@code return "Hello, World!";})
 *   <li><b>Triangulation</b> - quand un deuxième test vous force à généraliser, introduisez le
 *       minimum de logique
 *   <li><b>Obvious</b> - quand l'implémentation tient en une ligne évidente, écrivez-la directement
 * </ol>
 */
public class HelloWorld {

  /**
   * Retourne une salutation personnalisée.
   *
   * @param nom nom de la personne à saluer, ou {@code null} / chaîne vide pour une salutation
   *     générique
   * @return {@code "Hello, World!"} si {@code nom} est {@code null} ou vide, {@code "Hello,
   *     <nom>!"} sinon
   */
  public static String saluer(String nom) {
    // TODO exercice 1 : gérer le cas où nom est renseigné (sinon on retourne
    // "Hello, World!" par défaut, ce qui suffit pour les deux premiers tests).
    // Activez les tests un par un. Fake it d'abord, triangulez quand un
    // deuxième test vous y force.
    if (nom == null || nom.isEmpty()) return "Hello, World!";
    return "Hello, " + nom + "!";
  }
}
