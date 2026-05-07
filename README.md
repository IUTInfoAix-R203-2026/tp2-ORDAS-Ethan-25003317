# <img src=".github/assets/logo.png" alt="class logo" class="logo" width="120"/> R2.03 - Qualité de développement

### IUT d'Aix-Marseille - Département Informatique Aix-en-Provence

- **Ressource :** [Syllabus R2.03](https://github.com/IUTInfoAix-R203/syllabus) (compétences, calendrier, évaluations, ressources détaillées)

- **Équipe pédagogique :**

  - [Sébastien Nedjar](mailto:sebastien.nedjar@univ-amu.fr) - responsable du module
  - [Sophie Nabitz](mailto:sophie.nabitz@univ-avignon.fr)
  - [Leïla Sakli Miled](mailto:leila.SAKLI@univ-amu.fr)

- **Besoin d'aide ?**
    - Consulter et/ou créer des [issues](https://github.com/IUTInfoAix-R203/tp2/issues)
    - [Email](mailto:sebastien.nedjar@univ-amu.fr) pour toute question


## TP2 - TDD : premier contact avec le développement piloté par les tests

## Objectifs de la séance

### Ce que vous saurez faire à la fin de cette séance

Les exercices de ce TP sont organisés en progression. Cette progression suit la **taxonomie de Bloom**, un modèle pédagogique qui décrit les niveaux de maîtrise d'un savoir-faire -du plus simple (comprendre un concept) au plus complexe (créer une application complète).

| Niveau Bloom | Exercices | Vous serez capable de... | Compétence BUT |
|---|---|---|---|
| **Comprendre** | 1 - 2 | Appliquer le cycle **RED → GREEN → REFACTOR** en baby steps, alterner les stratégies **fake-it** et **triangulation** pour écrire le minimum de code qui fait passer le test courant | AC11.03 |
| **Appliquer** | 3 - 4 | Écrire du code tests-first sur des algorithmes classiques (parsing de chiffres romains, simulation d'un robot), lever et vérifier des exceptions, extraire des méthodes privées quand elles améliorent la lisibilité | AC11.02, AC11.03 |
| **Analyser / Créer** | 5 et bonus | Choisir la bonne forme d'assertion (`assertEquals` vs **ApprovalTests** pour les sorties textuelles), concevoir une progression de tests qui part des cas dégénérés vers les cas riches, dérouler un kata progressif (StringCalculator) en vraie autonomie | AC11.02, AC11.03 |

**Tout au long du TP** vous continuez à pratiquer le workflow Git du TP1 : une branche par exercice, une Pull Request par branche, une revue croisée avec un·e camarade et Copilot. C'est la mise en œuvre concrète de **AC15.02 - mettre en place les outils de gestion de projet**, sans laquelle un TP de TDD reste un exercice scolaire déconnecté de la réalité d'une équipe.

### Documentation de référence

- [Java 25 API Documentation](https://docs.oracle.com/en/java/javase/25/docs/api/)
- [JUnit 6 User Guide](https://docs.junit.org/current/user-guide/)
- [AssertJ Core Documentation](https://assertj.github.io/doc/)
- [ApprovalTests Java](https://github.com/approvals/ApprovalTests.Java)

---

<details>
<summary><strong>Mise en place du Codespace</strong> (rappel R2.02 / TP1) - déplier si besoin</summary>

La mise en place se fait en deux étapes : accepter le devoir sur GitHub Classroom (qui crée votre dépôt personnel), puis ouvrir ce dépôt dans un Codespace (votre environnement de développement dans le navigateur).

### Étape 1 - Accepter le devoir sur GitHub Classroom

1. Cliquez sur le lien suivant :

   👉 **https://classroom.github.com/a/HP_p0YPk**

2. Si c'est votre première utilisation de GitHub Classroom, autorisez l'accès à votre compte GitHub.
3. Sélectionnez votre nom dans la liste des étudiants (si elle apparaît) pour associer votre compte GitHub à votre identité dans le cours.
4. Cliquez sur **"Accept this assignment"**.
5. Attendez quelques secondes - GitHub crée automatiquement un dépôt à votre nom : `IUTInfoAix-R203-2026/tp2-votreLogin`.
6. Cliquez sur le lien du dépôt créé pour y accéder.

### Étape 2 - Ouvrir le projet dans GitHub Codespaces

Une fois sur la page de votre dépôt :

1. Cliquez sur le bouton vert **"Code"** (en haut à droite du listing de fichiers).
2. Sélectionnez l'onglet **"Codespaces"**.
3. Cliquez sur **"Create codespace on main"**.

<img src="src/main/resources/assets/create_codespace_on_main.png" alt="Bouton Code -> Codespaces -> Create codespace on main" width="400"/>

4. Attendez que l'environnement se construise (de 1 à 5 minutes la première fois).
5. VS Code s'ouvre **dans votre navigateur** avec tout l'environnement pré-configuré :
   - Java 25
   - Maven (via le wrapper `./mvnw`)
   - Git
   - Copilot Chat (votre assistant IA pédagogique)
   - Toutes les extensions nécessaires

![VS Code dans le navigateur après ouverture du Codespace](src/main/resources/assets/codespace_vscode.png)

> [!IMPORTANT]
> Le Codespace est **personnel et persistant**. Quand vous fermez l'onglet, votre travail est sauvegardé. Pour reprendre, retournez sur votre dépôt GitHub -> **"Code"** -> **"Codespaces"** -> cliquez sur le Codespace existant (ne créez pas un nouveau à chaque fois).

### Vérification rapide

Une fois le Codespace ouvert, ouvrez un terminal via le menu **Terminal -> New Terminal** :

![Menu Terminal -> New Terminal](src/main/resources/assets/codespace_vscode_nouveau_terminal.png)

Puis lancez :

```bash
./mvnw test
```

Vous devriez voir un résultat du type :
```
Tests run: X, Failures: 0, Errors: 0, Skipped: X
BUILD SUCCESS
```

Si c'est le cas, tout est prêt. Le seul test actif (`AppTest`) passe, et les tests d'exercices sont en attente (`Skipped`) - c'est normal, ils seront activés au fil de votre progression.

</details>

---

<details>
<summary><strong>Comment vous êtes évalué·e</strong> (autograding /1000, rappel R2.02) - déplier si besoin</summary>

### Comment vous êtes évalués

L'évaluation de ce TP est **entièrement automatique** : à chaque fois que vous poussez (`push`) votre code sur GitHub, un système d'autograding exécute tous vos tests et calcule un score sur **1000 points**. Ce score est affiché tel quel par le reporter Classroom ; pour le ramener à la note sur 20, divisez par 50 (ex : 850/1000 = 17/20).

- **100 points** sont attribués si le projet **compile** correctement
- **900 points** sont répartis entre les différents **tests des exercices**, chaque test valant au moins 1 point
- Un test `@Disabled` (non encore activé) rapporte **0 point** - c'est normal
- Un test activé et **qui passe** rapporte ses points
- Un test activé et **qui échoue** rapporte 0 point

Votre score augmente progressivement au fil de votre avancement. Il n'y a pas de date limite brutale : chaque push met à jour votre score.

### Consulter votre note actuelle

Après chaque `push`, rendez-vous sur la page de votre dépôt GitHub -> onglet **"Actions"** -> dernier run du workflow **"GitHub Classroom Workflow"**. Le score apparaît dans le résumé :

```
Points 250/1000
```

Vous pouvez aussi voir le détail test par test pour savoir exactement quels exercices sont validés et lesquels restent à faire.

</details>

---

<details>
<summary><strong>Commandes Maven essentielles</strong> (rappel R2.02) - déplier si besoin</summary>

**Maven** est un outil de construction de projets Java utilisé dans la majorité des projets professionnels. Il gère automatiquement la compilation du code, le téléchargement des bibliothèques nécessaires (JUnit, AssertJ, Mockito, ApprovalTests...), l'exécution des tests et le packaging de l'application. Plutôt que de lancer `javac` et `java` à la main avec des dizaines d'options, une seule commande Maven suffit.

Dans ce projet, Maven est embarqué via un **Maven Wrapper** (`./mvnw`) : un script qui télécharge et utilise automatiquement la bonne version de Maven. Aucune installation n'est nécessaire : la première exécution prend quelques secondes de plus (téléchargement), puis tout est instantané.

| Commande | Effet |
|----------|-------|
| `./mvnw compile exec:java` | Lance le menu console de l'application (choisit un exercice) |
| `./mvnw test` | Exécute les tests unitaires |
| `./mvnw clean test` | Rebuild propre (supprime `target/` puis relance les tests) |
| `./mvnw clean` | Supprime les artefacts (`target/`) |
| `./mvnw spotless:apply` | Formate le code Java (Google Java Style) |

> [!NOTE]
> Le code est aussi formaté **automatiquement** avant chaque commit via un hook pre-commit invisible. Il n'est pas nécessaire de lancer `spotless:apply` à la main, sauf pour vérifier visuellement le formatage avant un commit.

</details>

---

<details>
<summary><strong>Workflow Git par exercice</strong> (branche / PR / merge, rappel TP1) - déplier si besoin</summary>

Chaque exercice suit le même cycle. Cette démarche structurée vous aide à travailler de manière **méthodique et professionnelle** : c'est exactement le workflow que vous utiliserez en entreprise.

**1. Créer une branche pour l'exercice**

```bash
git checkout -b exerciceN
```

**2. Activer le premier test** - ouvrez le fichier de test correspondant et retirez l'annotation `@Disabled` du premier test.

**3. Vérifier que le test est rouge**

```bash
./mvnw test
```

Le test doit échouer - c'est normal et attendu. Le message d'erreur vous indique ce que le test attend.

**4. Implémenter le minimum** pour faire passer ce test au vert. Pas plus que nécessaire.

**5. Vérifier que le test passe**

```bash
./mvnw test
```

**6. Lancer l'application** pour voir le résultat depuis le menu :

```bash
./mvnw compile exec:java
```

Ou via `Ctrl+Shift+B` dans VS Code.

**7. Recommencer** - activez le test suivant (étapes 2 à 6) jusqu'à ce que tous les tests de l'exercice soient verts.

**8. Finaliser l'exercice** - quand tous les tests passent :

```bash
git add .
git commit -m "feat(exerciceN): termine l'exercice"
git push -u origin exerciceN
```

> Le format `feat(exerciceN): ...` suit la convention [Conventional Commits](https://www.conventionalcommits.org/fr/) découverte au TP1 : type (`feat`, `fix`, `docs`, `chore`...), scope entre parenthèses, puis résumé court à l'impératif.

**9. Créer une Pull Request** pour voir votre travail et recevoir une review automatique :

```bash
gh pr create --title "feat(exerciceN): termine l'exercice" --body "Tous les tests passent."
```

Ouvrez la PR dans le navigateur (`gh pr view --web`) pour consulter le diff, les checks CI, le score autograding et les commentaires de la review Copilot.

**10. Merger et passer à la suite** :

```bash
gh pr merge --rebase --delete-branch
```

Cette commande merge la PR, bascule votre HEAD local sur `main`, `pull` les
derniers commits et supprime la branche de feature locale et distante.

Votre score sur GitHub Actions augmente à chaque exercice terminé. Vous pouvez maintenant passer à l'exercice suivant en reprenant à l'étape 1.

> [!TIP]
> **Copilot Chat** est là pour vous accompagner à chaque étape. N'hésitez pas à lui poser des questions - il vous guidera sans donner la solution directement.

</details>

---

<details>
<summary><strong>Copilot Chat comme tuteur</strong> (rappel R2.02 / TP1) - déplier si besoin</summary>

Vous avez le droit d'utiliser **Copilot Chat** (panneau latéral dans VS Code) quand vous bloquez sur un exercice. Il est configuré spécifiquement pour ce TP : il ne donnera pas la solution directement, mais vous guidera par étapes : d'abord une explication du concept, puis un pointeur vers la documentation, et seulement en dernier recours un minimum de code.

**Copilot Chat n'est pas un raccourci, c'est un tuteur.** Il vous aide à comprendre, pas à copier-coller. L'objectif est que vous soyez capable d'écrire ce code **seul(e)** à la fin de la séance.

**Pourquoi c'est important** : l'évaluation de ce module se fera **sur papier, sans aucun outil d'assistance**. Il est donc essentiel que vous construisiez vos automatismes en écrivant le code vous-même. Copilot Chat est un filet de sécurité pour débloquer, pas un substitut à la réflexion.

**Conseil pratique** : sur les premiers exercices, n'hésitez pas à demander de l'aide pour vous familiariser avec les concepts et le workflow. Sur les exercices avancés, essayez d'aller le plus loin possible par vous-même avant de solliciter l'assistant. C'est cette progression vers l'autonomie qui vous préparera le mieux aux évaluations.

Le TP est découpé en plusieurs **exercices** à faire dans l'ordre. Chaque exercice vit dans son propre sous-paquet (code et tests en miroir). L'exercice 1 est très guidé pas à pas pour vous familiariser avec l'environnement. À partir de l'exercice 2, une boucle de travail systématique est introduite que vous appliquerez pour tous les exercices suivants.

</details>

---

## Lire les noms de tests

Tout au long du TP, les méthodes de test suivent une même structure de nommage que vous lirez naturellement comme une phrase française : **`<sujet>_<action>_<conséquence>`**.

Quelques exemples concrets, extraits du TP :

| Nom de la méthode | Lecture |
|---|---|
| `saluer_alice_retourne_hello_alice` | Saluer Alice retourne Hello, Alice. |
| `le_robot_tourne_a_droite_depuis_nord_donne_est` | Le robot tourne à droite depuis nord donne est. |
| `le_robot_avance_sans_changer_l_orientation` | Le robot avance sans changer l'orientation. |
| `le_convertisseur_traduit_XLIX_en_49` | Le convertisseur traduit XLIX en 49. |
| `la_calculatrice_avec_un_seul_nombre_retourne_ce_nombre` | La calculatrice avec un seul nombre retourne ce nombre. |
| `la_grille_avec_une_mine_unique_au_centre_est_correctement_annotee` | La grille avec une mine unique au centre est correctement annotée. |
| `la_calculatrice_avec_un_nombre_negatif_leve_exception` | La calculatrice avec un nombre négatif lève une exception. |

**Pourquoi cette forme ?**

- **Elle se lit toute seule.** Pas besoin d'aller voir le corps de la méthode pour comprendre ce qui est testé : le nom suffit.
- **Elle est exhaustive.** Le sujet (à qui on parle), l'action (ce qu'on fait) et la conséquence (ce qu'on attend) sont tous nommés.
- **Elle est en français.** Le code de production reste en anglais idiomatique (convention Java) mais les tests racontent l'histoire de votre code dans la langue dans laquelle vous pensez.

**Petites règles à retenir si vous écrivez de nouveaux tests :**

1. **`snake_case`** (mots séparés par `_`), pas `camelCase`. Plus lisible quand les noms s'allongent.
2. **Verbe conjugué à la 3e personne du singulier** : *le robot tourne* (pas `tourner`), *la calculatrice additionne* (pas `additionner`).
3. **Forme négative avec `sans`** plutôt que `ne ... pas` : `tourne_a_droite_sans_changer_la_position` plutôt que `tourne_a_droite_ne_change_pas_la_position`.
4. **Pas de verbe vague** comme `fonctionne` ou `marche`. Préférez le verbe précis qui décrit l'effet : `retourne_X`, `donne_X`, `additionne`, `traduit_X_en_Y`, `leve_exception`, `est_correctement_X`.
5. **La longueur n'est pas un problème, la lisibilité l'est.** Un nom de dix mots qui se lit comme une phrase est meilleur qu'un nom de trois mots qui force à ouvrir le corps du test.

> [!TIP]
> Cette forme transforme vos tests en **documentation exécutable**. Quand un collègue (ou vous-même dans six mois) lit la liste des tests d'une classe, il comprend en quelques secondes ce que la classe est censée faire et garantir, sans lire une seule ligne de code de test.

---

## Lancer un test rapidement (CodeLens)

Au-dessus de chaque méthode `@Test` dans VS Code, un lien cliquable **Run Test** apparaît. Il lance le test courant en une seconde, sans avoir à taper de commande Maven.

![CodeLens Run Test au-dessus d'une méthode @Test](src/main/resources/assets/codelens_run_test.png)

- **Run Test** au-dessus de la méthode : exécute **ce seul test**. Idéal pour la boucle TDD rouge → vert.
- **Run Test** au-dessus de la classe : exécute **tous les tests du fichier**.
- Le résultat s'affiche dans le panneau **Test Results** en bas, avec un point vert ou rouge à côté de chaque méthode dans l'arborescence à gauche.

> [!TIP]
> Pendant le TP, gardez le réflexe **CodeLens pour la boucle TDD** (un test à la fois, retour en moins d'une seconde) et `./mvnw test` (depuis le terminal) **avant chaque commit** pour relancer toute la suite et confirmer qu'aucun test antérieur n'a régressé.

---

## Exercice 1 - HelloWorld (★)

### Objectif

Premier contact avec le cycle TDD. Aucune algorithmique, aucun piège : uniquement l'apprentissage du **rythme** RED → GREEN → REFACTOR, et des trois stratégies de Kent Beck pour écrire juste le code nécessaire.

### Ce que vous allez découvrir

- Le cycle **RED → GREEN → REFACTOR** : on écrit (ou on active) un test, il doit d'abord être **rouge** (preuve qu'il teste réellement quelque chose), puis on écrit le **minimum** de code pour le rendre **vert**, enfin on **refactore** sans casser le vert.
- La stratégie **Fake it** : retourner une valeur en dur (`return "Hello, World!";`) pour passer le premier test. C'est moche, c'est volontaire - on généralise seulement quand un deuxième test nous y force.
- La stratégie **Triangulation** : dès qu'un deuxième test échoue avec la même valeur fake, on introduit le minimum de logique (un `if`) pour différencier les deux cas.
- La stratégie **Obvious** : quand l'implémentation est évidente et tient en une ligne, on l'écrit directement.

### Classe fournie

`fr.univ_amu.iut.exercice1.HelloWorld` : une méthode statique `saluer(String nom)` vide (à implémenter).

### Travail à faire

1. Ouvrez `HelloWorldTest.java`. Les 4 tests sont désactivés par `@Disabled`.
2. **Activez le premier test** (`saluer_sans_nom_retourne_hello_world`) en retirant son `@Disabled`. Lancez-le via le lien **Run Test** qui apparaît juste au-dessus de la méthode (CodeLens). Il doit être **rouge**.
3. Écrivez dans `HelloWorld.saluer()` le **fake-it** : `return "Hello, World!";`. Relancez le même test depuis le CodeLens : vert.
4. Activez le deuxième test (chaîne vide). Il est déjà vert sans rien changer ? Normal : la chaîne vide rentre dans le cas `null or empty`. Activez le troisième test (`saluer_alice_retourne_hello_alice`) : rouge.
5. **Triangulez** : introduisez un `if` pour gérer le cas où `nom` n'est ni `null` ni vide.
6. Activez le quatrième test (`saluer_bob_retourne_hello_bob`) : il doit déjà passer sans nouvelle modification (c'est ça, la triangulation qui généralise).
7. Quand tous les tests sont verts, committez et ouvrez une Pull Request.

> [!TIP]
> **Gardez le réflexe du rouge d'abord** : beaucoup d'étudiants écrivent le code avant d'activer le test ; ça semble plus rapide mais ça casse le principe du TDD. Un test qui n'a jamais été rouge ne prouve pas qu'il teste quelque chose.

---

## Exercice 2 - FizzBuzz (★)

### Objectif

Classique des entretiens d'embauche, FizzBuzz est l'exercice idéal pour pratiquer la triangulation sur plusieurs règles imbriquées.

### Ce que vous allez découvrir

- Comment **faire évoluer une méthode en plusieurs étapes** (d'abord `"1"` en dur, puis un `if n==1 return "1"; else return "2";`, puis un modulo pour Fizz, puis pour Buzz, puis combiner pour FizzBuzz).
- Comment **extraire une méthode de façon incrémentale** : la signature `fizzBuzzJusquA(int n)` réutilise `fizzBuzz(int n)` plutôt que de dupliquer la logique. C'est la réalisation concrète d'un **refactoring Extract Method** (TP4 anticipé).

### Règles

Pour chaque entier `n` :

- multiple de 3 **et** de 5 → `"FizzBuzz"`
- multiple de 3 seulement → `"Fizz"`
- multiple de 5 seulement → `"Buzz"`
- sinon → la représentation textuelle de `n` (ex. `"7"`)

### Travail à faire

1. Activez les 9 premiers tests **dans l'ordre** (1, 2, 3, 4, 5, 6, 9, 10, 15). À chaque activation, faites d'abord rougir puis verdir.
2. Les 3 derniers tests portent sur `fizzBuzzJusquA(n)` qui doit renvoyer un tableau de la séquence complète. Implémentez-la en réutilisant `fizzBuzz(int)` (pas de duplication !).

---

## Exercice 3 - Convertisseur de chiffres romains (★★)

### Objectif

Passage de la triangulation à la conception émergente : le premier `if` pour `I` devient vite illisible ; quand arrive `V`, il est temps de refactorer en `switch`. Puis quand arrivent les soustractions (`IV`, `IX`), on introduit une méthode privée pour valider la combinaison.

### Ce que vous allez découvrir

- L'**extraction progressive de méthodes privées** (`valeurDe(char)`, `verifierSoustractionValide(...)`) quand le corps principal de `enNombreArabe()` devient trop dense.
- La **gestion structurée des exceptions** : `IllegalArgumentException` pour signaler une entrée invalide, avec assertions AssertJ qui vérifient le type et le message.
- Une **progression de tests très structurée** : d'abord les chiffres simples, puis les compositions additives, puis les soustractions, puis les cas d'erreur. Cet ordre est lui-même une spécification lisible de ce que fait (et ne fait pas) la classe.

### Rappel des règles des chiffres romains

- Symboles : `I=1, V=5, X=10, L=50, C=100, D=500, M=1000`
- Lus de gauche à droite, les symboles s'**additionnent** (`VI = 5 + 1 = 6`)
- Un symbole de petite valeur **avant** un symbole plus grand **se soustrait** (`IV = 5 - 1 = 4`)
- **Soustractions valides uniquement** : `I` avant `V`/`X` ; `X` avant `L`/`C` ; `C` avant `D`/`M`. Toute autre combinaison (`IL`, `IC`, `VX`, ...) est invalide et doit lever `IllegalArgumentException`.

### Travail à faire

1. Activez les tests des chiffres simples un par un (`I`, `II`, `III`, `V`, `VI`, `X`, ...). Laissez la triangulation vous guider jusqu'à un `switch` sur le caractère.
2. Passez aux soustractions (`IV`, `IX`, `XL`, ...) : vous devez introduire une logique qui compare le symbole courant au suivant.
3. Activez le test du grand nombre `MMMMDCCCLXXXVIII → 4888` : c'est un stress test, il doit passer sans ajout de logique si votre algorithme est correct.
4. Activez enfin les 8 tests d'exception. Ajoutez la validation des soustractions invalides.

> [!IMPORTANT]
> Un algorithme classique pour ce problème est le **parcours de droite à gauche** : en lisant la chaîne à l'envers, un symbole plus petit que le précédent doit se soustraire. C'est une piste, pas une obligation.

---

## Exercice 4 - Robot et simulateur (★★★)

### Objectif

Premier exercice qui mêle **plusieurs classes qui collaborent** : un `Robot` (état), un `SimulateurDeRobot` (comportement orchestrant une séquence de commandes), deux `enum` (`Orientation`, `Commande` implicite via les caractères `R`, `L`, `A`) et un `record` (`Position`).

### Ce que vous allez découvrir

- Comment **tester une classe mutable** : chaque test crée un nouveau robot pour éviter les dépendances entre tests (principe d'**indépendance** des tests).
- L'utilisation d'un **`record`** Java pour une valeur immuable (`Position`) : `equals`, `hashCode`, `toString` sont générés automatiquement, ce qui rend les assertions triviales.
- L'**ordinal sur une enum** pour implémenter les rotations de façon élégante (les valeurs de `Orientation` sont déjà dans l'ordre horaire, une rotation à droite = `ordinal + 1 mod 4`).

### Classes fournies

- `Position(int x, int y)` : un `record` immuable.
- `Orientation` : énumération `NORD, EST, SUD, OUEST` dans le sens horaire.
- `Robot` : une position + une orientation, mutables. Méthodes à implémenter : `tournerADroite()`, `tournerAGauche()`, `avancer()`.
- `SimulateurDeRobot` : reçoit un `Robot` à son instanciation. Méthode à implémenter : `executer(String commandes)` qui applique chaque caractère de la chaîne.

### Commandes

- `R` - tourner à droite
- `L` - tourner à gauche
- `A` - avancer d'une case dans l'orientation courante
- tout autre caractère lève `IllegalArgumentException`

### Exemple

Depuis la position `(7, 3)` orienté `NORD`, la séquence `"RAALAL"` amène le robot en `(9, 4)` orienté `OUEST`.

### Travail à faire

1. Activez les 2 tests de création (mémorisation position et orientation), puis les 5 tests de `tournerADroite`, les 4 de `tournerAGauche`, les 5 d'`avancer`. Travaillez en TDD strict.
2. Activez les 3 tests du `SimulateurDeRobot` (séquence complète, séquence vide, commande inconnue).

---

## Exercice 5 - Grille du démineur (★★★★)

### Objectif

Algorithme sur tableau 2D irrégulier avec gestion des bords. Sert aussi d'introduction à **ApprovalTests** pour les cas où écrire la sortie attendue à la main est pénible et source d'erreurs.

### Ce que vous allez découvrir

- Le **parcours 2D avec 8 voisins** et les **bornes** (les cases au bord de la grille ont moins de voisins).
- La **séparation validation / calcul** : le constructeur valide l'entrée (`null`, symboles, lignes de tailles différentes), la méthode `getRepresentationAnnotee` calcule.
- **ApprovalTests** pour les sorties textuelles : plutôt que d'écrire `assertEquals(Arrays.asList(...), ...)` avec la grille attendue codée en dur dans le test, on sauvegarde la sortie dans un fichier `.approved.txt` que l'on relit une fois à l'œil et qu'on valide. Cela change complètement l'écriture des tests sur des sorties riches (grilles, tableaux, rapports, logs).

### Règles

- Entrée : `List<String>` où chaque `String` représente une ligne de la grille, composée uniquement de `' '` (case vide) et `'*'` (mine).
- Sortie : même forme, mais chaque case vide est remplacée par le **nombre de mines dans ses 8 voisines** (ou un espace si ce nombre est 0). Les mines restent des `*`.

Exemple :

```
entrée :         sortie :
  ' * * '          '1*3*1'
  '  *  '    →     '13*31'
  '  *  '          ' 2*2 '
  '     '          ' 111 '
```

### Travail à faire

1. Activez les tests dans l'ordre : d'abord les cas dégénérés (grille vide, sans mine, pleine de mines), puis les cas canoniques (mine au centre, croix, pourtour), puis les tests de validation d'entrée (null, symbole inconnu, lignes de tailles différentes).
2. Activez enfin le test ApprovalTests `grandeGrilleAnnotee`. La première exécution crée un fichier `GrilleDemineurTest.grandeGrilleAnnotee.received.txt` et échoue avec un diff. Ouvrez le `.received.txt`, **vérifiez visuellement** que la sortie est correcte, puis **renommez** le fichier en `.approved.txt` pour figer le comportement. Relancez le test, il doit passer.

> [!TIP]
> ApprovalTests brille particulièrement pour les objets complexes (JSON, XML, rapports, images) où écrire l'attendu à la main est pénible. Vous pouvez l'utiliser dans n'importe quel projet Maven en ajoutant la dépendance `com.approvaltests:approvaltests`.

---

## Bonus - StringCalculator (★★★★, kata de Roy Osherove)

> [!NOTE]
> **Hors barème.** Ce bonus n'est pas comptabilisé dans l'autograding (le paquet `bonus6/` est volontairement ignoré par le système de notation). Vous le faites pour vous, pour le plaisir de la discipline TDD.

### Objectif

Kata classique de la communauté TDD qui pousse les baby steps dans leurs retranchements : chaque nouveau test ajoute une règle incrémentale au parser. Il faut **résister à la tentation de coder en avance de phase**.

### Règles (à activer progressivement)

1. `add("")` → `0`
2. `add("42")` → `42`
3. `add("1,2")` → `3`
4. `add("1,2,3,4,5")` → `15` (nombre arbitraire de valeurs)
5. `add("1\n2,3")` → `6` (saut de ligne accepté comme séparateur)
6. `add("//;\n1;2;3")` → `6` (délimiteur personnalisé défini par un préfixe `//X\n`)
7. `add("-1,2")` → `IllegalArgumentException` dont le message contient `-1`
8. `add("2,-1,3,-4")` → `IllegalArgumentException` dont le message liste `-1` **et** `-4`

### Travail à faire

Activez les tests un par un **et gardez la discipline TDD stricte** : pas de ligne de code en avance sur le test. C'est un exercice d'auto-discipline - c'est aussi pour ça qu'il est rangé en bonus.

---

## Ressources complémentaires

- [JUnit 6 User Guide](https://docs.junit.org/current/user-guide/)
- [AssertJ Core Documentation](https://assertj.github.io/doc/)
- [ApprovalTests Java](https://github.com/approvals/ApprovalTests.Java)
- [Mockito Documentation](https://site.mockito.org)
- [Refactoring (Martin Fowler)](https://refactoring.com)

---

*IUT d'Aix-Marseille - Département Informatique - 2026*
