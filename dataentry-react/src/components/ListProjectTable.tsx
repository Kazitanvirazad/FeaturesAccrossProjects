import ListProjectTableView from "./ListProjectTableView";
import Buttons from "./Buttons";
import { useState } from "react";
import apiServiceBaseUrl from "../apiServiceBaseUrl";
import serviceuriconfig from '../config/service-uri-config.json';

const ListProjectTable = () => {
    let [projectData, setProjectData] = useState([]);

    document.title = 'Project List';

    let fetchProjectData = () => {
        let url = apiServiceBaseUrl();
        let uri = serviceuriconfig.listData.uri
        let method = serviceuriconfig.listData.method;
        url += uri;
        fetch(url, {
            method: method,
        }).then(response => {
            return response.json();
        }).then(data => {
            if (data && !data.error) {
                setProjectData(data.data);
            } else if (data && data.error) {
                alert(data.errorMsg);
            }
        });
    };

    return (
        <>
            <Buttons />
            <button type="button" onClick={fetchProjectData} className="btn btn-primary">Get Data</button>
            {projectData.length > 0 && <table className="table table-striped">
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
                    <ListProjectTableView data={projectData}></ListProjectTableView >
                </tbody>
            </table>}
        </>
    );
};

export default ListProjectTable;