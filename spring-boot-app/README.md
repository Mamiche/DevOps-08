# Application Web Java basée sur Spring Boot
 
Ceci est une simple application Java basée sur Spring Boot, qui peut être construite avec Maven.
Les dépendances Spring Boot sont gérées dans le fichier pom.xml situé à la racine du dépôt.

C’est une application utilisant l’architecture MVC où le contrôleur renvoie une page avec un titre et un message vers la vue.

## Exécuter l’application localement et y accéder depuis votre navigateur

Cloner le dépôt et se déplacer dans le répertoire du projet :

```
git clone https://github.com/Mamiche/DevOps-08.git
cd DevOps-08/spring-boot-app
```

Exécuter les cibles Maven pour générer les artefacts :

```
mvn clean package
```

Les artefacts générés seront placés dans le répertoire target.
Vous pouvez soit exécuter l’artefact sur votre machine locale,
soit le lancer dans un conteneur Docker.

⚠️ Remarque : Pour éviter les problèmes liés à la configuration locale, aux versions de Java et aux autres dépendances, il est recommandé d’utiliser Docker.


### Exécution locale (Java 11 requis) 
Accéder ensuite à l’application via http://localhost:8080
```
java -jar target/spring-boot-web.jar
```

### La méthode Docker

Construire l’image Docker :

```
docker build -t devops-08:v1 .
```

```
docker run -d -p 8010:8080 -t devops-08:v1
```

🎉 L’application est accessible sur `http://<ip-address>:8010` 


