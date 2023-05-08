interface Props {
    "id": string;
    "project_name": string;
    "poc": string;
    "artifact_detail": string;
    "used_year": string;
    "feature_extended": boolean;
}


const ListFeatureProjectTableBody = ({ data }: any) => {
    return (
        <>
            <tbody>
                {data.map((item: Props) => {
                    return (
                        <tr key={item.id}>
                            <td>{item.project_name}</td>
                            <td>{item.poc}</td>
                            <td>{item.artifact_detail}</td>
                            <td>{item.used_year}</td>
                            <td>{String(item.feature_extended)}</td>
                        </tr>
                    );
                })}
            </tbody>
        </>
    );
};

export default ListFeatureProjectTableBody;