import React, { useEffect, useState } from 'react';
import ApiService from '../services/ApiService';
import { Navigate, useNavigate, useParams } from "react-router-dom";
function UpdateResponse() {
  let apiService = new ApiService();
  let [response, setResponse] = useState([]);
  let [newResponse, setNewResponse] = useState({});
  let navigate = useNavigate();
  let { id } = useParams();
  let [answerError, SetAnswerError] = useState("");
  let [valid, setValid] = useState(true);

  let validation = () => {
    if (newResponse.answer === "" || newResponse.answer === undefined) {
      console.log("Validation failed!")
      valid = false;
      SetAnswerError("Please enter your response");
    }
  }
  useEffect(() => {
    if (id != 0) {
      apiService.getByResponseId(id).
        then((res) => {
          setResponse(res.data)
          console.log(res.data);
        }, (err) => { console.log("Error" + err) }
        );
    }
  }, []);
  let responseHandler = (evt) => {
    newResponse.answer = evt.target.value;
    console.log(newResponse);
    setNewResponse((r) => { return { ...r, answer: evt.target.value } });
  }

  let editHander = (evt) => {
    evt.preventDefault();
    validation();
    if (valid === true) {
      apiService.updateResponse(newResponse, id).then(
        (res) => {
          alert("Response updated successfully!")
          navigate("../Response/" + response.post.postId);
          window.location.reload();
        }).catch((err) => {
          alert("Error occured during adding response")
        })

    }


  }
  return <div>
    <h4>Update Response</h4>
    <div class="row d-flex justify-content-center">
      <div class="col-md-8 col-lg-6">
        <div class="card-body p-4">
          <div data-mdb-input-init class="form-outline mb-4">
            <input type="text" id="addANote" class="form-control" defaultValue={response.answer} onChange={responseHandler} />
            <br></br>
            <button type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-sm" onClick={editHander}>Post</button>

            <button type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-primary btn-sm">Cancel</button>
          </div>
        </div>
      </div>
    </div>
  </div>
}
export default UpdateResponse;