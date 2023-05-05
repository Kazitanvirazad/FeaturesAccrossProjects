
import { Link } from "react-router-dom";

const Buttons = () => {
    return (
        <>
            <div>
                <div>
                    <button type="button" className="btn border"><Link to="/addproject"><span>Add Project</span></Link></button>
                </div>
                <div>
                    <button type="button" className="btn border"><Link to="/addproject"><span>Add Feature</span></Link></button>
                </div>
                <div>
                    <button type="button" className="btn border"><Link to="/projectlist"><span>List Projects</span></Link></button>
                </div>
                <div>
                    <button type="button" className="btn border"><Link to="/searchprojects"><span>Search Features</span></Link></button>
                </div>
                <div>
                    {(window.location.pathname !== '/home' && window.location.pathname !== '/') && <button type="button" className="btn border"><Link to="/home"><span>Home</span></Link></button>}
                </div>
            </div>
        </>
    );
};

export default Buttons;