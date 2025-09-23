
# Setup & Utilisation

##  Prérequis
Avant de démarrer, assurez-vous d’avoir installé :
- **Java 17 ou version supérieur+** → vérifier avec :
  ```bash
  java -version
  ```  
- **Maven+** → vérifier avec :
  ```bash
  mvn -v
  ```  
- **Docker & Docker Compose** 

##  Installation
1. Cloner le projet et accéder au dossier backend :
   ```bash
   git clone https://github.com/MalickLSy/altenshop_productrial
   cd altenshop_productrial/back
   chmod +x setup.sh
   ```

2. Exécuter le script de setup :
   ```bash
   ./setup.sh
   ```

3. L’application démarre sur :  
   [http://localhost:8083] (http://localhost:8083)

---

##  Tests avec Postman

### 1. Créer un compte administrateur
**POST** `http://localhost:8083/account`
```json
{
  "username": "admin",
  "firstname": "Admin",
  "email": "admin@admin.com",
  "password": "admin2025"
}
```

### 2. Générer un token JWT
**POST** `http://localhost:8083/token`
```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

**Réponse attendue :**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
```

Ajouter ce token dans l’en-tête de toutes les requêtes protégées :
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIs...
```

### 3. Vérifier l’utilisateur connecté
**GET** `http://localhost:8083/user/me`

---

##  Gestion des Produits

Seul l’utilisateur **admin@admin.com** peut créer, modifier ou supprimer des produits.

### Ajouter un produit
**POST** `http://localhost:8083/products`
```json
{
  "code": "P100",
  "name": "Ordinateur portable",
  "description": "PC portable 15 pouces, 16Go RAM, SSD 512Go",
  "image": "laptop.png",
  "category": "Informatique",
  "price": 899.99,
  "quantity": 5,
  "internalReference": "REF100",
  "shellId": 10,
  "inventoryStatus": "INSTOCK",
  "rating": 4.8
}
```

---

##  Gestion du Panier

### Ajouter un produit au panier
**POST** `http://localhost:8083/cart/1`

---

##  Gestion de la Wishlist

### Ajouter un produit à la wishlist
**POST** `http://localhost:8083/wishlist/2`

---  ---------------------------------------

### Cahier de charge

## Back-end    -  Dev Back end

### Partie 1

Développer un back-end permettant la gestion de produits définis plus bas.
Vous pouvez utiliser la technologie de votre choix parmi la liste suivante :


- Java/Spring Boot 


Un produit a les caractéristiques suivantes : 

``` typescript
class Product {
  id: number;
  code: string;
  name: string;
  description: string;
  image: string;
  category: string;
  price: number;
  quantity: number;
  internalReference: string;
  shellId: number;
  inventoryStatus: "INSTOCK" | "LOWSTOCK" | "OUTOFSTOCK";
  rating: number;
  createdAt: number;
  updatedAt: number;
}
```

Le back-end créé doit pouvoir gérer les produits dans une base de données SQL/NoSQL ou dans un fichier json.

### Partie 2

- Imposer à l'utilisateur de se connecter pour accéder à l'API.
  La connexion doit être gérée en utilisant un token JWT.  
  Deux routes devront être créées :
  * [POST] /account -> Permet de créer un nouveau compte pour un utilisateur avec les informations fournies par la requête.   
    Payload attendu : 
    ```
    {
      username: string,
      firstname: string,
      email: string,
      password: string
    }
    ```
  * [POST] /token -> Permet de se connecter à l'application.  
    Payload attendu :  
    ```
    {
      email: string,
      password: string
    }
    ```
  Une vérification devra être effectuée parmi tout les utilisateurs de l'application afin de connecter celui qui correspond aux infos fournies. Un token JWT sera renvoyé en retour de la reqûete.
- Faire en sorte que seul l'utilisateur ayant le mail "admin@admin.com" puisse ajouter, modifier ou supprimer des produits. Une solution simple et générique devra être utilisée. Il n'est pas nécessaire de mettre en place une gestion des accès basée sur les rôles.
- Ajouter la possibilité pour un utilisateur de gérer un panier d'achat pouvant contenir des produits.
- Ajouter la possibilité pour un utilisateur de gérer une liste d'envie pouvant contenir des produits.

## Bonus

Vous pouvez ajouter des tests Postman ou Swagger pour valider votre API