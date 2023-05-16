import serviceuriconfig from '../config/service-uri-config.json';
import apiServiceBaseUrl from '../apiServiceBaseUrl';
import GetDropDownListItems from './GetDropDownListItems';
import Select from 'react-select';

const AddProjectDataForm = ({ formData }: any) => {
    let options = GetDropDownListItems();
    // console.log(options);

    let resData: any = {
        regionUS: false,
        regionCA: false,
        regionEU: false,
        regionAUNZ: false,
        regionChina: false,
        multi_site: false,
        multi_brand: false
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

    let handleReactSelectChange = (event: any) => {
        let name = event?.name;
        let value = event?.value;
        resData[name] = value;
    };

    let onFormSubmit = (event: any) => {
        event.preventDefault();
        let url = apiServiceBaseUrl();
        let uri = serviceuriconfig.AddProject.uri;
        let method = serviceuriconfig.AddProject.method;
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
                <form id='add-project-form' onSubmit={onFormSubmit} action="/addproject" method="post">
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.project_name}>Project Name</label>
                        <input name={formData.project_name} className="form-control" type="text" id={formData.project_name} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.regionUS.label}>{formData.regionUS.name}</label>
                        <select className="form-control" name={formData.regionUS.label} id={formData.regionUS.label} onChange={handleSelectChange} defaultValue="false">
                            <option value="true">Yes</option>
                            <option value="false">No</option>
                        </select>
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.regionCA.label}>{formData.regionCA.name}</label>
                        <select className="form-control" name={formData.regionCA.label} id={formData.regionCA.label} onChange={handleSelectChange} defaultValue="false">
                            <option value="true">Yes</option>
                            <option value="false">No</option>
                        </select>
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.regionEU.label}>{formData.regionEU.name}</label>
                        <select className="form-control" name={formData.regionEU.label} id={formData.regionEU.label} onChange={handleSelectChange} defaultValue="false">
                            <option value="true">Yes</option>
                            <option value="false">No</option>
                        </select>
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.regionAUNZ.label}>{formData.regionAUNZ.name}</label>
                        <select className="form-control" name={formData.regionAUNZ.label} id={formData.regionAUNZ.label} onChange={handleSelectChange} defaultValue="false">
                            <option value="true">Yes</option>
                            <option value="false">No</option>
                        </select>
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.regionChina.label}>{formData.regionChina.name}</label>
                        <select className="form-control" name={formData.regionChina.label} id={formData.regionChina.label} onChange={handleSelectChange} defaultValue="false">
                            <option value="true">Yes</option>
                            <option value="false">No</option>
                        </select>
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.sector}>Sector</label>
                        <Select
                            className="form-control"
                            name={formData.sector}
                            id={formData.sector}
                            onChange={handleReactSelectChange}
                            options={options['sector']}
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.project_contact_point}>Project Contact Point</label>
                        <input name={formData.project_contact_point} className="form-control" type="text" id={formData.project_contact_point} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.multi_brand.label}>{formData.multi_brand.name}</label>
                        <select className="form-control" name={formData.multi_brand.label} id={formData.multi_brand.label} onChange={handleSelectChange} defaultValue="false">
                            <option value="true">Yes</option>
                            <option value="false">No</option>
                        </select>
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.brandnames}>Brand Names</label>
                        <input name={formData.brandnames} className="form-control" type="text" id={formData.brandnames} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.multi_site.label}>{formData.multi_site.name}</label>
                        <select className="form-control" name={formData.multi_site.label} id={formData.multi_site.label} onChange={handleSelectChange} defaultValue="false">
                            <option value="true">Yes</option>
                            <option value="false">No</option>
                        </select>
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.project_notes}>Project Notes</label>
                        <input name={formData.project_notes} className="form-control" type="text" id={formData.project_notes} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.last_served_year}>Last Served Year</label>
                        <input name={formData.last_served_year} className="form-control" type="text" id={formData.last_served_year} defaultValue="" onChange={handleChange} required />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.client_base}>Client Base</label>
                        {/* <input name={formData.client_base} className="form-control" type="text" id={formData.client_base} defaultValue="" onChange={handleChange} required /> */}
                        <Select
                            className="form-control"
                            name={formData.client_base}
                            id={formData.client_base}
                            onChange={handleReactSelectChange}
                            options={options['client_base']}
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.practice}>Practice</label>
                        {/* <input name={formData.practice} className="form-control" type="text" id={formData.practice} defaultValue="" onChange={handleChange} required /> */}
                        <Select
                            className="form-control"
                            name={formData.practice}
                            id={formData.practice}
                            onChange={handleReactSelectChange}
                            options={options['practice']}
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label" htmlFor={formData.domain}>Domain</label>
                        {/* <input name={formData.domain} className="form-control" type="text" id={formData.domain} defaultValue="" onChange={handleChange} required /> */}
                        <Select
                            className="form-control"
                            name={formData.domain}
                            id={formData.domain}
                            onChange={handleReactSelectChange}
                            options={options['domain']}
                        />
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