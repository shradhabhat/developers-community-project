import { useEffect, useState } from "react"
import ApiService from "../services/ApiService";
import { useParams } from "react-router-dom";
import ResponseStyle from "../styles/ResponseStyle.css";
import moment from "moment";
import { Navigate, useNavigate } from "react-router-dom";
function Response() {
  let apiService = new ApiService();
  let [response, setResponse] = useState([]);
  let [newResponse, setNewResponse] = useState([]);
  let { id } = useParams();
  let [currentDeveloper, setCurrentDeveloper] = useState([]);
  let [del, setDel] = useState(false);
  let [post, setPost] = useState({});
  let [valid, setValid] = useState(true);
  let [answerError, SetAnswerError] = useState("");
  let navigate = useNavigate();

  let validation = () => {
    if (newResponse.answer === "" || newResponse.answer === undefined) {
      console.log("Validation failed!")
      valid = false;
      SetAnswerError("Please enter your response");
    }
  }
  useEffect(() => {
    apiService.getCurrentDeveloper().then(
      (res) => {
        setCurrentDeveloper(res.data)
        setDel(false);
        console.log("current developer")
        console.log(res.data)
      },
      (error) => { console.log("Error: " + error) }
    )
  }, [])
  useEffect(() => {
    if (id != 0) {
      apiService.getResponsesById(id).
        then((res) => {
          setResponse(res.data)
          console.log("responses for post");
          console.log(res.data);
        }, (err) => { console.log("Error" + err) }
        );
    }
  }, []);
  useEffect(() => {
    if (id != 0) {
      apiService.getPostById(id).
        then((res) => {
          setPost(res.data)
          console.log("post..");
          //console.log(res.data);
          console.log(post);

        }, (err) => { console.log("Error" + err) }
        );
    }
  }, []);

  let delResponse = (id) => {
    apiService.delResponseById(id).then(
      (res) => {
        alert("Deleted")
        window.location.reload();
      }

    )
  }
  let responseHandler = (evt) => {
    newResponse.answer = evt.target.value;
    console.log(evt.target.value);
    setNewResponse((r) => { return { ...r, answer: evt.target.value } });
  }

  let addHander = (evt) => {
    evt.preventDefault();
    validation();
    if (valid === true) {
      apiService.addResponse(newResponse, id).then(
        (res) => {
          alert("Response added successfully!")
          window.location.reload();
        }).catch((err) => {
          //handleShow();
          alert("Error occured during adding response")
        })
    }
  }

  try {
    console.log(post.developer.userName)
  } catch (e) {
    console.log('Error')
  }


  return <div>

    <div class="container-fluid mt-100">
      <div class="row">
        <div class="col-md-12">
          <div class="card mb-4">
            <div class="card-header">
              <div class="media flex-wrap w-100 align-items-center">
                <br></br>
                <p class="text-sm" ><span class="op-8">Posted</span> by,<a class="text-black" href="#"> {post?.developer?.userName}</a></p>


                <div class="text-muted small ml-3">

                </div>
              </div>
            </div>
            <div class="card-body" align="left">
              <p> {post.query} </p>
            </div>
            <div class="card-footer d-flex flex-wrap justify-content-between align-items-center px-0 pt-0 pb-3">
              <div class="px-4 pt-3"> <p class="text-muted d-inline-flex align-items-center align-middle">  <span class="align-middle"><p class="text-black">Posted {moment(post.postDateTime).fromNow()}</p></span> </p> <span class="text-muted d-inline-flex align-items-center align-middle ">  </span> </div>
            </div>
          </div>
        </div>
      </div>
    </div>




    <div class="row d-flex justify-content-center">
      <div class="col-md-8 col-md-12">
        <div class="card-body p-4">
          <div data-mdb-input-init class="form-outline mb-4">
            <input type="text" id="addANote" class="form-control" value={response.answer} placeholder="Type your Response..." onChange={responseHandler} />

            <div data-testid="answerError">{answerError}</div>
            <br></br>
            <button type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-sm" onClick={addHander} style={{ backgroundColor: 'grey' }}>Post</button>
            {/* <button type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-primary btn-sm">Cancel</button> */}
          </div>
        </div>
      </div>
    </div>

    <div className="container">
      {
        response.map((r) =>
          <div class="card row-hover pos-relative py-3 px-3 mb-5">

            {
              (JSON.stringify(currentDeveloper.developerId) === JSON.stringify(r.developer.developerId) || localStorage.getItem("thisUser") == "Admin") && <>
                <div class="card-body">
                  <div class="dropdown ms-auto" align="right">
                    <i class="bi bi-three-dots-vertical" data-bs-toggle="dropdown" aria-expanded="false"></i>
                    <ul class="dropdown-menu">
                      {localStorage.getItem("thisUser") !== "Admin" && <>
                        <li>
                          <span class="dropdown-item">
                            <a href={"/UpdateResponse/" + r.responseId} class="card-link"><i class="bi bi-pen"> update</i></a>
                          </span>
                        </li>
                      </>
                      }
                      <li>
                        <span class="dropdown-item">
                          <i class="remIcon bi bi-trash" onClick={() => { delResponse(r.responseId); setDel(true); }}></i> Delete
                        </span>
                      </li>
                    </ul>
                  </div>
                </div>

              </>
            }
            <div class="row align-items-center">
              <div class="col-md-8 mb-3 mb-sm-0">

                <h5>
                  <p class="text-primary" align="left">{r.answer}</p>
                </h5>
                <br></br>
                <br></br>

                <p class="text-sm" ><span class="op-8">Answered</span> <span class="text-black"> {moment(r.respDateTime).fromNow()}</span><span class="op-6"> by</span> <a class="text-black" href="#">{r.developer.userName}</a></p>
              </div>
              <div class="col-md-4 op-7">
                <div class="text-muted ">

                </div>

              </div>
            </div>
          </div>
        )
      }
    </div>
  </div>
}
export default Response;