import { useEffect, useMemo, useState } from 'react'
import { api } from '../lib/api'
import type { UserCreateRequest, UserResponse } from '../lib/types'
import { PageShell } from '../components/PageShell'

export function UsersPage() {
  const [items, setItems] = useState<UserResponse[]>([])
  const [error, setError] = useState<string | null>(null)
  const [loading, setLoading] = useState(false)

  const [firstName, setFirstName] = useState('')
  const [lastName, setLastName] = useState('')
  const [email, setEmail] = useState('')
  const [dateOfBirth, setDateOfBirth] = useState('')
  const [nationalityIdentity, setNationalityIdentity] = useState('')
  const [password, setPassword] = useState('')

  const canSubmit = useMemo(
    () => firstName && lastName && email && dateOfBirth && nationalityIdentity.length === 11 && password.length >= 6,
    [firstName, lastName, email, dateOfBirth, nationalityIdentity, password],
  )

  async function load() {
    setError(null)
    setLoading(true)
    try {
      const res = await api.get<UserResponse[]>('/api/users')
      setItems(res.data)
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'User listesi alınamadı')
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    load()
  }, [])

  async function create() {
    setError(null)
    try {
      const body: UserCreateRequest = {
        firstName,
        lastName,
        email,
        dateOfBirth,
        nationalityIdentity,
        password,
      }
      await api.post('/api/users', body)
      setFirstName('')
      setLastName('')
      setEmail('')
      setDateOfBirth('')
      setNationalityIdentity('')
      setPassword('')
      await load()
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'User oluşturulamadı')
    }
  }

  async function remove(id: number) {
    setError(null)
    try {
      await api.delete(`/api/users/${id}`)
      await load()
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'User silinemedi')
    }
  }

  return (
    <PageShell title="Users" subtitle={loading ? 'Yükleniyor…' : 'Listele / Ekle / Sil'}>
      {error && <div className="alert">{error}</div>}

      <div className="grid" style={{ marginTop: 12 }}>
        <div className="col-6">
          <label>Ad</label>
          <input value={firstName} onChange={(e) => setFirstName(e.target.value)} />
        </div>
        <div className="col-6">
          <label>Soyad</label>
          <input value={lastName} onChange={(e) => setLastName(e.target.value)} />
        </div>
        <div className="col-6">
          <label>Email</label>
          <input value={email} onChange={(e) => setEmail(e.target.value)} />
        </div>
        <div className="col-6">
          <label>Doğum tarihi</label>
          <input value={dateOfBirth} onChange={(e) => setDateOfBirth(e.target.value)} type="date" />
        </div>
        <div className="col-6">
          <label>TC Kimlik No</label>
          <input value={nationalityIdentity} onChange={(e) => setNationalityIdentity(e.target.value)} />
        </div>
        <div className="col-6">
          <label>Şifre</label>
          <input value={password} onChange={(e) => setPassword(e.target.value)} type="password" />
        </div>
        <div className="col-12 row" style={{ justifyContent: 'flex-end' }}>
          <button className="primary" type="button" disabled={!canSubmit} onClick={create}>
            User ekle
          </button>
        </div>
      </div>

      <div style={{ marginTop: 16 }}>
        <table className="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Ad Soyad</th>
              <th>Email</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {items.map((u) => (
              <tr key={u.id}>
                <td>{u.id}</td>
                <td>
                  {u.firstName} {u.lastName}
                </td>
                <td>{u.email}</td>
                <td style={{ textAlign: 'right' }}>
                  <button className="danger" type="button" onClick={() => remove(u.id)}>
                    Sil
                  </button>
                </td>
              </tr>
            ))}
            {items.length === 0 && (
              <tr>
                <td colSpan={4} className="muted">
                  Kayıt yok.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </PageShell>
  )
}

