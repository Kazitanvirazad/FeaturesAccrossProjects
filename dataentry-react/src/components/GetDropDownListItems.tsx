import serviceuriconfig from '../config/service-uri-config.json';
import apiServiceBaseUrl from '../apiServiceBaseUrl';

const GetDropDownListItems = () => {
    let url = apiServiceBaseUrl();
    let uri = serviceuriconfig.DropdownList.uri;
    let method = serviceuriconfig.DropdownList.method;
    url += uri;
    let options: any = {
        practice: [],
        client_base: [],
        domain: [],
        sector: []
    };

    let addOptions = (data: any) => {
        data.practice.map((item: string) => {
            options.practice.push({ 'value': item, 'label': item, 'name': 'practice' });
        });
        data.domain.map((item: string) => {
            options.domain.push({ 'value': item, 'label': item, 'name': 'domain' });
        });
        data.sector.map((item: string) => {
            options.sector.push({ 'value': item, 'label': item, 'name': 'sector' });
        });
        data.client_base.map((item: string) => {
            options.client_base.push({ 'value': item, 'label': item, 'name': 'client_base' });
        });
    }

    if (options.practice.length < 1) {
        fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json",
            }
        }).then(optionsponse => {
            return optionsponse.json();
        }).then(data => {
            if (data && !data.error) {
                addOptions(data.data);
                console.log(options);

            } else {
                alert(data.message);
            }
        });
    }
    return options;
};

export default GetDropDownListItems;