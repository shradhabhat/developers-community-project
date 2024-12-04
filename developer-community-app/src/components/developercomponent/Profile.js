import { useEffect, useState } from "react";
import ApiService from "../../services/ApiService";
import ProfileStyle from "../../styles/ProfileStyle.css"
import { UserSkills } from "../skillcomponents/SkillMap";
import { Link } from "react-router-dom";
function Profile() {
    let apiService = new ApiService();
    let [currentDeveloper, setCurrentDeveloper] = useState([]);
    let [showSkillMap, setShowSkillMap] = useState(false)
    useEffect(() => {
        apiService.getCurrentDeveloper().then(
            (res) => {
                setCurrentDeveloper(res.data)
                console.log("response is ready")
                console.log(res.data)
            },
            (error) => { console.log("Error: " + error) }
        )
    }, [])

    function toggleShowSkillMap() {
        setShowSkillMap(!showSkillMap)
    }


    return <div class="mode-body">
        <div class="container mt-4 mb-4 p-3 d-flex justify-content-center">
            <div class="card p-4">
                <div class=" image d-flex flex-column justify-content-center align-items-center">
                    <button class="btn btn-secondary">
                        <img src="images/developer.jpg" width="100px" align="center" height="100" />
                    </button>
                    <span class="name mt-3">{currentDeveloper.userName}</span>
                    <div class="d-flex flex-row justify-content-center align-items-center gap-2">
                        <span class="idd1"><b>Email Address: </b>{currentDeveloper.emailAddress}</span> <span><i class="fa fa-copy"></i></span>
                    </div>
                    <div class="d-flex flex-row justify-content-center align-items-center mt-3">
                        <span class="number"><b>Github Link: </b>{currentDeveloper.githubLink}</span>
                    </div>
                    <div class=" d-flex mt-2">
                        <button class="btn1 btn-blue"><Link to="updatedeveloper">Edit Profile</Link></button>
                    </div>

                    <div class=" px-2 rounded mt-4 date ">
                        <span class="join">Joined {currentDeveloper.memberSince}</span>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <button onClick={toggleShowSkillMap}>{showSkillMap ? "Hide Skills" : "Show Skills"}</button>
            <button style={{ marginLeft: "25px" }}><Link className='nav--link' to='/addSkillMap' textColor="black">Add Skill</Link></button>
            <br></br>
            {
                showSkillMap && <UserSkills onClose={toggleShowSkillMap} />
            }
        </div>
    </div>
}
export default Profile;