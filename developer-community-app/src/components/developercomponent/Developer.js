import { useEffect, useState } from "react";
import ApiService from "../../services/ApiService";
import Dashboard from "./Dashboard";

function Developer() {
    let apiService = new ApiService();
    let [developer, setDeveloper] = useState([]);
    useEffect(() => {
        apiService.getDeveloperById().then(
            (res) => {
                setDeveloper(res.data)
                console.log("response is ready")
                console.log(res.data)

            },
            (err) => { console.log("Error: " + err) }
        )
    }, [])

    return <div>
        <h1>Dashboard</h1>
        {
            developer.map(d => <Dashboard dev={d} />)
        }
    </div>

}
export default Developer;