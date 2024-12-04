import { useState, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import ApiService from '../services/ApiService';
import moment from 'moment';
import { useParams } from 'react-router-dom';


function AllPosts() {
  let [post, setPost] = useState([]);
  let apiService = new ApiService();
  let [newPost, setNewPost] = useState([]);
  let { id } = useParams();
  let { search } = useParams();


  useEffect(() => {
    if (search == "0") {
      apiService.getAllPosts().then((res) => {
        console.log(res.data)
        setPost(res.data);

      },
        (err) => { console.log("Error " + err) }
        ,);
    }
    else {
      apiService.searchPostByKeword(search).then((res) => {
        console.log(res.data)
        setPost(res.data);

      },
        (err) => { console.log("Error " + err) }
        ,);
    }
  }, []);
  let delPost = (id) => {
    apiService.deletePostById(id).then(
      (res) => alert("Deleted")

    )
  }
  let postHandler = (evt) => {
    newPost.query = evt.target.value;
    console.log(evt.target.value);
    setNewPost((p) => { return { ...p, query: evt.target.value } });
  }

  let addHandler = (evt) => {
    evt.preventDefault();
    apiService.addNewPost(newPost, id).
      then(
        (res) => {
          alert("Added ...");
        }
      );

  }

  try {
    console.log(post.developer.userName)
  } catch (e) {
    console.log('Error')
  }

  return (
    <div className='main mode-body'>
      <div className='main-container'>
        <div className='main-top' style={{ textAlign: 'center', marginLeft: '20px', marginRight: '20px' }} >
          <Link to="/addPost">
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
              }}>Ask Query</button>
          </Link>

        </div>
        {post.map(post =>
          <div class="card container" style={{ marginBottom: "30px" }}>
            <div class="card-body" align="center">
              <p class="card-text" style={{ fontSize: "30px" }}>{post.query}</p>
              <p class="card-text" style={{ fontSize: "15px" }}> posted on {moment(post.postDateTime).fromNow()} by {post.developer.userName}</p>
              <p class="card-text" style={{ fontSize: "20px" }}>Topic: {post.topic}</p>
              {/* <p class="card-text">Views: {post.noOfViews}</p> */}
              <i class="bi bi-chat-left-text"></i>
              <a href={"/Response/" + post.postId} class="btn-btn-primary">Responses</a>
            </div>

          </div>

        )};
      </div>
    </div>
  )

}

export default AllPosts