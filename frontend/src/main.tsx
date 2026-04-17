import React from 'react'
import ReactDOM from 'react-dom/client'
import { createBrowserRouter, Navigate, RouterProvider } from 'react-router-dom'
import './style.css'
import { AppLayout } from './App'
import { LoginPage } from './pages/LoginPage'
import { RegisterPage } from './pages/RegisterPage'
import { DashboardPage } from './pages/DashboardPage'
import { BootcampsPage } from './pages/BootcampsPage'
import { ApplicantsPage } from './pages/ApplicantsPage'
import { ApplicationsPage } from './pages/ApplicationsPage'
import { BlacklistsPage } from './pages/BlacklistsPage'
import { InstructorsPage } from './pages/InstructorsPage'
import { EmployeesPage } from './pages/EmployeesPage'
import { UsersPage } from './pages/UsersPage'
import { ProtectedRoute } from './components/ProtectedRoute'

const router = createBrowserRouter([
  {
    path: '/',
    element: <AppLayout />,
    children: [
      { index: true, element: <Navigate to="/dashboard" replace /> },
      { path: 'login', element: <LoginPage /> },
      { path: 'register', element: <RegisterPage /> },
      {
        path: 'dashboard',
        element: (
          <ProtectedRoute>
            <DashboardPage />
          </ProtectedRoute>
        ),
      },
      {
        path: 'bootcamps',
        element: (
          <ProtectedRoute>
            <BootcampsPage />
          </ProtectedRoute>
        ),
      },
      {
        path: 'applicants',
        element: (
          <ProtectedRoute>
            <ApplicantsPage />
          </ProtectedRoute>
        ),
      },
      {
        path: 'applications',
        element: (
          <ProtectedRoute>
            <ApplicationsPage />
          </ProtectedRoute>
        ),
      },
      {
        path: 'blacklists',
        element: (
          <ProtectedRoute>
            <BlacklistsPage />
          </ProtectedRoute>
        ),
      },
      {
        path: 'instructors',
        element: (
          <ProtectedRoute>
            <InstructorsPage />
          </ProtectedRoute>
        ),
      },
      {
        path: 'employees',
        element: (
          <ProtectedRoute>
            <EmployeesPage />
          </ProtectedRoute>
        ),
      },
      {
        path: 'users',
        element: (
          <ProtectedRoute>
            <UsersPage />
          </ProtectedRoute>
        ),
      },
    ],
  },
])

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)

