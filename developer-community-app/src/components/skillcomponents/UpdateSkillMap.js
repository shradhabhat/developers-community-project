import React from 'react';
import { useState, useEffect } from 'react';
import ApiService from '../../services/ApiService';
import { useNavigate } from 'react-router-dom';

export default function UpdateSkillMap({ id }) {

    const apiService = new ApiService();
    let navigate = useNavigate();

    const [formData, setFormData] = React.useState({
        profeciencyLevel: "",
        experienceLevel: "",
        projects: "",
    })

    let [errors, setErrors] = useState({
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




    useEffect(() => {
        if (id != 0) {
            apiService.getSkillMapBySkillId(id).then(
                (response) => {
                    setFormData(response.data)
                    console.log(response.data)
                },
                (error) => {
                    console.log("Error is" + error)
                }
            )

        }
    }, [id])

    let validation = () => {
        let valid = true;
        let newErrors = {}

        if (!formData.profeciencyLevel) {
            newErrors.profeciencyLevel = 'Profeciency Level is required'
            valid = false;
        }
        if (!formData.experienceLevel) {
            newErrors.experienceLevel = 'Expereice Level is required'
            valid = false
        }

        if (!formData.projects) {
            newErrors.projects = 'Skill Name is required'
            valid = false
        }

        setErrors(newErrors)
        return valid
    }

    console.log(formData)
    function submitHandle(event) {
        event.preventDefault();
        if (validation()) {
            apiService.updateSkillMap(id, formData).then(
                (result) => {
                    alert("Skill updated")
                    navigate('/about')
                }
            )
        }
    }

    return (
        <div >
            <form onSubmit={submitHandle} className='form-inline col-sm-4'>

                <div className='form-floating mb-5'>
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
                <div className='form-floating mb-5'>
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

                <div className='form-floating mb-5'>
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
                    {errors.projects && <span className="error" style={{ color: 'red' }}>{errors.projects}</span>}
                </div>
                <button className='btn btn-primary' style={{ backgroundColor: 'grey' }} >Update Skill</button>
            </form>
        </div>
    )
}