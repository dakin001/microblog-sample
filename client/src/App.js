import logo from './logo.svg';
import './App.css';
import AppNavigator from './components/Navigation'; 
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  return (
    <div className="App">
       <ToastContainer />

      <AppNavigator />
    </div>
  );
}

export default App;
