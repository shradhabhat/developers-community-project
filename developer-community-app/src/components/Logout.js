import { useNavigate } from "react-router-dom";

function Logout() {
    let navigate = useNavigate();
    let logout = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("role");
        localStorage.removeItem("user");
        localStorage.setItem("loggedIn", "false");
        alert("Logged out");
        navigate("../");
        window.location.reload();

    }
    return <div className="mode-body" style={{ paddingTop: '150px' }}>
        <br></br>
        {
            <h4 color="black">Are you sure you want to logout?
                <br></br>
                <button type="submit" class="btn btn-primary" onClick={logout} style={{ marginTop: "40px", backgroundColor: 'grey' }}>Logout</button>
            </h4>
        }
    </div>
}
export default Logout;