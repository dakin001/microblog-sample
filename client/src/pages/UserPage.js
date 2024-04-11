import React, { useCallback, useEffect, useState } from 'react';
import { UserApi } from "../apis/index";
import Follow from '../components/Follow';

const userApi = new UserApi();

function UserPage() {
  const [formData, setFormData] = useState({
  });

  const [userData, setUserData] = useState({
    name: '',
    followingCount: 0,
    followerCount: 0,
    following: [],
    followers: [],
  });

  const handleChange = (event) => {
    setFormData(values => ({
      ...values,
      [event.target.name]: event.target.value
    }))
  }

  const handleSubmit = useCallback((event) => {
    event.preventDefault(); // Prevent default form submission

    userApi.findUser(formData, (error, data, response) => {
      if (error) {
        return;
      }
      // setUserData(data||{});
      setUserData(values => ({
        ...values,
        'id': data.id,
        'name': data.name
      }));

      loadFollow(data.id);
    });

  }, [formData]);

  useEffect(() => {
    let loginUser = JSON.parse( localStorage.getItem("user")) ; 
 
    setUserData(values => ({
      ...values,
      'id': loginUser.id,
      'name': loginUser.name,
    }));

    loadFollow(loginUser.id);
  }, []);

  const loadFollow = (userId)=>{
    userApi.listFollowers(userId, {}, (error, data, response) => {
      if (error) {
        return;
      }
      setUserData(values => ({
        ...values,
        followers: data.items,
        followerCount: data.total,
      }));
    });

    userApi.listFollowing(userId, {}, (error, data, response) => {
      if (error) {
        return;
      }
      setUserData(values => ({
        ...values,
        following: data.items,
        followingCount: data.total,
      }));
    });
  }

  return (
    <div className="container mt-5">
      <div className="row">
        <div className="col-3">
          <Follow user={userData} />
        </div>
        <div className="col-9">
          <div className="card">
            <div className="card-body">

              <form onSubmit={handleSubmit}>
                <div className="input-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="nameOrEmail"
                    name="nameOrEmail"
                    placeholder="search by name/email"
                    aria-label="Recipient's username" aria-describedby="button-addon2"
                    value={formData.nameOrEmail}
                    onChange={handleChange}
                    required
                  />

                  <button className="btn btn-outline-secondary" type="submit" id="button-addon2">Search</button>

                </div>
              </form>
            </div>
          </div>
          <div className="mb-4"> </div>

          <ul className="nav nav-tabs">
            <li className="nav-item">
              <a className="nav-link active" aria-current="page" href="#">Following</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">Follower</a>
            </li>

          </ul>
          <div className="mb-2"> </div>
          <ul className="list-group">
            {userData.following.map((item) =>
              <li key={item.id} className="list-group-item d-flex justify-content-between align-items-start">
                <div className="ms-2 me-auto container">
                  <div className="row">
                    <div className="col-2 fw-bold">{item.name}</div>

                    <div className="col-10">{item.email}</div>
                  </div></div>

              </li>
            )}
          </ul>
        </div>
      </div>

    </div>
  );
}

export default UserPage;
