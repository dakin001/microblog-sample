import React, { useState } from 'react';
import { AccountApi } from "../apis/index";

function UserPage() {
  const [formData, setFormData] = useState({
    
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

    accountApi.login(formData, (error, data, response) => {

      window.location = "/home";

    })
  };

  return (
    <div className="container mt-5">
       
    </div>
  );
}

export default UserPage;
