# Pipeline Jenkins pour une application Java avec Maven, SonarQube, Argo CD, Helm et Kubernetes

![](https://user-images.githubusercontent.com/43399466/228301952-abc02ca2-9942-4a67-8293-f76647b6f9d8.png)

Voici les étapes détaillées pour mettre en place un **pipeline Jenkins de bout en bout pour une application Java utilisant SonarQube, Argo CD, Helm et Kubernetes :**

**Prérequis :**

- Code de l’application Java hébergé sur un dépôt Git
- Serveur Jenkins dans K8S
- Cluster Kubernetes ( MiniKube par exemple )
- Seveur Sonar dans k8s
- Gestionnaire de paquets Helm
- Serveur Argo CD dans k8s

**Étapes :**
1. Installer Minikube et les serveurs Jenkins :
   1.1 Start MiniKube (Minikube configure automatiquement kubectl pour pointer vers ton cluster local) 
   ```
   minikube start --driver=docker
   ```   
   1.2 Application du serveur Jenkins (k8s/jenkins.yaml)
   ```
   kubectl apply -f k8s/jenkins.yaml
   ```
   1.3 Application du serveur Sonar (k8s/sonar.yaml)
   ```
   kubectl apply -f k8s/sonar.yaml
   ```

   1.4 Application du serveur Argo CD
   ```
   kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/v2.5.8/manifests/install.yaml
   ```   
   
2. Installer les plugins Jenkins nécessaires :
   2.1 Plugin Git  
   2.2 Plugin Pipeline  
   2.3 Plugin Kubernetes Continuous Deploy  
   2.4 Configurer les differents credentials ( Sonar, git, DockerHub)

3. Créer un nouveau pipeline Jenkins :  
   3.1 Dans Jenkins, créer un nouveau job pipeline multibranche et le configurer avec l’URL du dépôt Git de l’application Java.  
   3.2 Ajouter un `Jenkinsfile` au dépôt Git pour définir les étapes du pipeline.  

4. Définir les étapes du pipeline :  
   - **Étape 1 :** Récupérer le code source depuis Git.  
   - **Étape 2 :** Construire l’application Java avec Maven.  
   - **Étape 3 :** Exécuter les tests unitaires avec JUnit et Mockito.  
   - **Étape 4 :** Lancer l’analyse SonarQube pour vérifier la qualité du code.  
   - **Étape 5 :** Générer un fichier JAR de l’application.  
   - **Étape 6 :** Déployer l’application dans DockerHub.  
   - **Étape 7 :** Promouvoir l’application en production avec Argo CD.  

5. Configurer les étapes du pipeline Jenkins :  
   - **Étape 1 :** Utiliser le plugin Git pour récupérer le code source.  
   - **Étape 2 :** Utiliser un agent kubernetes pour demarrer un pod avec maven et buildah 
   - **Étape 3 :** Utiliser les plugins JUnit et Mockito pour exécuter les tests unitaires.  
   - **Étape 4 :** Utiliser le serveur SonarQube pour analyser la qualité du code.  
   - **Étape 5 :** Utiliser le plugin Maven Integration pour packager l’application en JAR.  
   - **Étape 6 :** Utiliser le serveur Buildah deployer dans DockerHub
   - **Étape 7 :** Utiliser Argo CD pour déployer en production.  

6. Mettre en place Argo CD :  
   - Installer Argo CD sur le cluster Kubernetes.  
   - Configurer un dépôt Git pour qu’Argo CD suive les changements des charts Helm et des manifests Kubernetes.  
   - Créer un chart Helm pour l’application Java incluant les manifests Kubernetes et les valeurs Helm 
   - Ajouter le chart Helm dans le dépôt Git suivi par Argo CD.  
```
k8s/helm/
│── Chart.yaml          # Métadonnées du chart (nom, version, description, etc.)
│── values.yaml         # Valeurs par défaut pour les templates (paramètres configurables)
│
├── templates/          # Manifests Kubernetes qui seront générés
│   │── deployment.yaml       # Définition du déploiement Kubernetes
│   └── service.yaml          # Service exposant l’application
└── 
```
![Argo CD](https://imgur.com/lZC0TSJ.png)

7. Intégrer Jenkins avec Argo CD :  
   6.1 Ajouter le token API Argo CD dans les credentials Jenkins.  
   6.2 Mettre une étape dans pipeline Jenkins pour inclure l’étape l'update du tag de la charte helm pour declencher déploiement via Argo CD.  

8. Exécuter le pipeline Jenkins :  
   7.1 Lancer le pipeline Jenkins pour démarrer le processus CI/CD.  
   7.2 Surveiller les étapes et corriger les éventuels problèmes.  

---

✅ Ce pipeline Jenkins de bout en bout automatise l’intégralité du processus CI/CD pour une application Java, depuis la récupération du code jusqu’au déploiement en production, en utilisant des outils modernes tels que **SonarQube, Argo CD, Buildah, Helm et Kubernetes**.
