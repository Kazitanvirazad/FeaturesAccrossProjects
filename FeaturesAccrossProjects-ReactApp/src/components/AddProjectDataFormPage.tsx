import AddProjectDataForm from "./AddProjectDataForm";
import AddFormHeading from "./AddFormHeading";

const AddProjectDataFormPage = () => {
    const projectFormAttr: any = {
        project_name: 'project_name',
        regionUS: {
            label: 'regionUS',
            value: false,
            name: 'Region US'
        },
        regionCA: {
            label: 'regionCA',
            value: false,
            name: 'Region Canada'
        },
        regionEU: {
            label: 'regionEU',
            value: false,
            name: 'Region EU'
        },
        regionAUNZ: {
            label: 'regionAUNZ',
            value: false,
            name: 'Region AUNZ'
        },
        regionChina: {
            label: 'regionChina',
            value: false,
            name: 'Region China'
        },
        sector: 'sector',
        project_contact_point: 'project_contact_point',
        multi_brand: {
            label: 'multi_brand',
            value: false,
            name: 'Multi Brand'
        },
        brandnames: 'brandnames',
        multi_site: {
            label: 'multi_site',
            value: false,
            name: 'Multi Site'
        },
        project_notes: 'project_notes',
        last_served_year: 'last_served_year',
        client_base: 'client_base',
        practice: 'practice',
        domain: 'domain',
    };

    document.title = 'Add Project Data';
    return (
        <>
            <AddFormHeading heading="Add Project Form" />
            <AddProjectDataForm formData={projectFormAttr} />
        </>
    );
};

export default AddProjectDataFormPage;