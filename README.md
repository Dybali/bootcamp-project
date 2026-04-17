# Bootcamp Management (Spring Boot + React)

## Proje yapısı

- `src/main/java`: Spring Boot backend (REST API)
- `frontend/`: Vite + React (TypeScript) frontend

## Backend çalıştırma

### Gereksinimler

- Java 17 (veya uyumlu)
- `JAVA_HOME` environment variable ayarlı olmalı

### Windows (JAVA_HOME) hızlı kurulum

- **Java kurulu mu kontrol**: PowerShell’de `java -version`
- **JAVA_HOME örnek**: `C:\Program Files\Java\jdk-17`
- Ayarladıktan sonra yeni terminal aç.

### Çalıştırma

Proje kökünde:

```bash
.\mvnw.cmd spring-boot:run
```

Backend varsayılan port: `8080`

## Frontend çalıştırma

`frontend/` klasöründe:

```bash
npm install
npm run dev
```

Frontend varsayılan port: `5173`

Backend URL ayarı: `frontend/.env` içindeki `VITE_API_URL`.

## Auth (JWT)

- `POST /api/auth/register`
- `POST /api/auth/login` → `{ token, user }`

Login sonrası token’ı `Authorization: Bearer <token>` header’ı ile çağırmalısın.

## Örnek ekran

Frontend şu an:

- Login / Register
- Dashboard
- Bootcamps (listeleme + ekleme + silme)
- Applicants / Applications / Blacklists / Instructors / Employees / Users (listeleme + ekleme + silme)

## Demo önerilen akış

1. Backend’i çalıştır (H2 in-memory).
2. Frontend’i çalıştır.
3. `/register` ile kullanıcı oluştur → `/login` ile giriş yap.
4. İlk bootcamp için **Instructor ID = 1** kullanabilirsin (seed).

# bootcamp-project
java sprıng internship final project
