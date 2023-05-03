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
        feature_description: string
    }
}


const ListProjectTableView = ({ data }: any) => {
    return (
        <>
            {data.map((item: Props) => {
                return (<tr key={item.project_name}>
                    <td>{item.project_name}</td>
                    <td>{item.contact_point}</td>
                    <td>{item.artifacts_details}</td>
                    <td>{item.tools_and_platform}</td>
                    <td>{item.used_in_year}</td>
                    <td>{item.feature.practice}</td>
                    <td>{item.feature.domain}</td>
                    <td>{item.feature.sector}</td>
                    <td>{item.feature.category}</td>
                    <td>{item.feature.sub_category}</td>
                    <td>{item.feature.feature_short_name}</td>
                    <td>{item.feature.feature_description}</td>
                </tr>)
            })}
        </>
    );
};

export default ListProjectTableView;