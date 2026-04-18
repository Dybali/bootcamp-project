import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { api } from '../lib/api'
import type { AuthResponse } from '../lib/types'
import { setToken, setUser } from '../lib/storage'

export function LoginPage() {
  const navigate = useNavigate()
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState<string | null>(null)
  const [loading, setLoading] = useState(false)

  async function onSubmit(e: React.FormEvent) {
    e.preventDefault()
    setError(null)
    setLoading(true)
    try {
      const res = await api.post<AuthResponse>('/api/auth/login', { email, password })
      setToken(res.data.token)
      setUser(res.data.user)
      navigate('/dashboard', { replace: true })
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Giriş başarısız')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="container main">
      <div className="panel">
        <div className="panel-header">
          <h1>Giriş</h1>
          <p className="muted">E-posta ve şifrenle panele giriş yap.</p>
        </div>
        <div className="panel-body">
          {error && <div className="alert">{error}</div>}
          <form onSubmit={onSubmit} className="grid" style={{ marginTop: 12 }}>
            <div className="col-12">
              <label>Email</label>
              <input value={email} onChange={(e) => setEmail(e.target.value)} placeholder="email@example.com" />
            </div>
            <div className="col-12">
              <label>Şifre</label>
              <input value={password} onChange={(e) => setPassword(e.target.value)} type="password" />
            </div>
            <div className="col-12 row" style={{ justifyContent: 'space-between' }}>
              <button className="primary" type="submit" disabled={loading}>
                {loading ? 'Giriş yapılıyor…' : 'Giriş yap'}
              </button>
              <span className="muted">
                Hesabın yok mu? <Link to="/register">Kayıt ol</Link>
              </span>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}

