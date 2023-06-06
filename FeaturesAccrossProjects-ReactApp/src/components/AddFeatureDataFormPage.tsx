import AddFormHeading from "./AddFormHeading";
import AddFeatureDataForm from "./AddFeatureDataForm";

const AddFeatureDataFormPage = () => {
    const featureFormAttr: any = {
        project_name: 'project_name',
        category: 'category',
        sub_category: 'sub_category',
        feature_name: 'feature_name',
        description: 'description',
        type: 'type',
        poc: 'poc',
        artifact_detail: 'artifact_detail',
        used_year: 'used_year',
        feature_extended: {
            label: 'feature_extended',
            value: false,
            name: 'Feature Extended'
        },
        alternate_POC: 'alternate_POC'
    };
    return (
        <>
            <AddFormHeading heading="Add Feature Form" />
            <AddFeatureDataForm formData={featureFormAttr} />
        </>

    );
};

export default AddFeatureDataFormPage;