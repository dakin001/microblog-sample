import React, { useEffect, useState } from 'react';
import Follow from './Follow';
import { UserApi } from "../apis/index";
const userApi = new UserApi();

function MyFollow() {
  const [user, setUserData] = useState({
    name: '',
    followingCount: 0,
    followerCount: 0,
  });

  useEffect(() => {

    let loginUser = JSON.parse(localStorage.getItem("user"));
    user.id = loginUser.id;
    user.name = loginUser.name;
    user.email = loginUser.email;

    userApi.listFollowers(user.id, {}, (error, data, response) => {
      setUserData(values => ({ ...values, ['followerCount']: data.total }))
    });

    userApi.listFollowing(user.id, {}, (error, data, response) => {
      setUserData(values => ({ ...values, ['followingCount']: data.total }))
    });

  }, []);

  return <Follow user={user} />;
};

export default MyFollow;