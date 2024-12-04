import { useParams } from 'react-router-dom';
import ApiService from '../../services/ApiService';
import { Link } from 'react-router-dom'
import Modal from 'react-bootstrap/Modal';
import { useState, useEffect } from 'react';
import UpdateSkillMap from './UpdateSkillMap';


//For displaying the developer skills for the developer who is logged in 
export function UserSkills() {
    let apiService = new ApiService();
    let [developerSkills, setDeveloperSkills] = useState([]);

    useEffect(() => {
        apiService.getSkillMap().then(
            (response) => {
                setDeveloperSkills(response.data);
                console.log(response.data);

            },
            (error) => {
                console.log("Error is" + error);
            }
        )
    }, [])

    function handleClick(id) {
        console.log("One developer skill was deleted")
        setDeveloperSkills((prevState) => {
            return (
                prevState.filter((ds) => ds.skillDetailId.skill.skillId !== id)
            )
        })
        apiService.deleteSkillMap(id)
        alert("Developer Skill with id " + id + " deleted")
    }
    return (
        <div class="card container" style={{ marginBottom: "40px" }}>
            <table className="table table-striped table-hover container">
                <thead>
                    <tr>
                        <th scope="col">Sl.No</th>
                        <th scope="col">Skill Name</th>
                        <th scope="col">Profeciency Level</th>
                        <th scope="col">Experience Level</th>
                        <th scope="col">Projects</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    {developerSkills.map((ds, index) => (
                        <SkillMapsCard key={ds.skillDetailId} skillmaps={ds} id={ds.skillDetailId} index={index + 1} clickHandle={handleClick} deleteSkill={true} />
                    ))}
                </tbody>
            </table>
        </div>

    )
}

//For displaying each developer skills in the dashboard
export function ShowSkills() {
    let apiService = new ApiService();
    let [developerSkills, setDeveloperSkills] = useState([]);
    let { id } = useParams()
    console.log(typeof { id })
    console.log(id)
    useEffect(() => {
        if (id !== 0) {
            apiService.getSkillMapByDeveloperId(id).then(
                (response) => {
                    setDeveloperSkills(response.data);
                    console.log(response.data);

                },
                (error) => {
                    console.log("Error is" + error);
                }
            )
        }
    }, [])
    return (<div class='container mode-body' style={{ marginTop: "40px" }}>
        <div class="card container">
            <br></br>
            <h3>Skill Details</h3>
            <br></br>
            <table className="table table-striped table-hover container">
                <thead>
                    <tr>
                        <th scope="col">Sl.No</th>
                        <th scope="col">Skill Name</th>
                        <th scope="col">Profeciency Level</th>
                        <th scope="col">Experience Level</th>
                        <th scope="col">Projects</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    {developerSkills.map((ds, index) => (
                        <SkillMapsCard key={ds.skillDetailId} skillmaps={ds} id={ds.skillDetailId} index={index + 1} deleteSkill={false} />
                    ))}
                </tbody>
            </table>
        </div>

    </div>
    )
}


// For displaying each skill Map in the table
export function SkillMapsCard(props) {

    // let apiService = new ApiService();
    let skillName = props.id.skill.skillName
    let modifiedSkillName = skillName[0].toUpperCase() + skillName.slice(1);
    const [showModal, setShowModal] = useState(false);
    const handleClose = () => setShowModal(false);
    const handleOpen = () => setShowModal(true);


    return (
        <tr>
            <th scope="row">{props.index}</th>
            <td>{modifiedSkillName}</td>
            <td>{props.skillmaps.profeciencyLevel}</td>
            <td>{props.skillmaps.experienceLevel}</td>
            <td>{props.skillmaps.projects}</td>
            {
                props.deleteSkill && <>
                    <td><i className="remIcon bi bi-trash bi-lg" onClick={() => props.clickHandle(props.id.skill.skillId)} ></i></td>
                    <td><i class="bi bi-vector-pen" onClick={handleOpen}></i></td>
                </>
            }

            <Modal show={showModal} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Update Skill</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <UpdateSkillMap key={props.id.developer.developerId} id={props.id.skill.skillId} />
                </Modal.Body>
            </Modal>

        </tr>

    );
} 