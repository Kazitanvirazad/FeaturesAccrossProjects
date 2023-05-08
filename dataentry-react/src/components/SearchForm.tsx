import { useState } from "react";
import apiServiceBaseUrl from "../apiServiceBaseUrl";
import serviceuriconfig from '../config/service-uri-config.json';
import ListFeatureProjectTableBody from "./ListFeatureProjectTableBody";
import Buttons from "./Buttons";
import ListFeatureProjectTableHead from "./ListFeatureProjectTableHead";

const SearchForm = () => {
    let [inputVal, setInputVal] = useState('');
    let [resData, setResData] = useState([]);
    let handleChange = (event: any) => {
        const value: string = event.target.value;
        setInputVal(value);
    };

    let onFormSubmit = (event: any) => {
        event.preventDefault();

        let url = apiServiceBaseUrl();
        let uri = serviceuriconfig.SearchFeature.uri;
        let method = serviceuriconfig.SearchFeature.method;
        url += uri + '?keyword=' + inputVal;
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
                alert(data.message);
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
                        <label className="form-label" htmlFor="searchkeyword">Enter Search key</label>
                        <input name="searchkeyword" className="form-control" type="text" id="searchkeyword" defaultValue="" value={inputVal} onChange={handleChange} required />
                    </div>
                    <div>
                        <input className="btn btn-primary" type="submit" />
                    </div>
                </form>
            </div>
            <div className="result-table">
                {resData.length > 0 && <table className="table table-striped">
                    <ListFeatureProjectTableHead />
                    <ListFeatureProjectTableBody data={resData}></ListFeatureProjectTableBody >
                </table>}
            </div>
        </>
    );
};

export default SearchForm;