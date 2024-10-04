function scriptProtectionName(txt) {
    txt.value = txt.value.replace(/[^\s,&a-zA-Zа-яА-Я-0-9-'.]+/g, '');
}

function scriptProtectionUrl(txt) {
    txt.value = txt.value.replace(/[^#,/&?:;%=_@а-яА-Яa-zA-Z0-9-'.]+/g, '');
}

function checkURL (abc) {
    var string = abc.value;
    if (!~string.indexOf("http")) {
        string = "http://" + string;
    }
    abc.value = string;
    return abc
}

function scriptProtectionText(txt) {
    txt.value = txt.value.replace(/[^/,.!?+:;*()%=_@а-яА-Яa-zA-Z0-9-'"\s\n\r.]+/g, '');
}

function scriptProtectionLogin(txt) {
    txt.value = txt.value.replace(/[^a-zA-Z0-9-.]+/g, '');
}