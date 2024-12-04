import { useEffect, useState } from "react";
import ApiService from "../../services/ApiService";
import { useNavigate } from "react-router-dom";


function EditProfile() {
    let apiService = new ApiService();
    let [currentDeveloper, setCurrentDeveloper] = useState({});
    let navigate = useNavigate();
    let [valid, setValid] = useState(true);
    let [mailError, setMailError] = useState();
    let [githubError, setGithubError] = useState();




    let [button, setButton] = useState("Add Developer");
    useEffect(() => {
        apiService.getCurrentDeveloper().then(
            (res) => {
                setCurrentDeveloper(res.data)
                setButton("Update Profile")
            }
        );
    }, [])


    let developerEmailHandler = (evt) => {
        setCurrentDeveloper((prevState) => { return { ...prevState, emailAddress: evt.target.value } });
    }
    let developerGithubHandler = (evt) => {
        setCurrentDeveloper((prevState) => { return { ...prevState, githubLink: evt.target.value } });
    }


    let validation = () => {

        if (currentDeveloper.emailAddress === "" || currentDeveloper.emailAddress === undefined) {
            valid = false;
            setMailError("Please enter email address");
        }
        if (currentDeveloper.githubLink === "" || currentDeveloper.githubLink === undefined) {
            valid = false;
            setGithubError("Please enter github link");
        }

    }

    let submitHandler = (evt) => {
        //In default, submit button will refresh the page
        //To avoid refreshing the page while submitting
        evt.preventDefault();
        validation();
        if (valid) {
            apiService.addDeveloper(currentDeveloper).then(
                (res) => {
                    localStorage.setItem("justRegistered", "false")
                    alert("Added...")
                    navigate("/profile")
                }
            );
        }
    }

    return <div className="container mode-body" align="center">
        <br></br>
        <h4>Edit Profile</h4>
        <div className="form-group mb-2">
            <div class="col-lg-6 mb-5 mb-lg-0 position-relative">
                <div class="card bg-glass">
                    <div class="card-body px-4 py-5 px-md-5">
                        <form onSubmit={submitHandler}>

                            <div data-mdb-input-init class="form-outline mb-4">
                                <label class="form-label" for="password">Enter email address:</label>
                                <input type="text" class="form-control" onChange={developerEmailHandler} value={currentDeveloper.emailAddress} placeholder="name@gmail.com" />

                                <div class="error" style={{ color: 'red' }}> {mailError}</div>

                            </div>

                            <div data-mdb-input-init class="form-outline mb-4">
                                <label className="form-label" for="role">Enter github link:</label>
                                <input type="text" class="form-control" onChange={developerGithubHandler} value={currentDeveloper.githubLink} placeholder="github.com/username" />

                                <div class="error" style={{ color: 'red' }}>{githubError}</div>

                            </div>
                            <button className='btn btn-primary' style={{ backgroundColor: 'grey' }}>{button}</button>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
}
export default EditProfile;