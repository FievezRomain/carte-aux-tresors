# Carte aux trésors

## Description

Le projet **Carte aux trésors** est une application Java qui simule une carte où des aventuriers peuvent se déplacer, collecter des trésors et éviter des obstacles comme des montagnes. Cette application utilise Maven pour la gestion des dépendances et la construction du projet.

## Fonctionnalités

- **Gestion des aventuriers** : Les aventuriers peuvent se déplacer sur la carte, collecter des trésors et changer d'orientation.
- **Gestion des trésors** : Les trésors peuvent être ramassés par les aventuriers, avec des règles spécifiques pour les collecter.
- **Obstacles** : La carte contient des montagnes qui bloquent le chemin des aventuriers.
- **Journalisation** : Utilisation de Log4j pour la journalisation des événements dans l'application.

## Technologies utilisées

- Java 11
- Maven
- Log4j
- JUnit (pour les tests)

## Installation

Pour cloner le projet et le faire fonctionner sur votre machine :

1. Cloner le dépôt :

   ```bash
   git clone https://github.com/FievezRomain/carte-aux-tresors.git
   
2. Naviguer dans le répertoire du projet :
   ```bash
    cd carte-aux-tresors

4. Compiler le projet avec Maven :
   ```bash
   mvn clean package

6. Pour exécuter l'application, utilisez la commande suivante :
   ```bash
   java -jar target/carbon-1.0-SNAPSHOT.jar  input.txt output.txt

8. Les tests peuvent être exécutés avec la commande suivante :
   ```bash
   mvn test
