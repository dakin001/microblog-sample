import logo from './logo.svg';
import './App.css';
import AppNavigator from './components/Navigation';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import AuthContext from './components/AuthContext';
import { useState, useMemo, useCallback ,useEffect} from "react";

function App() {

  const [currentUser, setCurrentUser] = useState(null);
  const login = useCallback((user) => {
    localStorage.setItem("user", JSON.stringify(user));
    setCurrentUser(user);
  }, [])
  const logout = useCallback(() => {
    localStorage.removeItem("user");
    setCurrentUser(null);
  }, [])

  useEffect(() => {
    let loginUser = JSON.parse(localStorage.getItem("user"));
    setCurrentUser(loginUser);
  }, []);

  const authContextValue = useMemo(
    () => ({
      currentUser,
      login,
      logout
    }), [currentUser, login, logout])

  return (
    <div className="App">
      <ToastContainer />
      <AuthContext.Provider value={authContextValue}>
        <AppNavigator />
      </AuthContext.Provider>
    </div>
  );
}

export default App;
