 
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Registration from '../pages/Registration'; // Replace with your component path
import Layout from "./Layout";
import Home from "../pages/Home";
import Login from "../pages/Login";
 
const AppNavigator = () => {
  return (
    <BrowserRouter>
        <Routes>
        <Route path="/" element={<Layout />}>
            <Route index element={<Home />} />
            <Route path="signup" element={<Registration />} />
            <Route path="login" element={<Login />} />
            <Route path="*" element={<Home />} />
            </Route>
        </Routes>
        </BrowserRouter>
  );
};

export default AppNavigator;