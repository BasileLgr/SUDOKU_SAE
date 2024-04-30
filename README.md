# Éditeur de Sudoku

Ce projet est un éditeur de Sudoku développé en Java, utilisant Swing pour l'interface graphique. Il permet de créer, charger, modifier, et sauvegarder des grilles de Sudoku.

## Fonctionnalités

- **Création de grille**: L'utilisateur peut créer une nouvelle grille de Sudoku vierge.
- **Chargement de grille**: L'utilisateur peut charger une grille depuis un fichier.
- **Modification de grille**: L'utilisateur peut entrer des chiffres dans la grille.
- **Sauvegarde de grille**: L'utilisateur peut sauvegarder la grille actuelle dans un fichier.

## Technologies utilisées

- Java
- Swing pour l'interface utilisateur

## Démarrage rapide

### Prérequis

- Java 11 ou supérieur

### Installation

Clonez ce dépôt sur votre machine locale en utilisant:

```bash
git clone https://github.com/votre-username/votre-repo.git
```

## Compilation et Exécution

Utilisez le Makefile fourni pour compiler et exécuter l'application :

```bash
make createur # Pour lancer l'application en mode éditeur
make joueur   # Pour lancer l'application en mode joueur (pas encore implémenté)
```


## Nettoyage

Pour nettoyer le répertoire de travail des fichiers de classe et autres fichiers générés :

```bash
make clean
```

## Auteurs

- Dimitri Solar
- Basile Legrelle