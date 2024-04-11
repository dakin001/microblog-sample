import React, { useCallback, useState, useContext } from 'react';
import { AccountApi } from "../apis/index";
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';
import AuthContext from '../components/AuthContext';


function Login() {
  const [formData, setFormData] = useState({
    nameOrEmail: 'Jack',
    password: '123456',
  });

  const handleChange = (event) => {
    setFormData(values => ({
      ...formData,
      [event.target.name]: event.target.value
    }))
    console.log(formData);
  }

  const authContext = useContext(AuthContext)
  const accountApi = new AccountApi();
  const navigate = useNavigate();
  const handleSubmit = useCallback((event) => {
    event.preventDefault(); // Prevent default form submission
    console.log(formData);
    accountApi.login(formData, (error, data, response) => {
      if (!error) {
        toast.info('login sucessful.');

        authContext.login(data)
        navigate('/feed')
      }
    })
  }, [formData, navigate]);

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <h2 className='text-center'> Welcome </h2>
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <input
                type="text"
                className="form-control"
                id="nameOrEmail"
                name="nameOrEmail"
                placeholder="Name/Email"
                value={formData.nameOrEmail}
                onChange={handleChange}
                required
              />
            </div>

            <div className="mb-3">
              <input
                type="password"
                className="form-control"
                id="password"
                name="password"
                placeholder='Password'
                value={formData.password}
                onChange={handleChange}
                required
              />
            </div>

            <button type="submit" className="btn btn-primary">Submit</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
