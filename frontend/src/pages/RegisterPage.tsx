import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { api } from '../lib/api'

export function RegisterPage() {
  const navigate = useNavigate()
  const [firstName, setFirstName] = useState('')
  const [lastName, setLastName] = useState('')
  const [email, setEmail] = useState('')
  const [dateOfBirth, setDateOfBirth] = useState('')
  const [nationalityIdentity, setNationalityIdentity] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState<string | null>(null)
  const [loading, setLoading] = useState(false)

  async function onSubmit(e: React.FormEvent) {
    e.preventDefault()
    setError(null)
    setLoading(true)
    try {
      await api.post('/api/auth/register', {
        firstName,
        lastName,
        email,
        dateOfBirth,
        nationalityIdentity,
        password,
      })
      navigate('/login', { replace: true })
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Kayıt başarısız')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="container main">
      <div className="panel">
        <div className="panel-header">
          <h1>Kayıt</h1>
          <p className="muted">Hesap oluştur ve sisteme giriş yap.</p>
        </div>
        <div className="panel-body">
          {error && <div className="alert">{error}</div>}
          <form onSubmit={onSubmit} className="grid" style={{ marginTop: 12 }}>
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
              <input
                value={nationalityIdentity}
                onChange={(e) => setNationalityIdentity(e.target.value)}
                placeholder="11 haneli"
              />
            </div>
            <div className="col-6">
              <label>Şifre</label>
              <input value={password} onChange={(e) => setPassword(e.target.value)} type="password" />
            </div>
            <div className="col-12 row" style={{ justifyContent: 'space-between' }}>
              <button className="primary" type="submit" disabled={loading}>
                {loading ? 'Kaydediliyor…' : 'Kayıt ol'}
              </button>
              <span className="muted">
                Zaten hesabın var mı? <Link to="/login">Giriş</Link>
              </span>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}

