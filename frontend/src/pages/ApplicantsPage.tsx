import { useEffect, useMemo, useState } from 'react'
import { api } from '../lib/api'
import type { ApplicantCreateRequest, ApplicantResponse } from '../lib/types'
import { PageShell } from '../components/PageShell'

export function ApplicantsPage() {
  const [items, setItems] = useState<ApplicantResponse[]>([])
  const [error, setError] = useState<string | null>(null)
  const [loading, setLoading] = useState(false)

  const [firstName, setFirstName] = useState('')
  const [lastName, setLastName] = useState('')
  const [email, setEmail] = useState('')
  const [dateOfBirth, setDateOfBirth] = useState('')
  const [nationalityIdentity, setNationalityIdentity] = useState('')
  const [password, setPassword] = useState('')
  const [about, setAbout] = useState('')

  const canSubmit = useMemo(
    () => firstName && lastName && email && dateOfBirth && nationalityIdentity.length === 11 && password.length >= 6,
    [firstName, lastName, email, dateOfBirth, nationalityIdentity, password],
  )

  async function load() {
    setError(null)
    setLoading(true)
    try {
      const res = await api.get<ApplicantResponse[]>('/api/applicants')
      setItems(res.data)
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Applicant listesi alınamadı')
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
      const body: ApplicantCreateRequest = {
        firstName,
        lastName,
        email,
        dateOfBirth,
        nationalityIdentity,
        password,
        about: about || undefined,
      }
      await api.post('/api/applicants', body)
      setFirstName('')
      setLastName('')
      setEmail('')
      setDateOfBirth('')
      setNationalityIdentity('')
      setPassword('')
      setAbout('')
      await load()
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Applicant oluşturulamadı')
    }
  }

  async function remove(id: number) {
    setError(null)
    try {
      await api.delete(`/api/applicants/${id}`)
      await load()
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Applicant silinemedi')
    }
  }

  return (
    <PageShell title="Applicants" subtitle={loading ? 'Yükleniyor…' : 'Listele / Ekle / Sil'}>
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
          <label>About</label>
          <input value={about} onChange={(e) => setAbout(e.target.value)} placeholder="Opsiyonel" />
        </div>
        <div className="col-12 row" style={{ justifyContent: 'flex-end' }}>
          <button className="primary" type="button" disabled={!canSubmit} onClick={create}>
            Applicant ekle
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
              <th>TC</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {items.map((a) => (
              <tr key={a.id}>
                <td>{a.id}</td>
                <td>
                  {a.firstName} {a.lastName}
                </td>
                <td>{a.email}</td>
                <td>{a.nationalityIdentity}</td>
                <td style={{ textAlign: 'right' }}>
                  <button className="danger" type="button" onClick={() => remove(a.id)}>
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

