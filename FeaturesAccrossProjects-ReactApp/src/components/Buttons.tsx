
import { Link } from "react-router-dom";

const Buttons = () => {
    return (
        <>
            <div>
                <div>
                    <button type="button" className="btn border"><Link to="/addproject"><span>Add Project</span></Link></button>
                </div>
                <div>
                    <button type="button" className="btn border"><Link to="/addfeature"><span>Add Feature</span></Link></button>
                </div>
                <div>
                    <button type="button" className="btn border"><Link to="/featurelist"><span>List Features</span></Link></button>
                </div>
                <div>
                    <button type="button" className="btn border"><Link to="/search"><span>Search Features</span></Link></button>
                </div>
                <div>
                    {(window.location.pathname !== '/home' && window.location.pathname !== '/') && <button type="button" className="btn border"><Link to="/home"><span>Home</span></Link></button>}
                </div>
            </div>
        </>
    );
};

export default Buttons;