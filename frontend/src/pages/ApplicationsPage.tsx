import { useEffect, useMemo, useState } from 'react'
import { api } from '../lib/api'
import type { ApplicationCreateRequest, ApplicationResponse } from '../lib/types'
import { PageShell } from '../components/PageShell'

export function ApplicationsPage() {
  const [items, setItems] = useState<ApplicationResponse[]>([])
  const [error, setError] = useState<string | null>(null)
  const [loading, setLoading] = useState(false)

  const [applicantId, setApplicantId] = useState<number>(1)
  const [bootcampId, setBootcampId] = useState<number>(1)

  const canSubmit = useMemo(() => applicantId > 0 && bootcampId > 0, [applicantId, bootcampId])

  async function load() {
    setError(null)
    setLoading(true)
    try {
      const res = await api.get<ApplicationResponse[]>('/api/applications')
      setItems(res.data)
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Application listesi alınamadı')
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
      const body: ApplicationCreateRequest = { applicantId, bootcampId }
      await api.post('/api/applications', body)
      await load()
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Application oluşturulamadı')
    }
  }

  async function remove(id: number) {
    setError(null)
    try {
      await api.delete(`/api/applications/${id}`)
      await load()
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Application silinemedi')
    }
  }

  return (
    <PageShell title="Applications" subtitle={loading ? 'Yükleniyor…' : 'Listele / Ekle / Sil'}>
      {error && <div className="alert">{error}</div>}

      <div className="grid" style={{ marginTop: 12 }}>
        <div className="col-6">
          <label>Applicant ID</label>
          <input value={String(applicantId)} onChange={(e) => setApplicantId(Number(e.target.value))} inputMode="numeric" />
        </div>
        <div className="col-6">
          <label>Bootcamp ID</label>
          <input value={String(bootcampId)} onChange={(e) => setBootcampId(Number(e.target.value))} inputMode="numeric" />
        </div>
        <div className="col-12 row" style={{ justifyContent: 'flex-end' }}>
          <button className="primary" type="button" disabled={!canSubmit} onClick={create}>
            Application ekle
          </button>
        </div>
      </div>

      <div style={{ marginTop: 16 }}>
        <table className="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Applicant</th>
              <th>Bootcamp</th>
              <th>State</th>
              <th>Created</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {items.map((a) => (
              <tr key={a.id}>
                <td>{a.id}</td>
                <td>{a.applicantId}</td>
                <td>{a.bootcampId}</td>
                <td>{a.applicationState}</td>
                <td>{a.createdAt}</td>
                <td style={{ textAlign: 'right' }}>
                  <button className="danger" type="button" onClick={() => remove(a.id)}>
                    Sil
                  </button>
                </td>
              </tr>
            ))}
            {items.length === 0 && (
              <tr>
                <td colSpan={6} className="muted">
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

