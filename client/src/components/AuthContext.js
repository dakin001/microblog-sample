import { useState, createContext } from "react";
const AuthContext = createContext({
    currentUser: null
});

export default AuthContext;