import { Link, Outlet, useNavigate } from 'react-router-dom'
import { getUser, getToken, logout } from './lib/storage'

export function AppLayout() {
  const navigate = useNavigate()
  const user = getUser()
  const token = getToken()

  return (
    <>
      <div className="nav">
        <div className="container nav-inner">
          <div className="brand">
            <Link to={token ? '/dashboard' : '/login'}>Bootcamp Management</Link>
            <span className="pill muted">Spring Boot + React</span>
          </div>
          <div className="row">
            {token ? (
              <>
                <span className="pill">{user ? user.email : 'Giriş yapıldı'}</span>
                <button
                  type="button"
                  onClick={() => {
                    logout()
                    navigate('/login', { replace: true })
                  }}
                >
                  Çıkış
                </button>
              </>
            ) : (
              <>
                <Link className="pill" to="/login">
                  Giriş
                </Link>
                <Link className="pill" to="/register">
                  Kayıt
                </Link>
              </>
            )}
          </div>
        </div>
      </div>
      <Outlet />
    </>
  )
}

