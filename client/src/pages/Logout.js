import React, { useEffect, useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthContext from '../components/AuthContext';

function Logout() {
  const authContext = useContext(AuthContext)

  const navigate = useNavigate();

  useEffect(() => {
    authContext.logout();
    navigate('/login')
  });

  return (
    <></>
  );
}

export default Logout;
