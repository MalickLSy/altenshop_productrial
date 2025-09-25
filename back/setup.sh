#!/bin/bash


docker-compose down -v

echo "Lancement de la base de données Docker..."


docker-compose up -d

mvn -q -Dspring.profiles.active=test test

echo " Démarrage de l'application Spring Boot..."
mvn spring-boot:run
