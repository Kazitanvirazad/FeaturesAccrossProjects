import Buttons from "./Buttons";
const AddFormHeading = ({ heading }: any) => {
    return (
        <>
            <Buttons />
            <div>
                <p className="display-6 align-middle">{heading}</p>
            </div>
        </>
    );
};

export default AddFormHeading;