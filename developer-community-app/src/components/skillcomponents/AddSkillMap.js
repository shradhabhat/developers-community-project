import React from 'react';
import { useState, useEffect } from 'react';
import ApiService from '../../services/ApiService';
import { useParams, useNavigate } from 'react-router-dom'
export default function AddSkillMap() {

    const apiService = new ApiService()
    let navigate = useNavigate()
    let [skills, setSkills] = useState([])
    let [errors, setErrors] = useState({
        skillId: "",
        profeciencyLevel: "",
        experienceLevel: "",
        projects: "",
    })

    useEffect(() => {
        apiService.getAllSkill().then(
            (response) => {
                setSkills(response.data)
                console.log(response.data)

            },
            (error) => {
                console.log("Error is" + error)
            })
    }, [])


    const [formData, setFormData] = React.useState({
        profeciencyLevel: "",
        experienceLevel: "",
        projects: "",
    })


    console.log(formData)
    function handleChange(event) {
        const { name, value } = event.target
        setFormData((prevState) => ({
            ...prevState,
            [name]: value
        }))
    }

    function handleSkillChange(event) {
        const skillId = event.target.value;
        setFormData({
            ...formData,
            skillId: skillId
        })
    }
    let validation = () => {
        let valid = true;
        let newErrors = {}
        if (!formData.skillId) {
            newErrors.skillId = 'Skill Name is required'
            valid = false
        }
        if (!formData.profeciencyLevel) {
            newErrors.profeciencyLevel = 'Profeciency Level is required'
            valid = false;
        }
        if (!formData.experienceLevel) {
            newErrors.experienceLevel = 'Expereice Level is required'
            valid = false
        }

        setErrors(newErrors)
        return valid
    }



    console.log(formData)
    function submitHandle(event) {
        event.preventDefault();
        if (validation()) {
            apiService.addSkillMap(formData).then(
                (response) => {
                    alert("Skill Added")
                    navigate("/profile")
                    window.location.reload();

                }
            )
        }

    }




    return (
        <div className='container mode-body' style={{ marginTop: "40px" }}>
            {/* <h4>Add Skill</h4> */}
            <form onSubmit={submitHandle} className='form-inline col-sm-4'>

                <div className='form-floating mb-4'>
                    <select
                        name="skillId"
                        id={"floatingInput"}
                        placeholder="skillId"
                        onChange={handleSkillChange}
                        className='form-select form-select-sm'
                        aria-label="Small select example"
                    >
                        <option selected></option>
                        {skills.map((sk) => (
                            <option
                                key={sk.skillId}
                                value={sk.skillId}
                            >
                                {sk.skillName}
                            </option>
                        ))}
                    </select>
                    <label className="form-label" htmlFor={"floatingInput"}>Select from drop down</label>
                    {errors.skillId && <span className='error' style={{ color: "red" }}>{errors.skillId}</span>}
                </div>
                <div className='form-floating mb-4'>
                    <select
                        type="text"
                        value={formData.profeciencyLevel}
                        name="profeciencyLevel"
                        id={"floatingInput"}
                        placeholder="Profeciency Level"
                        onChange={handleChange}
                        className='form-select form-select-sm'
                        aria-label="Small select example"
                    >
                        <option selected></option>
                        <option>Beginner</option>
                        <option>Intermediate</option>
                        <option>Advanced</option>
                        <option>Expert</option>

                    </select>
                    <label className="form-label" htmlFor={"floatingInput"}>Profeciency Level</label>
                    {errors.profeciencyLevel && <span className="error" style={{ color: 'red' }}>{errors.profeciencyLevel}</span>}
                </div>
                <div className='form-floating mb-4'>
                    <select
                        type="text"
                        value={formData.experienceLevel}
                        name="experienceLevel"
                        id={"floatingInput"}
                        placeholder="experienceLevel"
                        onChange={handleChange}
                        className='form-select form-select-sm'
                        aria-label="Small select example"
                    >
                        <option selected></option>
                        <option>Entry-level</option>
                        <option>Mid-Level</option>
                        <option>Senior-Level</option>
                        <option>Lead-Level</option>
                    </select>
                    <label className="form-label" htmlFor={"floatingInput"}>Expereience level</label>
                    {errors.experienceLevel && <span className="error" style={{ color: 'red' }}>{errors.experienceLevel}</span>}

                </div>

                <div className='form-floating mb-4'>
                    <input
                        type="text"
                        value={formData.projects}
                        name="projects"
                        id={"floatingInput"}
                        placeholder="projects"
                        onChange={handleChange}
                        className='form-control'
                    ></input>
                    <label className="form-label" htmlFor={"floatingInput"}>Projects</label>
                </div>


                <button className='btn btn-primary' style={{ backgroundColor: 'grey' }}>Add Skill</button>
            </form>
        </div>
    )
}