import { useEffect, useState } from 'react';
import ApiService from '../../services/ApiService'
import Modal from 'react-bootstrap/Modal';
import { Button } from "react-bootstrap";
import { Link } from 'react-router-dom';

function Dashboard(props) {
    let apiService = new ApiService();
    let [developer, setDeveloper] = useState([]);
    const [showModal, setShowModal] = useState(false);
    let [skill, setSkill] = useState([])
    const handleClose = () => setShowModal(false);

    useEffect(() => {
        apiService.getTopDevelopers().then(
            (res) => {
                setDeveloper(res.data);
                console.log(developer)
            },
            (err) => { console.log("Error: " + err) }
        )
    }, []);

    const [id, setId] = useState(0);
    function handleChangeId(newId) {
        setId(newId)
        setShowModal(true)
    }

    useEffect(() => {
        if (id !== 0) {
            apiService.getSkillsByOtherDeveloperId(id).then(
                (response) => {
                    setSkill(response.data);
                    console.log(response.data)
                },
                (error) => {
                    console.log("Error is" + error)
                }

            )
        }
    }, [id])

    let rank = 0;
    return <div class='container mode-body' style={{ marginTop: "40px" }}>
        <div class="card container">
            <h5 style={{ marginBottom: "50px" }}>DASHBOARD</h5>
            <table class="table table-striped table-hover container">
                <thead>
                    <tr>
                        <th>Rank</th>
                        <th>Developer Name</th>
                        <th>Email Address</th>
                        <th>Reputation Point</th>
                        <th>Skill</th>
                    </tr>
                </thead>
                {developer.map(dev =>
                    <tbody>
                        <tr>
                            <th>{++rank}</th>
                            <td>{dev.userName.toUpperCase()}</td>
                            <td>{dev.emailAddress}</td>
                            <td>{dev.reputationPoints}</td>
                            <td><button variant='primary' onClick={() => handleChangeId(dev.developerId)} >Open Skills</button></td>
                        </tr>
                    </tbody>

                )}
                <Modal show={showModal} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Skills</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        {
                            skill.map((s, index) => (
                                <div key={s.id}>{index + 1}) {s.skillName}</div>
                            ))}
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose}>
                            Close
                        </Button>
                        <Button variant="secondary" onClick={handleClose}>
                            <Link to={'/showskills/' + id} ><span style={{ color: "white" }}>See More</span></Link>
                        </Button>
                    </Modal.Footer>
                </Modal>
            </table>
        </div>
    </div>
}
export default Dashboard;