import { Link } from 'react-router-dom'
import { getUser } from '../lib/storage'

export function DashboardPage() {
  const user = getUser()
  return (
    <div className="container main">
      <div className="panel wide">
        <div className="panel-header">
          <h1>Dashboard</h1>
          <p className="muted">
            Hoş geldin{user ? `, ${user.firstName} ${user.lastName}` : ''}.
          </p>
        </div>
        <div className="panel-body">
          <div className="row">
            <Link className="pill" to="/bootcamps">Bootcamps</Link>
            <Link className="pill" to="/applicants">Applicants</Link>
            <Link className="pill" to="/applications">Applications</Link>
            <Link className="pill" to="/blacklists">Blacklists</Link>
            <Link className="pill" to="/instructors">Instructors</Link>
            <Link className="pill" to="/employees">Employees</Link>
            <Link className="pill" to="/users">Users</Link>
          </div>
        </div>
      </div>
    </div>
  )
}

