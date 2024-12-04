import React, { useState } from 'react';
import ApiService from '../services/ApiService';
import '../styles/forms.css';
import { useNavigate } from "react-router-dom";
import SignupStyle from "../styles/SignupStyle.css"
export default function SignUp() {
    let navigate = useNavigate();
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const apiService = new ApiService();

    const [formData, setFormData] = useState({
        username: "",
        password: "",
        role: ""
    })


    let [valid, setValid] = useState(true);
    let [usernameError, setUsernameError] = useState("");
    let [passwordError, setPasswordError] = useState("");
    let [roleError, setRoleError] = useState("");
    let validation = () => {
        if (formData.username === "" || formData.username === undefined) {
            console.log("Validation failed!")
            valid = false;
            localStorage.setItem("signUpPage", "false")
            setUsernameError("Please enter username");
        }
        if (formData.password === "" || formData.password === undefined) {
            console.log("Validation failed!")
            valid = false;
            localStorage.setItem("signUpPage", "false")
            setPasswordError("Please enter password");
        }
        if (formData.role === "" || formData.role === undefined) {
            console.log("Validation failed!")
            valid = false;
            localStorage.setItem("signUpPage", "false")
            setRoleError("Please enter role");
        }
    }

    console.log(formData)
    function handleChange(event) {
        const { name, value } = event.target
        setFormData((prevState) => ({
            ...prevState,
            [name]: value
        }))
    }

    function handleCloseSignUp() {
        navigate('/')
        window.location.reload();
    }


    let signUp = (evt) => {
        evt.preventDefault();
        validation();
        if (valid === true) {
            apiService.register(formData).then(
                (res) => {
                    localStorage.setItem("justRegistered", "true")
                    localStorage.setItem("signUpPage", "false")

                    alert("Registered successfully!")
                    navigate('../')
                    window.location.reload();
                }).catch((err) => {
                    handleShow();
                    alert("Error occured during registration")
                })
        }
    }


    return (
        <div height="100vh">

            <section className="vh-200 gradient-custom">
                <div className="container py-5 h-100">
                    <div class="col-lg-6 mb-5 mb-lg-0" style={{ zIndex: "10" }}></div>
                    <div class="col-lg-6 mb-5 mb-lg-0 position-relative container">
                        <div class="card bg-glass">
                            <div class="card-body px-4 py-5 px-md-5">
                                <form onSubmit={signUp}>
                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="username">Enter your User Name</label>
                                        <input
                                            type="text"
                                            value={formData.username}
                                            name="username"
                                            id="username"
                                            placeholder="Username"
                                            onChange={handleChange}
                                            className="form-control" />
                                        <div data-testid="usrnameError" style={{ color: 'red' }}>{usernameError}</div>

                                    </div>


                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="password">Enter your Password</label>
                                        <input
                                            type="password"
                                            value={formData.password}
                                            name="password"
                                            id="password"
                                            placeholder="Password"
                                            onChange={handleChange}
                                            className='form-control'
                                        />
                                        <div data-testid="pswrdError" style={{ color: 'red' }}>{passwordError}</div>

                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label className="form-label" for="role">Enter the role</label>
                                        <select
                                            value={formData.role}
                                            name="role"
                                            id="role"
                                            placeholder="Role"
                                            onChange={handleChange}
                                            className='form-select form-select-md'>
                                            <option></option>
                                            <option>Developer</option>
                                        </select>
                                        <div data-testid="roleError" style={{ color: 'red' }}>{roleError}</div>
                                    </div>
                                    <div>
                                        <button className='btn btn-primary' style={{ marginLeft: "10px" }}>Sign up</button>
                                        <button className='btn btn-primary' style={{ marginLeft: "40px" }} onClick={handleCloseSignUp}>Cancel</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </section>



        </div>
    )
}