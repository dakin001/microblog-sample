import React, { useState } from 'react';
import { AccountApi } from "../apis/index";

function Login() {
  const [formData, setFormData] = useState({
    nameOrEmail: 'user11',
    password: '123',
  });

  const handleChange = (event) => {
    setFormData(values => ({
      ...values,
       [event.target.name]: event.target.value
    }))
  }

  const accountApi = new AccountApi();

  const handleSubmit = (event) => {
    event.preventDefault(); // Prevent default form submission

    accountApi.login(formData,(error, data, response)=>{
  
window.location = "/posts";
 
    })
  };

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
                id="name"
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
