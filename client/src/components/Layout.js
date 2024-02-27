import { Outlet, Link } from "react-router-dom";

const Layout = () => {
  return (
    <>
    Moi Kiitos
      <nav className="nav ">
          <li className="nav-item">
            <Link className="nav-link" to="/">Home</Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/blogs">Blogs</Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/users">Users</Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/signup">Signup</Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/login">Login</Link>
          </li>
      </nav>
      <Outlet />
    </>
  )
};

export default Layout;