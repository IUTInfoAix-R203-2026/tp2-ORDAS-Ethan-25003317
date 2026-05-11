package fr.univ_amu.iut.exercice3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests de l'exercice 3 : ConvertisseurDeNombreRomain.
 *
 * <p>Progression TDD :
 *
 * <ol>
 *   <li>Chiffres de base : I, II, III - fake it puis triangulation sur la somme
 *   <li>Arrivée de V, puis VI - triangulation sur l'addition à un autre symbole
 *   <li>IV (4) et IX (9) - introduction de la soustraction
 *   <li>X, L, C, D, M - extension à tous les symboles (extract method à ce stade)
 *   <li>Grand nombre MMMMDCCCLXXXVIII (4888) - stress test
 *   <li>Exceptions : symboles inconnus et soustractions invalides
 * </ol>
 */
class ConvertisseurDeNombreRomainTest {

  private ConvertisseurDeNombreRomain convertisseur;

  @BeforeEach
  void setUp() {
    convertisseur = new ConvertisseurDeNombreRomain();
  }

  // ========= Chiffres de base =========

  @Test
  void le_convertisseur_traduit_I_en_1() {
    assertThat(convertisseur.enNombreArabe("I")).isEqualTo(1);
  }

  @Test
  void le_convertisseur_traduit_II_en_2() {
    assertThat(convertisseur.enNombreArabe("II")).isEqualTo(2);
  }

  @Test
  void le_convertisseur_traduit_III_en_3() {
    assertThat(convertisseur.enNombreArabe("III")).isEqualTo(3);
  }

  @Test
  void le_convertisseur_traduit_V_en_5() {
    assertThat(convertisseur.enNombreArabe("V")).isEqualTo(5);
  }

  @Test
  void le_convertisseur_traduit_VI_en_6() {
    assertThat(convertisseur.enNombreArabe("VI")).isEqualTo(6);
  }

  @Test
  void le_convertisseur_traduit_X_en_10() {
    assertThat(convertisseur.enNombreArabe("X")).isEqualTo(10);
  }

  @Test
  void le_convertisseur_traduit_L_en_50() {
    assertThat(convertisseur.enNombreArabe("L")).isEqualTo(50);
  }

  @Test
  void le_convertisseur_traduit_C_en_100() {
    assertThat(convertisseur.enNombreArabe("C")).isEqualTo(100);
  }

  @Test
  void le_convertisseur_traduit_D_en_500() {
    assertThat(convertisseur.enNombreArabe("D")).isEqualTo(500);
  }

  @Test
  void le_convertisseur_traduit_M_en_1000() {
    assertThat(convertisseur.enNombreArabe("M")).isEqualTo(1000);
  }

  // ========= Soustractions valides =========

  @Test
  void le_convertisseur_traduit_IV_en_4() {
    assertThat(convertisseur.enNombreArabe("IV")).isEqualTo(4);
  }

  @Test
  void le_convertisseur_traduit_IX_en_9() {
    assertThat(convertisseur.enNombreArabe("IX")).isEqualTo(9);
  }

  @Test
  void le_convertisseur_traduit_XL_en_40() {
    assertThat(convertisseur.enNombreArabe("XL")).isEqualTo(40);
  }

  @Test
  void le_convertisseur_traduit_XC_en_90() {
    assertThat(convertisseur.enNombreArabe("XC")).isEqualTo(90);
  }

  @Test
  void le_convertisseur_traduit_CD_en_400() {
    assertThat(convertisseur.enNombreArabe("CD")).isEqualTo(400);
  }

  @Test
  void le_convertisseur_traduit_CM_en_900() {
    assertThat(convertisseur.enNombreArabe("CM")).isEqualTo(900);
  }

  // ========= Compositions =========

  @Test
  void le_convertisseur_traduit_XLIX_en_49() {
    assertThat(convertisseur.enNombreArabe("XLIX")).isEqualTo(49);
  }

  @Test
  void le_convertisseur_traduit_XCIX_en_99() {
    assertThat(convertisseur.enNombreArabe("XCIX")).isEqualTo(99);
  }

  @Test
  void le_convertisseur_traduit_MMMMDCCCLXXXVIII_en_4888() {
    assertThat(convertisseur.enNombreArabe("MMMMDCCCLXXXVIII")).isEqualTo(4888);
  }

  // ========= Exceptions =========

  @Test
  void le_convertisseur_avec_Z_leve_exception() {
    assertThatThrownBy(() -> convertisseur.enNombreArabe("Z"))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void le_convertisseur_avec_IL_leve_exception() {
    assertThatThrownBy(() -> convertisseur.enNombreArabe("IL"))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void le_convertisseur_avec_IC_leve_exception() {
    assertThatThrownBy(() -> convertisseur.enNombreArabe("IC"))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void le_convertisseur_avec_ID_leve_exception() {
    assertThatThrownBy(() -> convertisseur.enNombreArabe("ID"))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void le_convertisseur_avec_IM_leve_exception() {
    assertThatThrownBy(() -> convertisseur.enNombreArabe("IM"))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void le_convertisseur_avec_VX_leve_exception() {
    assertThatThrownBy(() -> convertisseur.enNombreArabe("VX"))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void le_convertisseur_avec_XD_leve_exception() {
    assertThatThrownBy(() -> convertisseur.enNombreArabe("XD"))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void le_convertisseur_avec_XM_leve_exception() {
    assertThatThrownBy(() -> convertisseur.enNombreArabe("XM"))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
