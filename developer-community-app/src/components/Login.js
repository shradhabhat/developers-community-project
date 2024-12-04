import ApiService from '../services/ApiService';
import React from 'react';
import Modal from 'react-bootstrap/Modal';
import { Button } from "react-bootstrap";

import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import LoginStyle from '../styles/LoginStyle.css'



function Login() {

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);


  let [login, setLogin] = useState({});
  let navigate = useNavigate();
  let userNameHandler = (evt) => {
    login.username = evt.target.value;
  }
  let passwordHandler = (evt) => {
    login.password = evt.target.value;
  }
  let [valid, setValid] = useState(true);
  let [usernameError, setUsernameError] = useState("");
  let [passwordError, setPasswordError] = useState("");
  let validation = () => {
    if (login.username === "" || login.username === undefined) {
      console.log("Validation failed !!!!")
      valid = false;
      setUsernameError("Please enter username");
    }
    if (login.password === "" || login.password === undefined) {
      valid = false;
      setPasswordError("Please enter password");
    }
  }
  let signIn = (evt) => {
    evt.preventDefault();
    validation();
    if (valid === true) {
      new ApiService().authenticate(login).then(
        (res) => {
          // console.log(res.data);
          localStorage.setItem("token", res.data.token);
          localStorage.setItem("role", res.data.role);
          localStorage.setItem("user", login.username);
          localStorage.setItem("loggedIn", "true");
          if (res.data.role === "admin" || res.data.role === "Admin") {
            localStorage.setItem("thisUser", "Admin")
          } else if (res.data.role === "developer" || res.data.role === "Developer") {
            localStorage.setItem("thisUser", "Developer")
          }
          navigate("../about");
          window.location.reload();

        }).catch((err) => {
          handleShow();
          // alert("Invalid credentials " + err.response.status)
        });

    }
  }

  return (
    <div>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Invalid Credentials</Modal.Title>
        </Modal.Header>
        <Modal.Body>Please check username and password</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Ok
          </Button>

        </Modal.Footer>
      </Modal>
      <section className="vh-200 gradient-custom">
        <div className="container py-5 h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-8 col-lg-6 col-xl-5">
              <div className="card bg-light text-black" style={{ borderRadius: "1rem" }}>
                <div className="card-body p-5 text-center">

                  <div className="mb-md-5 mt-md-4 pb-5">

                    <form onSubmit={signIn}>

                      <div data-mdb-input-init className="form-outline form-white mb-4" >
                        <label className="form-label "></label>
                        <input
                          className="form-control form-control-lg"
                          type="text"
                          onChange={userNameHandler}
                          placeholder="Enter username" >
                        </input>
                        <div data-testid="usrnameError">{usernameError}</div>
                      </div>

                      <div data-mdb-input-init className="form-outline form-white mb-4">
                        <label className="form-label"></label>
                        <input
                          className="form-control form-control-lg"
                          type="password"
                          onChange={passwordHandler}
                          placeholder="Enter password" >
                        </input>
                        <div data-testid="usrnameError">{passwordError}</div>
                      </div>
                      <div>
                        <button data-mdb-button-init data-mdb-ripple-init className="btn btn-outline-dark btn-lg px-5" type="submit">Login</button>

                      </div>
                    </form>



                  </div>

                  <div>
                    <p className="mb-0">Don't have an account? <Link to='/signup'>SignUp</Link>
                    </p>
                  </div>

                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  )

}
export default Login;