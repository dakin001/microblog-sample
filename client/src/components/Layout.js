import { Outlet, Link } from "react-router-dom";
import React, { useEffect, useState, useContext } from 'react';
import AuthContext from '../components/AuthContext';

function Layout() {
  const authContext=  useContext(AuthContext) 

  return (
    <>
      Moi Kiitos
      <nav className="nav ">
        {
          authContext.currentUser ?
            <>
              <li className="nav-item">
                <Link className="nav-link" to="/">Home</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/feed">Feed</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/users">Users</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/logout" >Logout</Link>
              </li>
            </>
            :
            <>
              <li className="nav-item">
                <Link className="nav-link" to="/signup">Signup</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/login">Login</Link>
              </li>
            </>
        }
      </nav>
      <Outlet />
    </>
  )
};

export default Layout;