# ![Formation GoLogic Example de Projet](Gologic.png)

## Pré-requis pour l'exemple de projet 

Nous sommes ravis d'explorer GitHub Copilot avec vous à travers des exemples pratiques. Pour assurer un bon déroulement, veuillez préparer votre poste de travail de la manière suivante :

- Installer Visual Studio Code : [https://code.visualstudio.com/download](https://code.visualstudio.com/download)

- Une fois que Visual Studio Code est installé sur votre système, installez le plugin SonarLint (Ouvrez VSCode, appuyez sur Ctrl+Shift+X, saisissez "SonarLint" dans la barre de recherche, puis cliquez sur "Install").

- Installer le SDK Java (Version 21) : [https://www.oracle.com/ca-en/java/technologies/downloads/#java21](https://www.oracle.com/ca-en/java/technologies/downloads/#java21)

- Installer Gradle pour construire et démarrer le projet : [https://gradle.org/install/](https://gradle.org/install/)

- (OPTIONNEL) Installer Postman pour faciliter les requêtes au backend : [https://www.postman.com/downloads/](https://www.postman.com/downloads/)


## Démarrer l'application

- Ouvrez VSCode et ouvrez une nouvelle fenêtre (Ctrl+Shift+N).

- Sur la page d'accueil, cliquez sur "Clone Git Repository...", entrez l'URL de ce dépôt [https://github.com/gologic-ca/Example-SpringBoot-Gradle-Java21-GHCopilot.git](https://github.com/gologic-ca/Example-SpringBoot-Gradle-Java21-GHCopilot.git)) et confirmez en cliquant sur "Clone from the URL". Cliquez ensuite sur "Open".

- Une fois le projet ouvert, ouvrez un nouveau terminal (Shift+Ctrl+\`). Exécuter la commande :
`./gradlew bootRun`

Félicitations, le projet devrait maintenant être en cours d'exécution sur `http://localhost:8080/`.
Ajoutez une requête à la fin de l'URL pour voir les résultats. Par exemple : `http://localhost:8080/tags`


## Fonctionnement

L'application utilise Spring Boot (Web, MyBatis).

Le code est organisé de la manière suivante :

1. `api` est la couche web implémentée par Spring MVC
2. `core` représente le modèle métier, comprenant les entités et les services
3. `application` fournit les services de haut niveau pour interroger les objets de transfert de données
4. `infrastructure` contient toutes les classes d'implémentation en tant que détails techniques

## Sécurité

Il y a une intégration avec Spring Security et l'ajout d'un autre filtre pour le traitement des jetons JWT.

La clé secrète est stockée dans `application.properties`.

## Base de données

On utilise une base de données SQLite (pour faciliter les tests locaux sans perdre les données de test après chaque redémarrage), qui peut être facilement modifiée dans `application.properties` pour toute autre base de données.

## [Source et documentation](https://github.com/gothinkster/realworld)

La base de code contient des exemples concrets (CRUD, authentification, modèles avancés, etc.) qui respectent la spécification et l'API [RealWorld](https://github.com/gothinkster/realworld-example-apps).

Cette base de code a été créée pour montrer une application full-stack complète construite avec Java Spring (avec une orientation fonctionnalité) incluant des opérations CRUD, de l'authentification, du routage, de la pagination, et plus encore.

Pour plus d'informations sur le fonctionnement avec d'autres frontends/backends, rendez-vous sur le dépôt [RealWorld](https://github.com/gothinkster/realworld).


