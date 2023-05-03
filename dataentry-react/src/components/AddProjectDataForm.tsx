import serviceuriconfig from '../config/service-uri-config.json';
import apiServiceBaseUrl from '../apiServiceBaseUrl';

interface Props {
    project_name: string;
    contact_point: string;
    artifacts_details: string;
    tools_and_platform: string;
    used_in_year: string;
    feature: {
        practice: string;
        domain: string;
        sector: string;
        category: string;
        sub_category: string;
        feature_short_name: string;
    }
}

const AddProjectDataForm = (formData: Props) => {
    let resData: any = formData;
    let handleChange = (event: any) => {
        const name = event.target.name;
        const value = event.target.value;
        if (name in resData) {
            resData[name] = value;
        } else if (resData.feature && name in resData.feature) {
            resData.feature[name] = value;
        }
    };

    let onFormSubmit = (event: any) => {
        event.preventDefault();
        let url = apiServiceBaseUrl();
        let uri = serviceuriconfig.addData.uri;
        let method = serviceuriconfig.addData.method;
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
                alert(data.errorMsg);
            }
        });
    }

    return (
        <>
            <div>
                <form id='add-project-form' onSubmit={onFormSubmit} action="/addData" method="post">
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.project_name}>Project Name</label>
                        <input name={formData.project_name} className="form-control" type="text" id={formData.project_name} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.contact_point}>Contact Point</label>
                        <input name={formData.contact_point} className="form-control" type="text" id={formData.contact_point} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.artifacts_details}>Artifacts Details</label>
                        <input name={formData.artifacts_details} className="form-control" type="text" id={formData.artifacts_details} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.tools_and_platform}>Tools And Platform</label>
                        <input name={formData.tools_and_platform} className="form-control" type="text" id={formData.tools_and_platform} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.used_in_year}>Used In Year</label>
                        <input name={formData.used_in_year} className="form-control" type="text" id={formData.used_in_year} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.feature.practice}>Practice</label>
                        <input name={formData.feature.practice} className="form-control" type="text" id={formData.feature.practice} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.feature.domain}>Domain</label>
                        <input name={formData.feature.domain} className="form-control" type="text" id={formData.feature.domain} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.feature.sector}>Sector</label>
                        <input name={formData.feature.sector} className="form-control" type="text" id={formData.feature.sector} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.feature.category}>Category</label>
                        <input name={formData.feature.category} className="form-control" type="text" id={formData.feature.category} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.feature.sub_category}>Sub Category</label>
                        <input name={formData.feature.sub_category} className="form-control" type="text" id={formData.feature.sub_category} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.feature.feature_short_name}>Feature Short Name</label>
                        <input name={formData.feature.feature_short_name} className="form-control" type="text" id={formData.feature.feature_short_name} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div>
                        <input className="btn btn-primary" type="submit" />
                    </div>
                </form >
            </div >
        </>
    );
};

export default AddProjectDataForm;