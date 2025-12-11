# Dimata - 员工管理系统

Dimata 是一个基于 **Spring Boot** 的 REST API，用于管理员工数据，密码使用 **Argon2** 算法加密。

## ✨ 主要功能

- ✅ 创建新员工数据
- ✅ 获取所有员工列表
- ✅ 根据ID搜索员工
- ✅ 更新员工数据
- ✅ 删除员工
- ✅ 使用 Argon2 加密密码（安全）

## 🛠️ 技术栈

| 组件 | 版本 |
|------|------|
| Java | 25 |
| Spring Boot | 4.0.0 |
| 数据库 | MySQL |
| ORM | JPA/Hibernate |
| 构建工具 | Maven |
| 安全 | Spring Security Crypto (Argon2) |

## 📋 先决条件

运行项目前，请确保您已安装：

- ☕ Java 开发工具包 (JDK) 25+
- 🗄️ MySQL 服务器（运行在 localhost:3306）
- 📦 Maven 3.6+（或使用已提供的 Maven Wrapper）

## 🚀 安装与运行指南

### 1. 克隆仓库
```bash
git clone <repository-url>
cd dimata
```

### 2. 设置 MySQL 数据库
在 MySQL 中创建新数据库：
```sql
CREATE DATABASE argon_employee;
```

### 3. 配置数据库
编辑文件 [src/main/resources/application.properties](src/main/resources/application.properties)：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/argon_employee?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=MyNewPassword123!
```

> **注意：** 请根据您的 MySQL 配置调整 `username` 和 `password`。

### 4. 运行应用程序

#### 使用 Maven Wrapper (Linux/Mac)：
```bash
./mvnw spring-boot:run
```

#### 使用 Maven Wrapper (Windows)：
```bash
mvnw.cmd spring-boot:run
```

#### 使用 Maven (如果已安装)：
```bash
mvn spring-boot:run
```

应用程序将在以下地址运行：**http://localhost:8080**

## 📡 REST API 端点

### 1️⃣ 创建新员工
**请求：**
```http
POST /employees
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "SecurePassword123!"
}
```

**响应 (201 Created)：**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "password": "$argon2id$v=19$m=65536,t=3,p=1$..."
}
```

---

### 2️⃣ 获取所有员工
**请求：**
```http
GET /employees
```

**响应 (200 OK)：**
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

### 3️⃣ 根据ID获取员工
**请求：**
```http
GET /employees/1
```

**响应 (200 OK)：**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "password": "$argon2id$..."
}
```

---

### 4️⃣ 更新员工数据
**请求：**
```http
PUT /employees/1
Content-Type: application/json

{
  "name": "John Updated",
  "email": "john.updated@example.com",
  "password": "NewSecurePassword123!"
}
```

**响应 (200 OK)：**
```json
{
  "id": 1,
  "name": "John Updated",
  "email": "john.updated@example.com",
  "password": "$argon2id$v=19$m=65536,t=3,p=1$..."
}
```

---

### 5️⃣ 删除员工
**请求：**
```http
DELETE /employees/1
```

**响应 (200 OK)：**
```json
"Deleted"
```

---

## 🔒 密码安全

密码使用 **Argon2PasswordEncoder** 安全存储，配置如下：

| 参数 | 值 | 说明 |
|------|----|------|
| Salt 长度 | 16 字节 | 随机盐长度 |
| Hash 长度 | 32 字节 | 哈希结果长度 |
| 并行度 | 1 | 并行线程数 |
| 内存 | 65536 KB | 使用的内存大小 |
| 迭代次数 | 3 | 哈希迭代次数 |

配置可在文件 [src/main/java/com/employee/dimata/config/SecurityConfig.java](src/main/java/com/employee/dimata/config/SecurityConfig.java) 中查看

## 📂 项目结构

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

## 📄 许可证

MIT 许可证。更多信息请查看 [LICENSE](LICENSE) 文件。