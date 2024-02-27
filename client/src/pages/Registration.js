import React, { useState } from 'react';
import { AccountApi } from "../apis/index";
import { AccountRegistrationDto } from '../apis/model/AccountRegistrationDto';

function Registration() {
  const [formData, setFormData] = useState({
    // name: '',
    // email: '',
    // password: '',
    // confirmPassword: '',

    name: 'user11',
    email: 'user11@example.com',
    password: '123',
    confirmPassword: '123',
  });

  const handleChange = (event) => {
    setFormData({
      ...formData,
      [event.target.name]: event.target.value,
    });
  };

  const accountApi = new AccountApi();

  const handleSubmit = (event) => {
    event.preventDefault(); // Prevent default form submission

    console.log('Form submitted:', formData);
    accountApi.register(formData, (a) => {
      console.log(a);
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
                placeholder="Name"
                value={formData.name}
                onChange={handleChange}
                required
              />
            </div>
            <div className="mb-3">
              <input
                type="email"
                className="form-control"
                id="email"
                placeholder="Email"
                value={formData.email}
                onChange={handleChange}
                required
              />
            </div>
            <div className="mb-3">
              <input
                type="password"
                className="form-control"
                id="password"
                placeholder='Password'
                value={formData.password}
                onChange={handleChange}
                required
              />
            </div>
            <div className="mb-3">
              <input
                type="password"
                className="form-control"
                id="confirmPassword"
                placeholder='Confirm Password'
                value={formData.confirmPassword}
                onChange={handleChange}
                required
              />
            </div>
            <button type="submit" className="btn btn-primary">Register</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Registration;
