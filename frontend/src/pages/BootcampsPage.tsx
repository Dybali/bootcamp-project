import { useEffect, useMemo, useState } from 'react'
import { api } from '../lib/api'
import type { BootcampCreateRequest, BootcampResponse } from '../lib/types'
import { PageShell } from '../components/PageShell'

export function BootcampsPage() {
  const [items, setItems] = useState<BootcampResponse[]>([])
  const [error, setError] = useState<string | null>(null)
  const [loading, setLoading] = useState(false)

  const [name, setName] = useState('')
  const [startDate, setStartDate] = useState('')
  const [endDate, setEndDate] = useState('')
  const [instructorId, setInstructorId] = useState<number>(1)

  const canSubmit = useMemo(() => name && startDate && endDate && instructorId > 0, [name, startDate, endDate, instructorId])

  async function load() {
    setError(null)
    setLoading(true)
    try {
      const res = await api.get<BootcampResponse[]>('/api/bootcamps')
      setItems(res.data)
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Bootcamp listesi alınamadı')
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
      const body: BootcampCreateRequest = {
        name,
        startDate,
        endDate,
        instructorId,
      }
      await api.post('/api/bootcamps', body)
      setName('')
      setStartDate('')
      setEndDate('')
      await load()
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Bootcamp oluşturulamadı')
    }
  }

  async function remove(id: number) {
    setError(null)
    try {
      await api.delete(`/api/bootcamps/${id}`)
      await load()
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Bootcamp silinemedi')
    }
  }

  return (
    <PageShell title="Bootcamps" subtitle={loading ? 'Yükleniyor…' : 'Listele / Ekle / Sil'}>
      {error && <div className="alert">{error}</div>}

      <div className="grid" style={{ marginTop: 12 }}>
        <div className="col-6">
          <label>Ad</label>
          <input value={name} onChange={(e) => setName(e.target.value)} />
        </div>
        <div className="col-6">
          <label>Instructor ID</label>
          <input value={String(instructorId)} onChange={(e) => setInstructorId(Number(e.target.value))} inputMode="numeric" />
        </div>
        <div className="col-6">
          <label>Başlangıç</label>
          <input value={startDate} onChange={(e) => setStartDate(e.target.value)} type="date" />
        </div>
        <div className="col-6">
          <label>Bitiş</label>
          <input value={endDate} onChange={(e) => setEndDate(e.target.value)} type="date" />
        </div>
        <div className="col-12 row" style={{ justifyContent: 'flex-end' }}>
          <button className="primary" disabled={!canSubmit} onClick={create} type="button">
            Bootcamp ekle
          </button>
        </div>
      </div>

      <div style={{ marginTop: 16 }}>
        <table className="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Ad</th>
              <th>Başlangıç</th>
              <th>Bitiş</th>
              <th>State</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {items.map((b) => (
              <tr key={b.id}>
                <td>{b.id}</td>
                <td>{b.name}</td>
                <td>{b.startDate}</td>
                <td>{b.endDate}</td>
                <td>{b.bootcampState}</td>
                <td style={{ textAlign: 'right' }}>
                  <button className="danger" onClick={() => remove(b.id)} type="button">
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

