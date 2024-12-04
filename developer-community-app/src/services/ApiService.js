import axios from "axios";
const loginUrl = "http://localhost:8080/identity-service/auth/login";
const devUrl = "http://localhost:8080/community-application/developers";
const registerUrl = "http://localhost:8080/identity-service/auth/register";
const postUrl = "http://localhost:8080/community-application/posts";
const respUrl = "http://localhost:8080/community-application/responses";
const baseSkillMapURL = "http://localhost:8080/community-application/skillMap";
const baseSkillURL = "http://localhost:8080/community-application/developers/skill";

class ApiService {
    getConfig() {
        let config = {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("token")
            }
        }
        return config;
    }
    authenticate(login) {
        return axios.post(loginUrl, login);
    }
    register(register) {
        return axios.post(registerUrl, register);
    }
    //Developer
    getAllDevelopers() {
        console.log(devUrl);
        return axios.get(devUrl, this.getConfig())
    }
    // getDeveloperById(id){
    //     return axios.get(devUrl+"/getdeveloperbyid/"+id,this.getConfig())
    // }
    getTopDevelopers() {
        return axios.get(devUrl + "/getTopDevelopers", this.getConfig())
    }
    getCurrentDeveloper() {
        return axios.get(devUrl + "/getcurrentdeveloper", this.getConfig())
    }
    addDeveloper(developer) {
        return axios.post(devUrl, developer, this.getConfig());
    }

    //SKILL MAP
    getAllSkillMap() {
        return axios.get(baseSkillMapURL, this.getConfig());
    }
    addSkillMap(skillMap) {
        return axios.post(baseSkillMapURL, skillMap, this.getConfig())
    }

    getSkillMap() {
        return axios.get(baseSkillMapURL + "/developer", this.getConfig());
    }

    getSkillMapByDeveloperId(id) {
        return axios.get(baseSkillMapURL + "/developer/" + id, this.getConfig());
    }

    getSkillsByOtherDeveloperId(id) {
        return axios.get(baseSkillMapURL + "/skills/" + id, this.getConfig());
    }

    deleteSkillMap(id) {
        return axios.delete(baseSkillMapURL + "/delete/" + id, this.getConfig());
    }

    updateSkillMap(id, skillMap) {
        return axios.put(baseSkillMapURL + '/update/' + id, skillMap, this.getConfig());
    }

    getSkillMapBySkillId(id) {
        return axios.get(baseSkillMapURL + '/' + id, this.getConfig());
    }


    //SKILL
    getAllSkill() {
        return axios.get(baseSkillURL, this.getConfig());
    }

    addSkill(skill) {
        return axios.post(baseSkillURL, skill, this.getConfig());
    }

    updateSkill(id, skill) {
        return axios.put(baseSkillURL + '/' + id, skill, this.getConfig())
    }

    getSkillById(id) {
        return axios.get(baseSkillURL + '/' + id, this.getConfig());
    }

    //RESPONSE
    getResponsesById(id) {
        return axios.get(respUrl + "/getbypostid/" + id, this.getConfig())
    }

    addResponse(response, id) {
        return axios.post(respUrl + "/addresponse/" + id, response, this.getConfig())
    }
    delResponseById(id) {
        return axios.delete(respUrl + "/" + id, this.getConfig())
    }
    getByResponseId(id) {
        return axios.get(respUrl + "/getbyresponseid/" + id, this.getConfig())
    }
    updateResponse(response, id) {
        return axios.put(respUrl + "/updateresponse/" + id, response, this.getConfig())
    }

    //POSTS

    getAllPosts() {
        return axios.get(postUrl, this.getConfig())
    }
    searchPostByKeword(keyword) {
        return axios.get(postUrl + "/searchpost/" + keyword, this.getConfig())
    }
    getPostById(post_id) {
        return axios.get(postUrl + "/getbypostid/" + post_id, this.getConfig());
    }
    getPostByDeveloper(developer_id) {
        return axios.get(postUrl + "/getbydeveloperid/" + developer_id, this.getConfig());
    }
    addNewPost(post) {
        return axios.post(postUrl, post, this.getConfig());
    }
    updatePost(post, post_id) {
        return axios.put(postUrl + "/updatepost/" + post_id, post, this.getConfig());
    }
    deletePostById(post_id) {
        return axios.delete(postUrl + "/" + post_id, this.getConfig());
    }

    //     getAllPosts(){
    //       return axios.get(postUrl,this.getConfig())
    //     }
    //     getPostById(post_id){
    //       console.log(typeof post_id);
    //       console.log(post_id);
    //       return axios.get(postUrl+"/getbypostid/"+post_id,this.getConfig());
    //   }
    //   getPostByDeveloper(developer_id)
    //     {
    //         console.log(typeof developer_id);
    //         console.log(developer_id);
    //         return axios.get(postUrl+"/getbydeveloperid/"+developer_id,this.getConfig());
    //     }
    //     addNewPost(post){
    //       alert("Adding")
    //       return axios.post(postUrl,post,this.getConfig());
    //   }
    //   updatePost( post_id, post)
    //   {
    //       console.log(typeof post_id);
    //       console.log(post_id);
    //       const updatedPost={
    //           ...post,
    //           post_id:post_id
    //       };
    //       console.log(updatedPost);
    //       return axios.put(postUrl+"/updatepost/"+post_id, updatedPost,this.getConfig());
    //   }
    //   deletePostById(post_id){
    //       console.log(typeof post_id);
    //       console.log(post_id);
    //       return axios.delete(postUrl+"/"+post_id,this.getConfig());
    //   }


}
export default ApiService;