# Bootcamp Management (Spring Boot + React)

This is my bootcamp project. It has a **Spring Boot REST API** and a simple **React admin panel** to manage a bootcamp system.

## What can you do in the app?

- Manage **Bootcamps**
- Manage **Applicants**
- Manage **Applications**
- Manage **Blacklists**
- Manage system users (**User / Instructor / Employee**)

## Main features

- **JWT Authentication**
  - After login, the API returns a token.
  - For protected endpoints, use: `Authorization: Bearer <token>`
- **Layered architecture**
  - `controller / service / repository / dto / businessrules / exception / mapper`
- **Validation and error handling**
  - DTO validation with `@Valid`
  - Global exception handler for readable API errors
- **React Admin Panel (CRUD)**
  - Login / Register
  - Dashboard
  - CRUD pages for Bootcamps, Applicants, Applications, Blacklists, Instructors, Employees, Users

## Tech stack

- **Backend**: Java 17, Spring Boot, Spring Security, Spring Data JPA, Validation, PostgreSQL, MapStruct
- **Auth**: JWT
- **Frontend**: Vite + React + TypeScript, Axios, React Router

## Project structure

- `src/main/java`: Spring Boot backend (REST API)
- `frontend/`: React frontend (Vite + TypeScript)

## Run PostgreSQL (Docker)

From the project root:

```bash
docker compose up -d
```

Default DB values (Docker):

- Host: `localhost`
- Port: `5433` (because `5432` can already be used on your computer)
- DB: `bootcampdb`
- User: `postgres`
- Password: `postgres`

You can override with environment variables:

- `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD`
- `HIBERNATE_DDL_AUTO` (default: `update`)

## Run backend (Spring Boot)

### Requirements

- Java 17+
- `JAVA_HOME` should be set

### Run

From the project root:

```bash
.\mvnw.cmd spring-boot:run
```

Backend default port: `8080`

## Run frontend (React)

Inside `frontend/`:

```bash
npm install
npm run dev
```

Frontend default port: `5173`

Backend URL config is in `frontend/.env` (`VITE_API_URL`).

## Auth endpoints (JWT)

- `POST /api/auth/register`
- `POST /api/auth/login` → returns `{ token, user }`

After login, send your token in the header: `Authorization: Bearer <token>`.

## First usage (quick demo flow)

1. Start PostgreSQL with Docker.
2. Start backend.
3. Start frontend.
4. Go to `/register` and create your user.
5. Login and use the admin panel.

### Demo seed

On first run, the backend inserts a demo instructor into the database.

- Email: `instructor@demo.com`
- Password: `Password123!`
- When creating a Bootcamp, you can use **Instructor ID = 1**
