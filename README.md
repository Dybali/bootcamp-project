# Bootcamp Management (Spring Boot + React)

## Uygulama ne yapar?

Bootcamp Management, bir bootcamp eğitim sürecini yönetmek için geliştirilmiş bir **REST API (Spring Boot)** ve ona bağlı bir **admin panel (React)** uygulamasıdır.

Amaç:
- Bootcamp’leri tanımlamak ve yönetmek
- Başvuru sahiplerini (Applicant) yönetmek
- Bootcamp başvurularını (Application) yönetmek
- Kara liste (Blacklist) ile başvuruları kural bazlı kısıtlamak
- Sistemdeki kullanıcı tiplerini (User/Instructor/Employee) yönetmek

## Öne çıkan özellikler

- **JWT Authentication**
  - Login sonrası token üretilir.
  - Token ile korumalı endpoint’lere erişilir (`Authorization: Bearer <token>`).

- **Katmanlı mimari**
  - `controller / service / repository / dto / businessrules / exception / mapper` yapısı.

- **Validasyon & hata yönetimi**
  - DTO doğrulamaları (`@Valid`, bean validation)
  - Global exception handler ile okunabilir hata cevapları.

- **CRUD Yönetim Paneli (React)**
  - Login / Register
  - Dashboard
  - Bootcamps: Listele / Ekle / Sil
  - Applicants: Listele / Ekle / Sil
  - Applications: Listele / Ekle / Sil
  - Blacklists: Listele / Ekle / Sil
  - Instructors: Listele / Ekle / Sil
  - Employees: Listele / Ekle / Sil
  - Users: Listele / Ekle / Sil

- **Kurallar (Business Rules)**
  - Bootcamp tarih kontrolü (startDate < endDate)
  - Aynı bootcamp adına mükerrer kayıt engeli
  - Başvuru tekrarını engelleme (aynı applicant aynı bootcamp’e tekrar başvuramasın)
  - Blacklist kontrolü (blacklist’teyse başvuru yapamasın)

## Teknolojiler

- Backend: Java 17, Spring Boot, Spring Security, Spring Data JPA, Validation, H2, MapStruct
- Auth: JWT
- Frontend: Vite + React + TypeScript, Axios, React Router

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

## İlk giriş ve veritabanı

- **Ayrı bir veritabanı kurmana gerek yok.** Varsayılan olarak **H2 (bellek içi / in-memory)** kullanılır; uygulama kapanınca veriler sıfırlanır (`application.properties` içinde `spring.jpa.hibernate.ddl-auto=create-drop`).
- **H2 Console** (geliştirme): `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:bootcampdb`, kullanıcı `sa` — detaylar `application.properties` içinde).
- **Panele nasıl girilir?**
  1. Önce backend’i, sonra frontend’i çalıştır.
  2. Tarayıcıda **Kayıt ol** ile kendi kullanıcını oluştur (e-posta, şifre, doğum tarihi, 11 haneli TC alanı zorunlu).
  3. **Giriş** ile aynı e-posta ve şifreyle oturum aç. Token tarayıcıda saklanır; korumalı sayfalar otomatik API’ye ekler.
- **Hazır demo eğitmen (seed):** İlk açılışta sistem bir **Instructor** ekler. Bootcamp eklerken **Instructor ID = 1** kullanabilirsin. (Örnek hesap: `instructor@demo.com` / `Password123!` — panel girişi için değil, tabloda kayıt olması içindir; panele kendi register kullanıcınla gir.)

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
