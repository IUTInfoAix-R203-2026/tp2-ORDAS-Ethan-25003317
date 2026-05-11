package fr.univ_amu.iut.exercice3;

/**
 * Exercice 3 - Convertisseur de chiffres romains en nombres arabes.
 *
 * <p>Règles :
 *
 * <ul>
 *   <li>Les symboles valides sont {@code I=1, V=5, X=10, L=50, C=100, D=500, M=1000}
 *   <li>Lus de gauche à droite, les symboles s'additionnent : {@code VI = 5 + 1 = 6}
 *   <li>Un symbole placé avant un symbole de valeur supérieure se soustrait : {@code IV = 5 - 1 =
 *       4}
 *   <li>Les seules soustractions valides sont :
 *       <ul>
 *         <li>I avant V ou X ({@code IV = 4}, {@code IX = 9})
 *         <li>X avant L ou C ({@code XL = 40}, {@code XC = 90})
 *         <li>C avant D ou M ({@code CD = 400}, {@code CM = 900})
 *       </ul>
 *       Toute autre soustraction doit lever {@link IllegalArgumentException}.
 * </ul>
 *
 * <p>Conseils TDD : commencez par gérer uniquement {@code I}, puis {@code II} / {@code III} (fake
 * it), puis {@code V} (triangulation), puis {@code VI} (addition de deux symboles différents), puis
 * {@code IV} (introduction de la soustraction). À ce moment-là, <b>extrayez une méthode</b> pour
 * calculer la valeur d'un symbole - vous en aurez besoin pour les symboles suivants.
 */
public class ConvertisseurDeNombreRomain {

  /**
   * Convertit une chaîne de chiffres romains en valeur entière.
   *
   * @param chiffreRomain chaîne composée de symboles romains (par exemple {@code "XLIX"})
   * @return la valeur entière correspondante
   * @throws IllegalArgumentException si la chaîne contient un symbole invalide ou une soustraction
   *     interdite
   */
  public int enNombreArabe(String chiffreRomain) {
    int total = valeurDe(chiffreRomain.charAt(0));

    for (int i = 1; i < chiffreRomain.length(); i++) {
      char chiffreRomainCourant = chiffreRomain.charAt(i);
      char chiffreRomainPrecedent = chiffreRomain.charAt(i - 1);
      if (estCasDeSoustraction(chiffreRomainCourant, chiffreRomainPrecedent)) {
        validerSoustraction(chiffreRomainCourant, chiffreRomainPrecedent);
        total -= 2 * valeurDe(chiffreRomainPrecedent);
      }
      total += valeurDe(chiffreRomainCourant);
    }
    return total;
  }

  private void validerSoustraction(char chiffreRomainCourant, char chiffreRomainPrecedent) {
    if (!estCasDeSoustractionValide(chiffreRomainCourant, chiffreRomainPrecedent))
      throw new IllegalArgumentException();
  }

  private boolean estCasDeSoustractionValide(
      char chiffreRomainCourant, char chiffreRomainPrecedent) {
    return chiffreRomainPrecedent == 'I' && chiffreRomainCourant == 'V'
        || chiffreRomainPrecedent == 'I' && chiffreRomainCourant == 'X'
        || chiffreRomainPrecedent == 'X' && chiffreRomainCourant == 'L'
        || chiffreRomainPrecedent == 'X' && chiffreRomainCourant == 'C'
        || chiffreRomainPrecedent == 'C' && chiffreRomainCourant == 'D'
        || chiffreRomainPrecedent == 'C' && chiffreRomainCourant == 'M';
  }

  private boolean estCasDeSoustraction(char chiffreRomainCourant, char chiffreRomainPrecedent) {
    return valeurDe(chiffreRomainPrecedent) < valeurDe(chiffreRomainCourant);
  }

  private int valeurDe(char chiffreRomain) {
    return switch (chiffreRomain) {
      case 'I' -> 1;
      case 'V' -> 5;
      case 'X' -> 10;
      case 'L' -> 50;
      case 'C' -> 100;
      case 'D' -> 500;
      case 'M' -> 1000;
      default -> throw new IllegalArgumentException();
    };
  }
}
