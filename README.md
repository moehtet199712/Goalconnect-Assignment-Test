# 認証機能付きWebアプリケーション（Spring Boot）

## ✅ プロジェクト概要
本プロジェクトは **Spring Boot** を使用して作成した、**ログイン・新規登録機能付きWebアプリケーション** です。

ユーザーは以下の操作が可能です。
- メールアドレスとパスワードで新規登録
- 登録後、ログイン
- ログイン後、ホーム画面を表示
- ログアウト

パスワードは **BCrypt** を使用して安全に暗号化しています。

---

## ✅ 使用技術
- Java 17
- Spring Boot
- Spring MVC
- MyBatis  
  ➡︎ Java アプリケーションと Database（MySQL / PostgreSQL 等）を接続するフレームワーク
- Thymeleaf  
  ➡︎ HTML と Java のデータを組み合わせて表示するテンプレートエンジン
- MySQL
- BCrypt Password Encoder
- HTML / CSS

---

## ✅ プロジェクト構成
```
com.example.authapp
 ├─ controller
 │   └─ AuthController.java
 ├─ model
 │   └─ User.java
 ├─ repository
 │   └─ UserMapper.java
 ├─ service
 │   └─ UserService.java
 ├─ AuthApplication.java
 └─ resources
     ├─ mapper
     │   └─ UserMapper.xml
     └─ templates
         └─ screen
             ├─ login.html
             ├─ register.html
             └─ home.html
```

---

## ✅ 機能一覧

### ➡︎ 新規登録機能
- メールアドレスとパスワードでユーザー登録
- パスワードは BCrypt により暗号化して保存
- 登録済みのメールアドレスの場合、登録不可

### ➡︎ ログイン機能
- メールアドレスとパスワードで認証
- BCrypt の `matches()` を使用してパスワードを検証
- ログイン成功時、ユーザー情報を Session に保存

### ➡︎ ホーム画面
- ログインしていない場合はアクセス不可
- ログイン後のみ表示可能

### ➡︎ ログアウト機能
- Session を無効化してログアウト

---

## ✅ データベース構成
```sql
CREATE TABLE test_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    pass VARCHAR(255) NOT NULL
);
```

---

## ✅ 実行方法
1. MySQL データベースを作成
2. `test_user` テーブルを作成
3. `application.yml` または `application.properties` にDB設定を記述
4. アプリケーションを起動
5. ブラウザで以下のURLにアクセス
```
http://localhost:8080/api/login
```

---

## ✅ 画面一覧
- ログイン画面
- 新規登録画面
- ホーム画面（ログイン後）

---

## ✅ 作成者
- **名前**: Moe Htet Htet Soe（モーテッ）

---

## ✅ 生成AIの使用について
- 本プロジェクトでは、以下の目的で生成AI（ChatGPT）を利用しました。

- プロジェクト全体の構成やログイン機能の実装方針を検討するため  
　（Spring Boot を約2年間使用していなかったため、記憶の整理と理解の補助として利用）

- CSS デザイン部分の作成および調整

- パスワードの安全な管理方法として、BCrypt に関する理解および実装方法の確認

- 生成AIは、コードの考え方や実装例の参考として使用しており、最終的な設計・実装は自身で確認・調整しています。


---

