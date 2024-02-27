import React, { useEffect, useState } from 'react';
import { UserApi } from "../apis/index";
const userApi = new UserApi();

const Follow = ({user}) => {
 
  useEffect(() => {
 
    let loginUser = JSON.parse( localStorage.getItem("user")) ; 
    user.id = loginUser.id;
    user.name = loginUser.name;
    user.email = loginUser.email;

      userApi.listFollowers(user.id, {}, (error, data, response) => {
        user.followerCount=data.total;
      });

      userApi.listFollowing(user.id,  {},(error, data, response) => {
        user.followingCount=data.total; 

        console.log(user);
      });
   
  }, [user]);


  return <div>


<div class="card text-center">
  <div class="card-header">
  <div>  { user.name } </div>
  </div>
  <div class="card-body">
  <ul className="list-group">
      <li className="list-group-item d-flex justify-content-between align-items-start">
        <div className="ms-2 me-auto">
          <div className="fw-bold">Following</div>
         
        </div>
        <span className="badge text-bg-primary rounded-pill"> { user.followingCount } </span>
      </li>
      <li className="list-group-item d-flex justify-content-between align-items-start">
        <div className="ms-2 me-auto">
          <div className="fw-bold">Follower</div>
        </div>
        <span className="badge text-bg-primary rounded-pill"> { user.followerCount }</span>
      </li>

    </ul>

  </div>
 
</div>

   


  

  </div>;
};

export default Follow;