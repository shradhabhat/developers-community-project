import { Link, Route, Routes } from "react-router-dom";
import MenuStyle from "../styles/MenuStyle.css"
import Dashboard from "./developercomponent/Dashboard";
import About from "./About";
import Developer from "./developercomponent/Developer";
import Login from "./Login";
import Logout from "./Logout";
import Profile from "./developercomponent/Profile";
import SignUp from "./SignUp";
import AddSkillMap from './skillcomponents/AddSkillMap'
import { ShowSkills } from './skillcomponents/SkillMap';
import AddProfile from "./developercomponent/AddProfile";
import EditProfile from "./developercomponent/EditProfile";
import { UserSkills } from "./skillcomponents/SkillMap";
import { Skill } from "./skillcomponents/Skill";
import AddSkill from "./skillcomponents/AddSkill";
import UpdateSkillMap from "./skillcomponents/UpdateSkillMap";
import UpdateSkills from "./skillcomponents/UpdateSkills";
import Response from "./Response";
import AddPosts from "./AddPosts";
import Posts from "./Posts";
import UpdateResponse from "./UpdateResponse";
import { useState } from "react";
import { CgDarkMode } from "react-icons/cg";
import { useNavigate } from 'react-router-dom';
import AllPosts from "./AllPosts";
function Menu() {
    // localStorage.setItem("justRegistered", "false")
    let navigate = useNavigate();
    let searchPost = (evt) => {

        navigate("post/" + evt.target.value);

    }

    const [darkMode, setDarkMode] = useState({
        forBody: false,
        forNavbar: false
    })
    function handleDarkMode() {
        setDarkMode(prevState => !prevState)
    }

    const colorBody = {
        backgroundColor: darkMode ? "#31363F" : "#FEECE2"
    }

    const colorNavbar = {
        backgroundColor: darkMode ? "#acb4ba" : "#FA7070"
    }

    return <div style={colorBody}>
        <div>
            {
                localStorage.getItem("signUpPage") !== "true" && <>
                    <nav class="navbar navbar-expand-lg" style={colorNavbar}>
                        <div class="container-fluid">
                            <div class="navbar--img">
                                <img src="/images/logo.png" alt="Developer Community" />
                            </div>

                            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>

                            {

                                localStorage.getItem("loggedIn") === "true" && localStorage.getItem("thisUser") === "Developer" && <>

                                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                            <li class="nav-item">
                                                <Link class="nav-link active" to="about">About</Link>
                                            </li>
                                            <li class="nav-item">
                                                <Link class="nav-link" to="dashboard">Dashboard</Link>
                                            </li>
                                            <li class="nav-item">
                                                <Link class="nav-link" to="post">Posts</Link>
                                            </li>

                                            <li class="nav-item dropdown">
                                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                    {localStorage.getItem("user").toUpperCase()}
                                                </a>
                                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                                    {localStorage.getItem("justRegistered") === "true" && <>
                                                        <li><Link class="dropdown-item" to="addprofile">Add Profile</Link></li>
                                                    </>
                                                    }
                                                    {localStorage.getItem("justRegistered") === "false" && <>
                                                        <li><Link class="dropdown-item" to="profile">View Profile</Link></li>
                                                    </>
                                                    }
                                                    <li><hr class="dropdown-divider"></hr></li>
                                                    <Link class="dropdown-item" to="logout">Logout</Link>
                                                </ul>
                                            </li>

                                        </ul>
                                        <div className="searchbox">
                                            <form class="d-flex">
                                                <input onChange={(evt) => searchPost(evt)} class="form-control me-2" type="search" placeholder="Search" aria-label="Search" ></input>
                                            </form>
                                        </div>

                                        <div>
                                            <CgDarkMode onClick={handleDarkMode} size={35} />
                                        </div>
                                    </div>
                                </>
                            }

                            {

                                localStorage.getItem("loggedIn") === "true" && localStorage.getItem("thisUser") === "Admin" && <>

                                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                            <li class="nav-item">
                                                <Link class="nav-link active" to="about">About</Link>
                                            </li>
                                            <li class="nav-item">
                                                <Link class="nav-link" to="dashboard">Dashboard</Link>
                                            </li>
                                            <li class="nav-item">
                                                <Link class="nav-link" to="post">Posts</Link>
                                            </li>
                                            <li class="nav-item dropdown">
                                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                    {localStorage.getItem("user").toUpperCase()}
                                                </a>
                                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">

                                                    <li><Link class="dropdown-item" to="logout">Logout</Link></li>

                                                </ul>
                                            </li>

                                        </ul>
                                        <div className="searchbox">
                                            <form class="d-flex">
                                                <input onChange={(evt) => searchPost(evt)} class="form-control me-2" type="search" placeholder="Search" aria-label="Search" ></input>
                                            </form>
                                        </div>

                                        <div>
                                            <CgDarkMode onClick={handleDarkMode} size={35} />
                                        </div>

                                    </div>
                                </>
                            }

                        </div>
                    </nav>
                </>
            }


        </div>

        <Routes>
            <Route path="/" element={<Login />}></Route>
            <Route path="/about" element={<About />}></Route>
            <Route path="/dashboard" element={<Dashboard />}></Route>
            <Route path="/developer" element={<Developer />}></Route>
            <Route path="/logout" element={<Logout />}></Route>
            <Route path="/profile" element={<Profile />}></Route>
            <Route path="/signup" element={<SignUp />}></Route>
            <Route path="/addprofile" element={<AddProfile />}></Route>
            <Route path="/profile/updatedeveloper" element={<EditProfile />}></Route>
            <Route path='/userskills' element={<UserSkills />}></Route>
            <Route path='/addSkillMap' element={<AddSkillMap />}></Route>
            <Route path='/showSkills/:id' element={<ShowSkills />}></Route>
            <Route path='/skill' element={<Skill />}></Route>
            <Route path='/addskill' element={<AddSkill />}></Route>
            <Route path='/updateskill/:id' element={<UpdateSkills />}></Route>
            <Route path='/updateskillmap/:id' element={<UpdateSkillMap />}></Route>
            {/* <Route path="/posts" element={<Post />}></Route> */}
            <Route path="/post" element={<Posts />}></Route>
            <Route path="/addPosts" element={<AddPosts />}></Route>
            <Route path="/response/:id" element={<Response />}></Route>
            <Route path="/post/:search" element={<AllPosts />}></Route>

            <Route path="/updateresponse/:id" element={<UpdateResponse />}></Route>
        </Routes>

    </div>
}
export default Menu;