import { Link } from 'react-router-dom'
import '../styles/AboutStyle.css'
function About() {
    return (
        <div id="carouselExampleControls" class="carousel carousel-light mode-body" >
            <div>
                <Link to="/skill">#BrowseTechnicalSkills</Link>
            </div>
            <br></br>
            <div class="carousel-in-ner">
                <div class="carousel-item active" >
                    <img src="favicon.ico" width="100" height="350px" align="center" />
                    <div class="blockquote">
                        <h3 >Welcome to the Developer community system,</h3>
                        <br></br>
                        <div class="b-footer">Sharing knowledge, connecting developers</div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="carousel-item active" >

                        <div class="blockquote">
                            <h3 ></h3>
                            <br></br>
                            <div class="b-footer">The aim of our community is to support developers to share knowledge and foster collaborations among developers with similar interests.</div>
                        </div>
                    </div>
                </div>

            </div>

            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    );
}

export default About;