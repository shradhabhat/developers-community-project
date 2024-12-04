import React, { useEffect, useState } from 'react';
import ApiService from '../services/ApiService';
import { useNavigate, useParams } from "react-router-dom";

function AddPosts() {
  let [posts, setPosts] = useState([]);
  let { postId } = useParams();
  let navigate = useNavigate();
  let [todo, setTodo] = useState("Add Posts");
  let apiService = new ApiService();

  let postQueryHandler = (evt) => {
    setPosts((prevState) => {
      return {
        ...prevState, query: evt.target.value //... is spread operator it can combine two different arrays here it is taking both previous state and current state
      }
    });
  }

  let postTopicHandler = (evt) => {
    setPosts((prevState) => {
      return {
        ...prevState, topic: evt.target.value //... is spread operator it can combine two different arrays here it is taking both previous state and current state
      }
    });
  }

  let [valid, setValid] = useState(true);
  let [postQueryError, setPostQueryError] = useState();
  let [postTopicError, setPostTopicError] = useState();
  let validation = () => {
    if (posts.query === "" || posts.query === undefined) {
      valid = false;
      setPostQueryError("Please enter your Query");
    }

    if (posts.topic === "" || posts.topic === undefined) {
      valid = false;
      setPostTopicError("Please enter topic of the Query");
    }
  }
  let submitHandler = (evt) => {
    evt.preventDefault();
    if (valid == true) {
      apiService.addNewPost(posts).then(
        (res) => {
          alert("Post Added Successfully!!!!");//this will call automatically by promise object
          navigate("/post/0");
          window.location.reload();
        }
        , err => { alert(err.response.data.msg); console.log(err) }
      );
    }
  }
  let backHandler = () => {
    navigate("/post");
    window.location.reload();

  }
  return <div class="container">
    <form onSubmit={submitHandler}>
      <div class="form-group">
        <h4 for="exampleFormControlSelect1" >Post</h4>
        <br></br>
        <label>Enter query:</label>
        <input type="text" value={posts.query} class="form-control" id="exampleFormControlSelect1" onChange={postQueryHandler} />
        <label>Enter topic:</label>
        <input type="text" value={posts.topic} class="form-control" id="exampleFormControlSelect1" onChange={postTopicHandler} />

        <span class="error">{postQueryError}</span>
      </div><br></br>
      <button type="submit" class="btn btn-primary" >{todo} </button>
      &ensp;
      <button type="back" class="btn btn-primary" onClick={backHandler}> Back </button>
    </form>
  </div>
}
export default AddPosts;



