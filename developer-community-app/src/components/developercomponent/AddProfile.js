import { useEffect, useState } from "react";
import ApiService from "../../services/ApiService";
import { useNavigate } from "react-router-dom";


function AddProfile() {
    let apiService = new ApiService();
    let [developers, setDevelopers] = useState({});
    let navigate = useNavigate();
    let [valid, setValid] = useState(true);
    let [nameError, setNameError] = useState();
    let [mailError, setMailError] = useState();
    let [githubError, setGithubError] = useState();
    let [dateError, setDateError] = useState();




    let [button, setButton] = useState("Add");
    useEffect(() => {

    })

    let developerNameHandler = (evt) => {
        setDevelopers((prevState) => { return { ...prevState, userName: evt.target.value } });
    }
    let developerEmailHandler = (evt) => {
        setDevelopers((prevState) => { return { ...prevState, emailAddress: evt.target.value } });
    }
    let developerGithubHandler = (evt) => {
        setDevelopers((prevState) => { return { ...prevState, githubLink: evt.target.value } });
    }
    // let developerDateHandler = (evt) => {
    //     setDevelopers((prevState) => { return { ...prevState, memberSince: evt.target.value } });
    // }

    let validation = () => {

        if (developers.userName === "" || developers.userName === undefined) {
            valid = false;
            setNameError("Please enter display name");
        }
        if (developers.emailAddress === "" || developers.emailAddress === undefined) {
            valid = false;
            setMailError("Please enter email address");
        }
        if (developers.githubLink === "" || developers.githubLink === undefined) {
            valid = false;
            setGithubError("Please enter github link");
        }
        // if (developers.memberSince === "" || developers.memberSince === undefined) {
        //     valid = false;
        //     setDateError("Please select today's date");
        // }
    }

    let submitHandler = (evt) => {
        //In default, submit button will refresh the page
        //To avoid refreshing the page while submitting
        evt.preventDefault();
        validation();
        if (valid) {
            apiService.addDeveloper(developers).then(
                (res) => {
                    localStorage.setItem("justRegistered", "false")
                    alert("Added...")
                    navigate("/dashboard")
                    window.location.reload();

                }
            );
        }
    }

    return <div className="container" align="center">
        <h4>Add Profile</h4>

        <div class="col-lg-6 mb-5 mb-lg-0 position-relative">

            <div class="card bg-glass">
                <div class="card-body px-4 py-5 px-md-5">
                    <form onSubmit={submitHandler}>


                        <div data-mdb-input-init class="form-outline mb-4">
                            <label class="form-label" for="username">Enter your display name:</label>
                            <input type="text" class="form-control" onChange={developerNameHandler} placeholder="Display Name" />

                            <div class="error" style={{ color: 'red' }}> {nameError}</div>

                        </div>


                        <div data-mdb-input-init class="form-outline mb-4">
                            <label class="form-label" for="password">Enter email address:</label>
                            <input type="text" class="form-control" onChange={developerEmailHandler} placeholder="name@gmail.com" />

                            <div class="error" style={{ color: 'red' }}> {mailError}</div>

                        </div>

                        <div data-mdb-input-init class="form-outline mb-4">
                            <label className="form-label" for="role">Enter github link:</label>
                            <input type="text" class="form-control" onChange={developerGithubHandler} placeholder="github.com/username" />

                            <div class="error" style={{ color: 'red' }}>{githubError}</div>

                        </div>
                        <button className='btn btn-primary'>{button}</button>
                    </form>



                    {/*             
            <form onSubmit={submitHandler}>

                <label class="form-label">
                    <b>Enter your display name:</b>
                    <input type="text" class="form-control" onChange={developerNameHandler} placeholder="Display Name" />
                </label>
                <div class="error" style={{color:'pink'}}> {nameError}</div>
                <br></br>
                <label class="form-label">
                    <b>Enter email address:</b>
                    <input type="text" class="form-control" onChange={developerEmailHandler} placeholder="name@gmail.com" />
                </label>
                <div class="error" style={{color:'pink'}}> {mailError}</div>
                <br></br>
                <label class="form-label">
                    <b>Enter github link:</b>
                    <input type="text" class="form-control" onChange={developerGithubHandler} placeholder="github.com/username" />
                </label>
                <div class="error" style={{color:'pink'}}>{githubError}</div>
                <br></br>
                {/* <label class="form-label">
                    <b>Select Today's Date:</b>
                    <input type="date" class="form-control" onChange={developerDateHandler} placeholder="Select today's date" />
                </label>
                <div class="error"> {dateError}</div>
                <br></br> */}
                    {/* <button type="submit" class="btn btn-primary mb-2" >{button}</button>
            </form> */}
                    {/* */}
                </div>
            </div>
        </div>
    </div>
}
export default AddProfile;