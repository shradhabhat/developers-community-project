import ApiService from '../../services/ApiService';
import React from 'react';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom'
import '../../styles/Skill.css'

export function Skill() {
    let apiService = new ApiService();
    let [skills, setSkills] = useState([]);
    let navigate = useNavigate();

    useEffect(() => {
        apiService.getAllSkill().then(
            (response) => {
                setSkills(response.data);
                console.log(response.data);
            },
            (error) => {
                console.log("Error is" + error);
            }
        )
    }, [])


    return (

        <div className='container mode-body'>
            <h2 style={{ marginBottom: "40px" }}>Technical Skills</h2>

            <div className="row">
                <div className="maincontainer col-md-12">
                    {
                        skills.map((s) => {
                            return (
                                <SkillCard key={s.skillId} skills={s} id={s.skillId} />
                            )
                        })
                    }
                </div>
                <div>


                    {
                        localStorage.getItem("role") === "Admin" && <>
                            <button style={{ marginTop: "40px", marginBottom: "40px" }}><Link to='/addskill'>Add Skill</Link></button>
                        </>
                    }
                </div>
            </div>


        </div>
    )
}


export function SkillCard(props) {
    const actualSkillName = props.skills.skillName;
    const modifiedSkillName = actualSkillName[0].toUpperCase() + actualSkillName.slice(1)
    const strings = props.skills.dependencySkill.split(",")
    console.log(strings[0])

    return (

        <div class="skillcard card mb-3" >

            <div class="row g-0">
                <div class="col-md-4" style={{ marginTop: '25px' }}>

                    <img src={`/images/${props.skills.skillName}.jpg`} alt={`${props.skills.skillName}`} class="img-fluid rounded-start" style={{ height: "170px", width: "140px" }} />
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">{modifiedSkillName}</h5>
                        <p class="card-text"><i><b>Description:</b></i>  {props.skills.skillDescription}</p>
                    </div>
                    <div>

                        <i><b>Pre-Requisites:</b></i>
                        {
                            strings.map((st, index) => ` ${index + 1})${st}`)
                        }
                    </div>
                    <div style={{ marginTop: '60px', marginLeft: '260px' }} >
                        {
                            localStorage.getItem("role") === "Admin" && <>
                                <Link to={'/updateskill/' + props.id} ><i class="bi bi-pencil-square"></i></Link>
                            </>
                        }
                    </div>
                </div>
            </div>
        </div>

    );
} 