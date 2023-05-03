const apiServiceBaseUrl = () => {
    let hostname = window.location.hostname;
    let protocol = window.location.protocol;
    let port = window.location.port.length > 0 ? (':' + window.location.port) : '';
    return protocol + "//" + hostname + port + '/';
}

export default apiServiceBaseUrl;