import serviceuriconfig from '../config/service-uri-config.json';
import apiServiceBaseUrl from '../apiServiceBaseUrl';
import { useState } from 'react';

const GetProjectList = () => {
    let [optionData, setOptionData] = useState([]);
    let url = apiServiceBaseUrl();
    let uri = serviceuriconfig.ProjectList.uri;
    let method = serviceuriconfig.ProjectList.method;
    url += uri;

    if (optionData.length < 1) {
        fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json",
            }
        }).then(response => {
            return response.json();
        }).then(data => {
            if (data && !data.error) {
                setOptionData(data.data);
            } else {
                alert(data.message);
            }
        });
    }

    return (
        <>
            {optionData.length > 0 && optionData.map((item: string) => {
                return (
                    <option key={item} value={item}>{item}</option>
                );
            })}
        </>
    );
};

export default GetProjectList;