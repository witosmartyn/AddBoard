function sendDeleteRequest(url, rUrl) {

    $.ajax({
        url: url,
        method: "DELETE",
        success: function () {
            window.location = rUrl;
        },
        error: function () {
            console.error('ean is not contains in database');
            window.location.reload();
        }
    });
}

function sendUpdateRequest(formId, rUrl) {
    var form = $('#' + formId);
    $.ajax({
        url: form.attr('action'),
        method: "PUT",
        data: form.serialize(),
        success: function () {
            window.location = rUrl;
        },
        error: function () {
            window.location.reload();
        }
    });
}
