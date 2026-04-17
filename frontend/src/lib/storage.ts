export type StoredUser = {
  id: number
  firstName: string
  lastName: string
  dateOfBirth: string
  nationalityIdentity: string
  email: string
}

const TOKEN_KEY = 'bootcamp.token'
const USER_KEY = 'bootcamp.user'

export function getToken(): string | null {
  return localStorage.getItem(TOKEN_KEY)
}

export function setToken(token: string) {
  localStorage.setItem(TOKEN_KEY, token)
}

export function clearToken() {
  localStorage.removeItem(TOKEN_KEY)
}

export function getUser(): StoredUser | null {
  const raw = localStorage.getItem(USER_KEY)
  if (!raw) return null
  try {
    return JSON.parse(raw) as StoredUser
  } catch {
    return null
  }
}

export function setUser(user: StoredUser) {
  localStorage.setItem(USER_KEY, JSON.stringify(user))
}

export function clearUser() {
  localStorage.removeItem(USER_KEY)
}

export function logout() {
  clearToken()
  clearUser()
}

