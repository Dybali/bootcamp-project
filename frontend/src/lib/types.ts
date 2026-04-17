export type AuthResponse = {
  token: string
  user: {
    id: number
    firstName: string
    lastName: string
    dateOfBirth: string
    nationalityIdentity: string
    email: string
  }
}

export type BootcampResponse = {
  id: number
  name: string
  instructorId: number
  startDate: string
  endDate: string
  bootcampState: string
}

export type BootcampCreateRequest = {
  name: string
  startDate: string
  endDate: string
  instructorId: number
}

export type ApplicantResponse = {
  id: number
  firstName: string
  lastName: string
  dateOfBirth: string
  nationalityIdentity: string
  email: string
  about: string | null
}

export type ApplicantCreateRequest = {
  firstName: string
  lastName: string
  dateOfBirth: string
  nationalityIdentity: string
  email: string
  password: string
  about?: string
}

export type InstructorResponse = {
  id: number
  firstName: string
  lastName: string
  dateOfBirth: string
  nationalityIdentity: string
  email: string
  companyName: string
}

export type InstructorCreateRequest = {
  firstName: string
  lastName: string
  dateOfBirth: string
  nationalityIdentity: string
  email: string
  password: string
  companyName: string
}

export type EmployeeResponse = {
  id: number
  firstName: string
  lastName: string
  dateOfBirth: string
  nationalityIdentity: string
  email: string
  position: string
}

export type EmployeeCreateRequest = {
  firstName: string
  lastName: string
  dateOfBirth: string
  nationalityIdentity: string
  email: string
  password: string
  position: string
}

export type UserResponse = {
  id: number
  firstName: string
  lastName: string
  dateOfBirth: string
  nationalityIdentity: string
  email: string
}

export type UserCreateRequest = {
  firstName: string
  lastName: string
  dateOfBirth: string
  nationalityIdentity: string
  email: string
  password: string
}

export type ApplicationResponse = {
  id: number
  applicantId: number
  bootcampId: number
  applicationState: string
  createdAt: string
  updatedAt: string
}

export type ApplicationCreateRequest = {
  applicantId: number
  bootcampId: number
}

export type BlacklistResponse = {
  id: number
  reason: string
  date: string
  applicantId: number
}

export type BlacklistCreateRequest = {
  reason: string
  applicantId: number
}

