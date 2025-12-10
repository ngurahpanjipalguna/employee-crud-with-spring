# Dimata - Employee Management System

Dimata adalah REST API berbasis **Spring Boot** untuk mengelola data karyawan dengan enkripsi password menggunakan algoritma **Argon2**.

## ✨ Fitur Utama

- ✅ Membuat data karyawan baru
- ✅ Mengambil daftar semua karyawan
- ✅ Mencari karyawan berdasarkan ID
- ✅ Memperbarui data karyawan
- ✅ Menghapus karyawan
- ✅ Enkripsi password dengan Argon2 (aman)

## 🛠️ Tech Stack

| Komponen | Versi |
|----------|-------|
| Java | 25 |
| Spring Boot | 4.0.0 |
| Database | MySQL |
| ORM | JPA/Hibernate |
| Build Tool | Maven |
| Security | Spring Security Crypto (Argon2) |

## 📋 Prerequisites

Sebelum menjalankan project, pastikan Anda memiliki:

- ☕ Java Development Kit (JDK) 25+
- 🗄️ MySQL Server (berjalan di localhost:3306)
- 📦 Maven 3.6+ (atau gunakan Maven Wrapper yang sudah disediakan)

## 🚀 Panduan Instalasi & Menjalankan

### 1. Clone Repository
```bash
git clone <repository-url>
cd dimata
```

### 2. Setup Database MySQL
Buat database baru di MySQL:
```sql
CREATE DATABASE argon_employee;
```

### 3. Konfigurasi Database
Edit file [src/main/resources/application.properties](src/main/resources/application.properties):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/argon_employee?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=MyNewPassword123!
```

> **Catatan:** Sesuaikan `username` dan `password` dengan konfigurasi MySQL Anda.

### 4. Menjalankan Aplikasi

#### Menggunakan Maven Wrapper (Linux/Mac):
```bash
./mvnw spring-boot:run
```

#### Menggunakan Maven Wrapper (Windows):
```bash
mvnw.cmd spring-boot:run
```

#### Menggunakan Maven (jika sudah terinstall):
```bash
mvn spring-boot:run
```

Aplikasi akan berjalan di: **http://localhost:8080**

## 📡 REST API Endpoints

### 1️⃣ Membuat Karyawan Baru
**Request:**
```http
POST /employees
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "SecurePassword123!"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "password": "$argon2id$v=19$m=65536,t=3,p=1$..."
}
```

---

### 2️⃣ Mengambil Semua Karyawan
**Request:**
```http
GET /employees
```

**Response (200 OK):**
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

### 3️⃣ Mengambil Karyawan Berdasarkan ID
**Request:**
```http
GET /employees/1
```

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "password": "$argon2id$..."
}
```

---

### 4️⃣ Memperbarui Data Karyawan
**Request:**
```http
PUT /employees/1
Content-Type: application/json

{
  "name": "John Updated",
  "email": "john.updated@example.com",
  "password": "NewSecurePassword123!"
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "John Updated",
  "email": "john.updated@example.com",
  "password": "$argon2id$v=19$m=65536,t=3,p=1$..."
}
```

---

### 5️⃣ Menghapus Karyawan
**Request:**
```http
DELETE /employees/1
```

**Response (200 OK):**
```json
"Deleted"
```

---

## 🔒 Keamanan Password

Password disimpan dengan aman menggunakan **Argon2PasswordEncoder** dengan konfigurasi:

| Parameter | Nilai | Keterangan |
|-----------|-------|-----------|
| Salt Length | 16 bytes | Panjang random salt |
| Hash Length | 32 bytes | Panjang hash result |
| Parallelism | 1 | Jumlah thread paralel |
| Memory | 65536 KB | Ukuran memory yang digunakan |
| Iterations | 3 | Jumlah iterasi hashing |

Konfigurasi dapat dilihat di file [src/main/java/com/employee/dimata/config/SecurityConfig.java](src/main/java/com/employee/dimata/config/SecurityConfig.java)

## 📂 Struktur Project

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

## 📄 License

MIT License. Lihat file [LICENSE](LICENSE) untuk informasi lebih lanjut.