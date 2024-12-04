
import React, { useEffect, useState } from 'react';
import ApiService from '../services/ApiService';
import { Link } from 'react-router-dom';

function Posts() {
  let apiService = new ApiService();
  let [posts, setPosts] = useState([]);
  //   let [showAddPosts, setShowAddPosts] = React.useState(false);
  //let n="images/"+categories.categoryName+".jpg";

  useEffect(() => {
    apiService.getAllPosts().then(
      (res) => {
        //console.log("Response is Ready...");
        console.log(res.data);
        setPosts(res.data);
        console.log(posts);
      },
      (err) => { console.log("Error " + err) }
    );
  }, []);
  let deleteHandler = (postId) => {
    if (postId !== 0) {
      apiService.deletePostById(postId).then
        (
          (res) => {
            alert("deleted " + postId);
          }
        );
    }
  }

  return <div className='mode-body'>
    <div className='container'>
      <div className='main-top' style={{ textAlign: 'center', marginLeft: '20px', marginRight: '20px' }} >
        <Link to="/addPosts">
          <button style={
            {
              backgroundColor: 'grey',
              color: '#fff',
              padding: '15px',
              border: '2px solid #007cd446',
              outline: 'none',
              borderRadius: '3px',
              cursor: 'pointer',
              fontSize: '10px',
              marginTop: "30px",
              marginBottom: "30px"
            }} >Ask Query</button>
        </Link>
      </div>

      {posts.map(p =>
        <div class="card container" style={{ marginBottom: "30px" }}>
          <div class="card-body">
            <p class="card-text" style={{ fontSize: "30px" }}>{p.query}</p>
            <p class="card-text" style={{ fontSize: "15px" }}> posted on {p.postDateTime} by {p.developer.userName}</p>
            <p class="card-text" style={{ fontSize: "20px" }}>Topic: {p.topic}</p  >
            {/* <p class="card-text" style={{ fontSize: "20px" }}>Views: {p.noOfViews}</p> */}
            <i class="bi bi-chat-left-text"></i>
            <a href={"/Response/" + p.postId} class="btn-btn-primary">Responses</a>
          </div>
        </div>
      )}
    </div>
  </div>
}

export default Posts;