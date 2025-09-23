#!/bin/bash

echo "Build du projet avec Maven..."
mvn -DskipTests clean package

echo "Lancement de la base de données Docker..."
docker-compose up -d

echo " Démarrage de l'application Spring Boot..."
mvn spring-boot:run
