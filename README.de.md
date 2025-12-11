Here's the German version of the README file:

```markdown
# Dimata - Mitarbeiterverwaltungssystem

Dimata ist eine auf **Spring Boot** basierende REST-API zur Verwaltung von Mitarbeiterdaten mit Passwortverschlüsselung unter Verwendung des **Argon2**-Algorithmus.

## ✨ Hauptfunktionen

- ✅ Erstellen neuer Mitarbeiterdaten
- ✅ Abrufen der Liste aller Mitarbeiter
- ✅ Suchen von Mitarbeitern nach ID
- ✅ Aktualisieren von Mitarbeiterdaten
- ✅ Löschen von Mitarbeitern
- ✅ Passwortverschlüsselung mit Argon2 (sicher)

## 🛠️ Technologie-Stack

| Komponente | Version |
|------------|---------|
| Java | 25 |
| Spring Boot | 4.0.0 |
| Datenbank | MySQL |
| ORM | JPA/Hibernate |
| Build-Tool | Maven |
| Sicherheit | Spring Security Crypto (Argon2) |

## 📋 Voraussetzungen

Bevor Sie das Projekt ausführen, stellen Sie sicher, dass Folgendes installiert ist:

- ☕ Java Development Kit (JDK) 25+
- 🗄️ MySQL Server (läuft auf localhost:3306)
- 📦 Maven 3.6+ (oder verwenden Sie den bereitgestellten Maven Wrapper)

## 🚀 Installations- und Ausführungsanleitung

### 1. Repository klonen
```bash
git clone <repository-url>
cd dimata
```

### 2. MySQL-Datenbank einrichten
Erstellen Sie eine neue Datenbank in MySQL:
```sql
CREATE DATABASE argon_employee;
```

### 3. Datenbankkonfiguration
Bearbeiten Sie die Datei [src/main/resources/application.properties](src/main/resources/application.properties):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/argon_employee?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=MyNewPassword123!
```

> **Hinweis:** Passen Sie `username` und [password](file:///home/panjipalguna/Documents/dimata/src/main/java/com/employee/dimata/entity/Employee.java#L22-L23) entsprechend Ihrer MySQL-Konfiguration an.

### 4. Anwendung ausführen

#### Mit Maven Wrapper (Linux/Mac):
```bash
./mvnw spring-boot:run
```

#### Mit Maven Wrapper (Windows):
```bash
mvnw.cmd spring-boot:run
```

#### Mit Maven (wenn bereits installiert):
```bash
mvn spring-boot:run
```

Die Anwendung läuft unter: **http://localhost:8080**

## 📡 REST-API-Endpunkte

### 1️⃣ Neuen Mitarbeiter erstellen
**Anfrage:**
```http
POST /employees
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "SecurePassword123!"
}
```

**Antwort (201 Created):**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "password": "$argon2id$v=19$m=65536,t=3,p=1$..."
}
```

---

### 2️⃣ Alle Mitarbeiter abrufen
**Anfrage:**
```http
GET /employees
```

**Antwort (200 OK):**
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "password": "$argon2id$..."
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "email": "jane@example.com",
    "password": "$argon2id$..."
  }
]
```

---

### 3️⃣ Mitarbeiter nach ID abrufen
**Anfrage:**
```http
GET /employees/1
```

**Antwort (200 OK):**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "password": "$argon2id$..."
}
```

---

### 4️⃣ Mitarbeiterdaten aktualisieren
**Anfrage:**
```http
PUT /employees/1
Content-Type: application/json

{
  "name": "John Updated",
  "email": "john.updated@example.com",
  "password": "NewSecurePassword123!"
}
```

**Antwort (200 OK):**
```json
{
  "id": 1,
  "name": "John Updated",
  "email": "john.updated@example.com",
  "password": "$argon2id$v=19$m=65536,t=3,p=1$..."
}
```

---

### 5️⃣ Mitarbeiter löschen
**Anfrage:**
```http
DELETE /employees/1
```

**Antwort (200 OK):**
```json
"Deleted"
```

---

## 🔒 Passwortsicherheit

Passwörter werden sicher mit **Argon2PasswordEncoder** gespeichert mit folgender Konfiguration:

| Parameter | Wert | Beschreibung |
|-----------|------|--------------|
| Salt-Länge | 16 Bytes | Länge des zufälligen Salts |
| Hash-Länge | 32 Bytes | Länge des Hash-Ergebnisses |
| Parallelität | 1 | Anzahl paralleler Threads |
| Speicher | 65536 KB | Verwendete Speichergröße |
| Iterationen | 3 | Anzahl der Hashing-Iterationen |

Die Konfiguration kann in der Datei [src/main/java/com/employee/dimata/config/SecurityConfig.java](src/main/java/com/employee/dimata/config/SecurityConfig.java) eingesehen werden.

## 📂 Projektstruktur

```
dimata/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── employee/
│   │   │           └── dimata/
│   │   │               ├── DimataApplication.java
│   │   │               ├── config/
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── controller/
│   │   │               │   └── EmployeeController.java
│   │   │               ├── model/
│   │   │               │   └── Employee.java
│   │   │               ├── repository/
│   │   │               │   └── EmployeeRepository.java
│   │   │               └── service/
│   │   │                   └── EmployeeService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/
│       └── java/
│           └── com/
│               └── employee/
│                   └── dimata/
│                       └── DimataApplicationTests.java
└── pom.xml
```

## 📄 Lizenz

MIT-Lizenz. Weitere Informationen finden Sie in der Datei [LICENSE](LICENSE).
```