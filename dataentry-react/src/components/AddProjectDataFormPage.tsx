import AddProjectDataForm from "./AddProjectDataForm";
import AddProjectDataFormHeading from "./AddProjectDataFormHeading";
import Buttons from "./Buttons";
const AddProjectDataFormPage = () => {
    document.title = 'Add Project Data';
    return (
        <>
            <AddProjectDataFormHeading />
            <AddProjectDataForm project_name={"project_name"} contact_point={"contact_point"} artifacts_details={"artifacts_details"} tools_and_platform={"tools_and_platform"} used_in_year={"used_in_year"} feature={{
                practice: "practice",
                domain: "domain",
                sector: "sector",
                category: "category",
                sub_category: "sub_category",
                feature_short_name: "feature_short_name"
            }} />
            <Buttons />
        </>
    );
};

export default AddProjectDataFormPage;