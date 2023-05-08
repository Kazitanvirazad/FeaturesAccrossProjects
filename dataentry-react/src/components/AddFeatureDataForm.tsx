import serviceuriconfig from '../config/service-uri-config.json';
import apiServiceBaseUrl from '../apiServiceBaseUrl';
import GetProjectList from './GetProjectList';

const AddFeatureDataForm = ({ formData }: any) => {
    let resData: any = {
        feature_extended: false
    };

    let handleChange = (event: any) => {
        const name = event.target.name;
        const value = event.target.value;
        resData[name] = value;
    };

    let handleSelectChange = (event: any) => {
        let name = event.target.name;
        let value = event.target.value.toLowerCase() === 'true';
        resData[name] = value;
    };

    let onFormSubmit = (event: any) => {
        event.preventDefault();
        let url = apiServiceBaseUrl();
        let uri = serviceuriconfig.AddFeature.uri;
        let method = serviceuriconfig.AddFeature.method;
        url += uri;
        fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(resData)
        }).then(response => {
            return response.json();
        }).then(data => {
            if (data && !data.error) {
                alert("Data Added successfully");
            } else {
                alert(data.message);
            }
        });
    };

    return (
        <>
            <div>
                <form id='add-feature-form' onSubmit={onFormSubmit} action="/addfeature" method="post">
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.project_name}>Project Name</label>
                        <select className="form-control" name={formData.project_name} id={formData.project_name} onChange={handleChange} defaultValue="false">
                            <GetProjectList />
                        </select>
                        {/* <input name={formData.project_name} className="form-control" type="text" id={formData.project_name} defaultValue="" onChange={handleChange} required /> */}
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.category}>Category</label>
                        <input name={formData.category} className="form-control" type="text" id={formData.category} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.sub_category}>Sub Category</label>
                        <input name={formData.sub_category} className="form-control" type="text" id={formData.sub_category} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.feature_name}>Feature Name</label>
                        <input name={formData.feature_name} className="form-control" type="text" id={formData.feature_name} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.description}>Description</label>
                        <input name={formData.description} className="form-control" type="text" id={formData.description} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.type}>Type</label>
                        <input name={formData.type} className="form-control" type="text" id={formData.type} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.poc}>Point of Contact</label>
                        <input name={formData.poc} className="form-control" type="text" id={formData.poc} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.artifact_detail}>Artifact Detail</label>
                        <input name={formData.artifact_detail} className="form-control" type="text" id={formData.artifact_detail} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.used_year}>Used Year</label>
                        <input name={formData.used_year} className="form-control" type="text" id={formData.used_year} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.feature_extended.label}>{formData.feature_extended.name}</label>
                        <select className="form-control" name={formData.feature_extended.label} id={formData.feature_extended.label} onChange={handleSelectChange} defaultValue="false">
                            <option value="true">Yes</option>
                            <option value="false">No</option>
                        </select>
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.alternate_POC}>Alternate Point of Contact</label>
                        <input name={formData.alternate_POC} className="form-control" type="text" id={formData.alternate_POC} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div>
                        <input className="btn btn-primary" type="submit" />
                    </div>
                </form>
            </div>
        </>
    );
};

export default AddFeatureDataForm;