# API de Gestion d'Inventaire de Produits

Cette API permet de gérer un inventaire de produits avec des fonctionnalités CRUD (Créer, Lire, Mettre à jour, Supprimer). Elle est développée en Spring Boot et utilise PostgreSQL pour la base de données.
## Technologies utilisées

- **Java 17, 21, 22** (ou supérieur)
- **Maven** (pour la gestion des dépendances)
- **PostgreSQL** (installé et configuré localement)
- **Spring Boot** (pour la gestion de l'application)
- **Spring Data JPA** (pour l'accès aux données)
- **Lombok** (pour la gestion des annotations)
- **Swagger** (pour la documentation de l'API)

## Installation

1. **Cloner le dépôt** :
   ```bash
   git clone https://github.com/loickenmoe/API-Inventory-Management
   cd API-Inventory-Management

   2. **Configurer la base de données PostgreSQL :** :   
   -Créez une base de données nommée inventory_db :
        "CREATE DATABASE inventory_db;"
        -Vérifiez les identifiants dans application.properties :
        "spring.datasource.username=postgres
        spring.datasource.password=mot_de_passe
        spring.jpa.hibernate.ddl-auto=update"

   3. ** Construire et exécuter le projet :** :
   "mvn clean install"
     "mvn spring-boot:run"
   -L'API sera disponible sur http://localhost:9008.

## Utilisation de l'application :
            Endpoints
         
         Méthode	     Endpoint	                   Description
         GET	          /api/products/all                    Lister tous les produits 
         POST	          /api/products/create	               Ajouter un nouveau produit
         DELETE	          /api/products/delete?productId=1	           Supprimer un produit par ID
         PUT	          /api/products/update/{productId}	    Mettre à jour un produit par ID
         GET	          /api/products/low-stock	            Obtenir les produits en stock bas (qtéEnStock < 5)

## Lien Swagger :
   http://localhost:9008/swagger-ui/index.html

## Exemples de requêtes :
      -Créer un nouveau produit :
      POST http://localhost:9008/api/products/create
      Content-Type: application/json
      {
      "name": "Produit Test",
      "price": 25.99,
      "stockQuantity": 100
      }

      Reponse attendue:
      {
      "id": 5,
      "name": "Produit Test",
      "price": 25.99,
      "stockQuantity": 100
      }
   
      -Lister toutes les produits :
      GET http://localhost:9008/api/products/all
    [
       {
           "id": 2,
           "name": "Choux",
           "price": 300.0,
           "stockQuantity": 4
       },
       {
           "id": 1,
           "name": "pomme",
           "price": 200.0,
           "stockQuantity": 10
       },
       {
           "id": 4,
           "name": "Tomate",
           "price": 300.0,
           "stockQuantity": 5
       },
       {
           "id": 5,
           "name": "Produit Test",
           "price": 25.99,
           "stockQuantity": 100
       }
    ]


    -Mettre à jour un produit (ID=1) :
     PUT http://localhost:9008/api/products/update/1
     Content-Type: application/json
    {
    "name": "Riz",
    "price": 800,
    "stockQuantity": 13
    }

     Reponse attendue:
    {
    "id": 1,
    "name": "Riz",
    "price": 800.0,
    "stockQuantity": 13
    }

      -Supprimer une tâche (ID=4) :
      DELETE http://localhost:9008/api/products/delete/4
    


