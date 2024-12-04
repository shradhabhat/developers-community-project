import React from 'react';
import { useState } from 'react';
import ApiService from '../../services/ApiService';
import '../../styles/forms.css'
import { useNavigate, useParams } from "react-router-dom";
import '../../styles/AddSkillStyle.css';
export default function AddSkill() {
    const apiService = new ApiService();
    let navigate = useNavigate();

    let [errors, setErrors] = useState({
        skillName: "",
        skillDescription: "",
        dependencySkill: ""
    })

    const [formData, setFormData] = useState({
        skillName: "",
        skillDescription: "",
        dependencySkill: "",
    })

    let { id } = useParams()

    console.log(formData)
    function handleChange(event) {
        const { name, value } = event.target
        setFormData((prevState) => ({
            ...prevState,
            [name]: value
        }))
    }

    let validation = () => {
        let valid = true;
        const newErrors = {};
        if (!formData.skillName) {
            newErrors.skillName = 'Skill Name is required'
            valid = false
        }
        if (!formData.skillDescription) {
            newErrors.skillDescription = 'Skill Description is required'
            valid = false
        }

        if (!formData.dependencySkill) {
            newErrors.dependencySkill = 'Dependency Skill is required'
            valid = false
        }

        setErrors(newErrors);
        return valid;

    }

    console.log(id)
    console.log(formData)

    function submitHandle(event) {
        event.preventDefault();
        if (validation()) {
            apiService.addSkill(formData).then(
                (response) => {
                    alert("Added...")
                    navigate("/skill")
                    window.location.reload();
                })
        }
    }



    return (
        <div className='mode-body container' style={{ marginTop: "40px" }}>
            <form onSubmit={submitHandle} className='form-inline col-sm-4'>
                <div className='form-floating mb-5'>
                    <input
                        type="text"
                        name="skillName"
                        value={formData.skillName}
                        id="floatingInput"
                        placeholder="skill Name"
                        onChange={handleChange}
                        className='form-control'
                    ></input>
                    <label className="form-label" htmlFor="floatingInput">Name</label>
                    {errors.skillName && <span className="error" style={{ color: 'red' }}>{errors.skillName}</span>}
                </div>
                <div className='form-floating description mb-5'>
                    <textarea
                        // type="text"

                        name="skillDescription"
                        value={formData.skillDescription}
                        id={"floatingInput"}
                        placeholder="Description"
                        onChange={handleChange}
                        className='form-control h-25'
                        rows='5'
                    ></textarea>
                    <label className="form-label" htmlFor={"floatingInput"}>Description</label>
                    {errors.skillDescription && <span className="error" style={{ color: 'red' }}>{errors.skillDescription}</span>}
                </div>
                <div className='form-floating mb-5'>
                    <input
                        type="text"
                        name="dependencySkill"
                        value={formData.dependencySkill}
                        id={"floatingInput"}
                        placeholder="Dependencies"
                        onChange={handleChange}
                        className='form-control'
                    ></input>
                    <label className="form-label" htmlFor={"floatingInput"}>Dependencies</label>

                    {errors.dependencySkill && <span className="error" style={{ color: 'red' }}>{errors.dependencySkill}</span>}
                </div>
                <button className='btn btn-primary' style={{ backgroundColor: 'grey' }}>Add Skill</button>
            </form>
        </div>
    )
}