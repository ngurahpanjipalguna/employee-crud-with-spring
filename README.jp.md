以下は [README.md](file:///home/panjipalguna/Documents/dimata/README.md) の日本語版です：

```markdown
# Dimata - 従業員管理システム

Dimataは、**Spring Boot** ベースのREST APIで、**Argon2**アルゴリズムを使用してパスワードを暗号化し、従業員データを管理します。

## ✨ 主な機能

- ✅ 新しい従業員データの作成
- ✅ すべての従業員リストの取得
- ✅ IDによる従業員検索
- ✅ 従業員データの更新
- ✅ 従業員の削除
- ✅ Argon2によるパスワード暗号化（安全）

## 🛠️ 技術スタック

| コンポーネント | バージョン |
|---------------|------------|
| Java          | 25         |
| Spring Boot   | 4.0.0      |
| データベース   | MySQL      |
| ORM           | JPA/Hibernate |
| ビルドツール   | Maven      |
| セキュリティ   | Spring Security Crypto (Argon2) |

## 📋 前提条件

プロジェクトを実行する前に、以下のものがインストールされていることを確認してください：

- ☕ Java Development Kit (JDK) 25+
- 🗄️ MySQLサーバー（localhost:3306で動作）
- 📦 Maven 3.6+（または同梱されているMaven Wrapperを使用）

## 🚀 インストールと実行ガイド

### 1. リポジトリのクローン
```bash
git clone <repository-url>
cd dimata
```

### 2. MySQLデータベースのセットアップ
MySQLで新しいデータベースを作成：
```sql
CREATE DATABASE argon_employee;
```

### 3. データベース設定
ファイル [src/main/resources/application.properties](src/main/resources/application.properties) を編集：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/argon_employee?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=MyNewPassword123!
```

> **注意:** `username` と `password` はあなたのMySQL設定に合わせて変更してください。

### 4. アプリケーションの実行

#### Maven Wrapperを使用（Linux/Mac）:
```bash
./mvnw spring-boot:run
```

#### Maven Wrapperを使用（Windows）:
```bash
mvnw.cmd spring-boot:run
```

#### Mavenを使用（すでにインストール済みの場合）:
```bash
mvn spring-boot:run
```

アプリケーションは **http://localhost:8080** で動作します。

## 📡 REST APIエンドポイント

### 1️⃣ 新しい従業員の作成
**リクエスト:**
```http
POST /employees
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "SecurePassword123!"
}
```

**レスポンス (201 Created):**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "password": "$argon2id$v=19$m=65536,t=3,p=1$..."
}
```

---

### 2️⃣ すべての従業員の取得
**リクエスト:**
```http
GET /employees
```

**レスポンス (200 OK):**
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

### 3️⃣ IDによる従業員の取得
**リクエスト:**
```http
GET /employees/1
```

**レスポンス (200 OK):**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "password": "$argon2id$..."
}
```

---

### 4️⃣ 従業員データの更新
**リクエスト:**
```http
PUT /employees/1
Content-Type: application/json

{
  "name": "John Updated",
  "email": "john.updated@example.com",
  "password": "NewSecurePassword123!"
}
```

**レスポンス (200 OK):**
```json
{
  "id": 1,
  "name": "John Updated",
  "email": "john.updated@example.com",
  "password": "$argon2id$v=19$m=65536,t=3,p=1$..."
}
```

---

### 5️⃣ 従業員の削除
**リクエスト:**
```http
DELETE /employees/1
```

**レスポンス (200 OK):**
```json
"Deleted"
```

---

## 🔒 パスワードセキュリティ

パスワードは以下の設定で **Argon2PasswordEncoder** を使用して安全に保存されます：

| パラメータ     | 値        | 説明                     |
|----------------|-----------|--------------------------|
| ソルト長       | 16バイト   | ランダムソルトの長さ     |
| ハッシュ長     | 32バイト   | ハッシュ結果の長さ       |
| 並列処理数     | 1         | 並列スレッド数           |
| メモリ         | 65536 KB  | 使用されるメモリサイズ   |
| 反復回数       | 3         | ハッシュ化の反復回数     |

設定は [src/main/java/com/employee/dimata/config/SecurityConfig.java](src/main/java/com/employee/dimata/config/SecurityConfig.java) で確認できます。

## 📂 プロジェクト構造

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

## 📄 ライセンス

MITライセンス。詳細は [LICENSE](LICENSE) ファイルをご確認ください。
```