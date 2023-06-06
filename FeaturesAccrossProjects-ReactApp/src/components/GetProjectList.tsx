import serviceuriconfig from '../config/service-uri-config.json';
import apiServiceBaseUrl from '../apiServiceBaseUrl';
import { useNavigate } from 'react-router-dom';

const GetProjectList = () => {
    const navigate = useNavigate();
    let url = apiServiceBaseUrl();
    let uri = serviceuriconfig.ProjectList.uri;
    let method = serviceuriconfig.ProjectList.method;
    url += uri;
    let options: any = [];

    let addOptions = (data: string[]) => {
        if (data && data.length > 0) {
            data.map((item: string) => {
                options.push({ 'value': item, 'label': item, 'name': 'project_name' });
            });
        }
    }

    if (options.length < 1) {
        fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json",
            }
        }).then(response => {
            return response.json();
        }).then(data => {
            if (data && !data.error) {
                addOptions(data.data);
            } else {
                alert(data.message);
                navigate("/addproject");
            }
        });
    }
    return options;
};

export default GetProjectList;