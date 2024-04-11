
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Registration from '../pages/Registration'; // Replace with your component path
import Layout from "./Layout";
import Home from "../pages/Home";
import Login from "../pages/Login";
import Logout from "../pages/Logout";
import UserPage from "../pages/UserPage";
import PostPage from "../pages/PostPage";

function AppNavigator() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="feed" element={<PostPage />} />
          <Route path="users" element={<UserPage />} />
          <Route path="login" element={<Login />} />
          <Route path="logout" element={<Logout />} />
          <Route path="signup" element={<Registration />} />
          <Route path="*" element={<PostPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default AppNavigator;