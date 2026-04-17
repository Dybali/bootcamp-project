import { useEffect, useMemo, useState } from 'react'
import { api } from '../lib/api'
import type { InstructorCreateRequest, InstructorResponse } from '../lib/types'
import { PageShell } from '../components/PageShell'

export function InstructorsPage() {
  const [items, setItems] = useState<InstructorResponse[]>([])
  const [error, setError] = useState<string | null>(null)
  const [loading, setLoading] = useState(false)

  const [firstName, setFirstName] = useState('')
  const [lastName, setLastName] = useState('')
  const [email, setEmail] = useState('')
  const [dateOfBirth, setDateOfBirth] = useState('')
  const [nationalityIdentity, setNationalityIdentity] = useState('')
  const [password, setPassword] = useState('')
  const [companyName, setCompanyName] = useState('')

  const canSubmit = useMemo(
    () =>
      firstName &&
      lastName &&
      email &&
      dateOfBirth &&
      nationalityIdentity.length === 11 &&
      password.length >= 6 &&
      companyName.length >= 2,
    [firstName, lastName, email, dateOfBirth, nationalityIdentity, password, companyName],
  )

  async function load() {
    setError(null)
    setLoading(true)
    try {
      const res = await api.get<InstructorResponse[]>('/api/instructors')
      setItems(res.data)
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Instructor listesi alınamadı')
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
      const body: InstructorCreateRequest = {
        firstName,
        lastName,
        email,
        dateOfBirth,
        nationalityIdentity,
        password,
        companyName,
      }
      await api.post('/api/instructors', body)
      setFirstName('')
      setLastName('')
      setEmail('')
      setDateOfBirth('')
      setNationalityIdentity('')
      setPassword('')
      setCompanyName('')
      await load()
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Instructor oluşturulamadı')
    }
  }

  async function remove(id: number) {
    setError(null)
    try {
      await api.delete(`/api/instructors/${id}`)
      await load()
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Instructor silinemedi')
    }
  }

  return (
    <PageShell title="Instructors" subtitle={loading ? 'Yükleniyor…' : 'Listele / Ekle / Sil'}>
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
        <div className="col-12">
          <label>Şirket</label>
          <input value={companyName} onChange={(e) => setCompanyName(e.target.value)} />
        </div>
        <div className="col-12 row" style={{ justifyContent: 'flex-end' }}>
          <button className="primary" type="button" disabled={!canSubmit} onClick={create}>
            Instructor ekle
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
              <th>Şirket</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {items.map((i) => (
              <tr key={i.id}>
                <td>{i.id}</td>
                <td>
                  {i.firstName} {i.lastName}
                </td>
                <td>{i.email}</td>
                <td>{i.companyName}</td>
                <td style={{ textAlign: 'right' }}>
                  <button className="danger" type="button" onClick={() => remove(i.id)}>
                    Sil
                  </button>
                </td>
              </tr>
            ))}
            {items.length === 0 && (
              <tr>
                <td colSpan={5} className="muted">
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

