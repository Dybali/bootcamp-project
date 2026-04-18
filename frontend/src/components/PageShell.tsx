import { Link } from 'react-router-dom'

export function PageShell({
  title,
  subtitle,
  backTo = '/dashboard',
  children,
}: {
  title: string
  subtitle?: string
  backTo?: string
  children: React.ReactNode
}) {
  return (
    <div className="container main">
      <div className="row" style={{ justifyContent: 'space-between', marginBottom: 12 }}>
        <Link className="pill" to={backTo}>
          ← Geri
        </Link>
      </div>
      <div className="panel wide">
        <div className="panel-header">
          <h1>{title}</h1>
          {subtitle && <p className="muted">{subtitle}</p>}
        </div>
        <div className="panel-body">{children}</div>
      </div>
    </div>
  )
}

