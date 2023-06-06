interface Props {
    "id": string;
    "project_name": string;
    "poc": string;
    "artifact_detail": string;
    "feature_extended": boolean;
    "feature_name": string;
    "sub_category": string;
    "alternate_POC": string;
    "type": string;
    "multi_site": boolean;
    "domain": string;
    "client_base": string;
    "category": string;
    "sector": string;
    "multi_brand": boolean;
    "last_served_year": string;
}

const ListFeatureProjectTableBody = ({ data }: any) => {
    return (
        <>
            <tbody>
                {data.map((item: Props) => {
                    return (
                        <tr key={item.id}>
                            <td>{item.project_name}</td>
                            <td>{item.feature_name}</td>
                            <td>{item.category}</td>
                            <td>{item.poc}</td>
                            <td>{item.artifact_detail}</td>
                            <td>{item.last_served_year}</td>
                            <td>{String(item.feature_extended)}</td>
                            <td>{item.alternate_POC}</td>
                            <td>{item.sub_category}</td>
                            <td>{item.type}</td>
                            <td>{String(item.multi_brand)}</td>
                            <td>{String(item.multi_site)}</td>
                            <td>{item.sector}</td>
                            <td>{item.domain}</td>
                            <td>{item.client_base}</td>
                        </tr>
                    );
                })}
            </tbody>
        </>
    );
};

export default ListFeatureProjectTableBody;