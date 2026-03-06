# English Version:
# Gym Planner

[![Java Version](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Database](https://img.shields.io/badge/Database-MS%20SQL%20Server-red.svg)](https://www.microsoft.com/en-us/sql-server/)

**Gym Planner** is a modern web application built with **Spring Boot**, designed for users who want to rigorously organize their fitness routine. The application allows for workout planning, real-time tracking of exercises, and maintaining a detailed history of progress.

---

## Project Description
The primary goal of this application is to digitize the gym experience, eliminating the need for physical notebooks or generic note-taking apps.

**Key Features:**
* **Exercise Management**: Add and organize exercises by category and target muscle groups.
* **Weekly Planner**: Create a personalized workout schedule for each day of the week.
* **Live Workout**: An active interface to record sets and repetitions in real-time while you train.
* **Full History**: Track your evolution through the automatic saving of completed sessions.

---

## Technologies Used
* **Language:** Java 21
* **Primary Framework:** Spring Boot (Spring MVC, Spring Data JPA)
* **Template Engine:** Thymeleaf (for the web interface)
* **Database:** Microsoft SQL Server
* **ORM:** Hibernate
* **Build Tool:** Maven

---

## System Architecture
The project follows the **Layered MVC** (Model-View-Controller) architecture:



```text
User ↔ Controller ↔ Service ↔ Repository ↔ Database (SQL Server)
```
1. Controller: Handles HTTP requests and acts as the bridge to the interface.

2.Service: Contains the business logic and application calculations.

3.Repository: Manages database interaction via Spring Data JPA.

4.Model: Defines the data structure (Entities).

gym-planner/
├── src/main/java/com/gymplanner/

│   ├── controller/      # Application endpoints

│   ├── service/         # Business logic
│   ├── repository/      # Database interfaces
│   ├── model/           # Entities (User, Exercise, Workout, etc.)
│   └── GymPlannerApplication.java
├── src/main/resources/
│   ├── templates/       # HTML pages (Thymeleaf)
│   ├── static/          # Static files (CSS, JavaScript, Images)
│   └── application.properties
└── pom.xml              # Maven dependencies

## Data Model
The application is based on the following main entities:

* **User:** Account management and authentication.

* **Exercise & TemplateExercise:** Definition of exercises (name, description, target muscles).

* **WeeklySchedule:** Links a specific day of the week to a set of exercises.

* **WorkoutHistory & WorkoutHistorySet:** Saves the weight and repetitions for every set performed during a session.

# Romanina Version:
# Gym Planner

[![Java Version](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Database](https://img.shields.io/badge/Database-MS%20SQL%20Server-red.svg)](https://www.microsoft.com/en-us/sql-server/)

**Gym Planner** este o aplicație web modernă dezvoltată cu **Spring Boot**, concepută pentru utilizatorii care doresc să își organizeze riguros rutina de fitness. Aplicația permite planificarea antrenamentelor, monitorizarea execuției în timp real și păstrarea unui istoric detaliat al progresului.

---

## Descriere Proiect
Scopul principal al aplicației este de a digitaliza experiența de antrenament la sală, eliminând nevoia de carnețele sau aplicații de note generice. 

**Funcționalități cheie:**
* **Gestiune Exerciții**: Adăugarea și organizarea exercițiilor pe categorii.
* **Planificator Săptămânal**: Crearea unui program personalizat pentru fiecare zi a săptămânii.
* **Antrenament Live**: Interfață activă pentru înregistrarea seturilor și repetărilor în timp ce te antrenezi.
* **Istoric Complet**: Monitorizarea evoluției prin salvarea automată a sesiunilor finalizate.

---

## Tehnologii Folosite
* **Limbaj:** Java 21
* **Framework Principal:** Spring Boot (Spring MVC, Spring Data JPA)
* **Motor de Template:** Thymeleaf (pentru interfața web)
* **Bază de Date:** Microsoft SQL Server
* **ORM:** Hibernate
* **Build Tool:** Maven

---

## Arhitectura Sistemului
Proiectul respectă arhitectura **Layered MVC** (Model-View-Controller):

```text
Utilizator ↔ Controller ↔ Service ↔ Repository ↔ Bază de Date (SQL Server)
```
1.Controller: Gestionează request-urile HTTP și face legătura cu interfața.

2.Service: Conține logica de business și calculele aplicației.

3.Repository: Gestionează interacțiunea cu baza de date prin Spring Data JPA.

4.Model: Definește structura datelor (Entitățile).

gym-planner/
├── src/main/java/com/gymplanner/
│   ├── controller/      # Endpoint-urile aplicației
│   ├── service/         # Logica de business
│   ├── repository/      # Interfețele pentru baza de date
│   ├── model/           # Entitățile (User, Exercise, Workout, etc.)
│   └── GymPlannerApplication.java
├── src/main/resources/
│   ├── templates/       # Pagini HTML (Thymeleaf)
│   ├── static/          # Fișiere CSS, JavaScript, Imagini
│   └── application.properties
└── pom.xml              # Dependențele Maven
## Modelul de Date
Aplicația se bazează pe următoarele entități principale:

* **User:** Gestionarea conturilor.

* **Exercise & TemplateExercise:** Definirea exercițiilor (nume, descriere, mușchi vizați).

* **WeeklySchedule:** Legătura dintre o zi a săptămânii și un set de exerciții.

* **WorkoutHistory & WorkoutHistorySet:** Salvează greutatea și repetările pentru fiecare set efectuat.
