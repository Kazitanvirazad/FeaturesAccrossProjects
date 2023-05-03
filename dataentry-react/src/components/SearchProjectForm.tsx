import { useState } from "react";
import apiServiceBaseUrl from "../apiServiceBaseUrl";
import serviceuriconfig from '../config/service-uri-config.json';
import ListProjectTableView from "./ListProjectTableView";
import Buttons from "./Buttons";

const SearchProjectForm = () => {
    let [input, setInput] = useState('');
    let [resData, setResData] = useState([]);
    let handleChange = (event: any) => {
        const value = event.target.value;
        setInput(value);
    };

    let onFormSubmit = (event: any) => {
        event.preventDefault();

        let url = apiServiceBaseUrl();
        let uri = serviceuriconfig.searchbykeyword.uri;
        let method = serviceuriconfig.searchbykeyword.method;
        url += uri + '?keyword=' + input;
        fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json",
            }
        }).then(response => {
            return response.json();
        }).then(data => {
            if (data && !data.error) {
                setResData(data.data);
            } else if (data && data.error) {
                alert(data.errorMsg);
                setResData([]);
            }
        });
    };

    return (
        <>
            <Buttons />
            <div className="form">
                <form id='search-project-form' onSubmit={onFormSubmit} action="/searchprojectdata" method="get">
                    <div className="mb-3">
                        <label className="form-label" htmlFor="searchkeyword">Project Name</label>
                        <input name="searchkeyword" className="form-control" type="text" id="searchkeyword" defaultValue="" value={input} onChange={handleChange} required />
                    </div>
                    <div>
                        <input className="btn btn-primary" type="submit" />
                    </div>
                </form>
            </div>
            <div className="result-table">
                {resData.length > 0 && <table className="table table-striped">
                    <thead>
                        <tr>
                            <th><span>Project Name</span></th>
                            <th><span>Contact Point</span></th>
                            <th><span>Artifact Details</span></th>
                            <th><span>Tools & Platform</span></th>
                            <th><span>Used In year</span></th>
                            <th><span>Practice</span></th>
                            <th><span>Domain</span></th>
                            <th><span>Sector</span></th>
                            <th><span>Category</span></th>
                            <th><span>Sub Category</span></th>
                            <th><span>Feature Short Name</span></th>
                            <th><span>Feature Description</span></th>
                        </tr>
                    </thead>
                    <tbody>
                        <ListProjectTableView data={resData}></ListProjectTableView >
                    </tbody>
                </table>}
            </div>
        </>
    );
};

export default SearchProjectForm;