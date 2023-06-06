import ListFeatureProjectTableBody from "./ListFeatureProjectTableBody";
import ListFeatureProjectTableHead from "./ListFeatureProjectTableHead";
import Buttons from "./Buttons";
import { useState } from "react";
import apiServiceBaseUrl from "../apiServiceBaseUrl";
import serviceuriconfig from '../config/service-uri-config.json';

const ListFeatures = () => {
    let [projectData, setProjectData] = useState([]);

    document.title = 'Project List';

    let fetchProjectData = () => {
        let url = apiServiceBaseUrl();
        let uri = serviceuriconfig.FeatureList.uri
        let method = serviceuriconfig.FeatureList.method;
        url += uri;
        fetch(url, {
            method: method,
        }).then(response => {
            return response.json();
        }).then(data => {
            if (data && !data.error) {
                setProjectData(data.data);
            } else if (data && data.error) {
                alert(data.message);
            }
        });
    };

    return (
        <>
            <Buttons />
            <button type="button" onClick={fetchProjectData} className="btn btn-primary">Get Data</button>
            {projectData.length > 0 && <table className="table table-striped">
                <ListFeatureProjectTableHead />
                <ListFeatureProjectTableBody data={projectData}></ListFeatureProjectTableBody >
            </table>}
        </>
    );
};

export default ListFeatures;