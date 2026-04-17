import { useEffect, useMemo, useState } from 'react'
import { api } from '../lib/api'
import type { BlacklistCreateRequest, BlacklistResponse } from '../lib/types'
import { PageShell } from '../components/PageShell'

export function BlacklistsPage() {
  const [items, setItems] = useState<BlacklistResponse[]>([])
  const [error, setError] = useState<string | null>(null)
  const [loading, setLoading] = useState(false)

  const [reason, setReason] = useState('')
  const [applicantId, setApplicantId] = useState<number>(1)

  const canSubmit = useMemo(() => reason.length >= 10 && applicantId > 0, [reason, applicantId])

  async function load() {
    setError(null)
    setLoading(true)
    try {
      const res = await api.get<BlacklistResponse[]>('/api/blacklists')
      setItems(res.data)
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Blacklist listesi alınamadı')
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
      const body: BlacklistCreateRequest = { reason, applicantId }
      await api.post('/api/blacklists', body)
      setReason('')
      await load()
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Blacklist oluşturulamadı')
    }
  }

  async function remove(id: number) {
    setError(null)
    try {
      await api.delete(`/api/blacklists/${id}`)
      await load()
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Blacklist silinemedi')
    }
  }

  return (
    <PageShell title="Blacklists" subtitle={loading ? 'Yükleniyor…' : 'Listele / Ekle / Sil'}>
      {error && <div className="alert">{error}</div>}

      <div className="grid" style={{ marginTop: 12 }}>
        <div className="col-12">
          <label>Reason</label>
          <input value={reason} onChange={(e) => setReason(e.target.value)} placeholder="En az 10 karakter" />
        </div>
        <div className="col-6">
          <label>Applicant ID</label>
          <input value={String(applicantId)} onChange={(e) => setApplicantId(Number(e.target.value))} inputMode="numeric" />
        </div>
        <div className="col-12 row" style={{ justifyContent: 'flex-end' }}>
          <button className="primary" type="button" disabled={!canSubmit} onClick={create}>
            Blacklist ekle
          </button>
        </div>
      </div>

      <div style={{ marginTop: 16 }}>
        <table className="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Applicant</th>
              <th>Date</th>
              <th>Reason</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {items.map((b) => (
              <tr key={b.id}>
                <td>{b.id}</td>
                <td>{b.applicantId}</td>
                <td>{b.date}</td>
                <td>{b.reason}</td>
                <td style={{ textAlign: 'right' }}>
                  <button className="danger" type="button" onClick={() => remove(b.id)}>
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

