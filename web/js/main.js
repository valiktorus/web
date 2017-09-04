
function getToDo(id) {
    event.preventDefault();
    $('.visible').removeClass('visible');
    var elementId = id.replace('Button', "");
    $("#" + elementId).addClass('visible');
    switch (elementId){
        case 'fixed':
            $('#deleteTaskButton').addClass('visible');
            break;
        case 'recycleBin':
            $('.recycleBinButtons').addClass('visible');
            break;
        default:
            $('.commonTaskButton').addClass('visible');
    }
}

function populateData(operation) {
    var $checkboxes;
    if (operation === 'clearAll') {
        $checkboxes = $('.todo.visible :checkbox');
    } else {
        $checkboxes = $('.todo.visible :checkbox:checked');
    }
    if (!$checkboxes.length) {
        return;
    }
    var result = [];
    $checkboxes.each(function(id, el) {
        var $el = $(el);
        result.push({
            description: $el.data('description'),
            date: $el.data('date')
        });
    });
    return JSON.stringify(result);
}

function onClickButton(event, url, operation) {
    event.preventDefault();
    var data = populateData(operation);
    if (!data) {
        return;
    }
    if (operation !== null) {
        data += "\n" + operation;
    }
    $.post(url, data, function(data) {
        $('body').html(data.replace(/.*?<body>(.*?)<\/body>.*/, '$1'));
        setOnClickButtons();
    });
}

function setOnClickButtons() {
    $('#deleteTaskButton').on('click', function (event) {
        onClickButton(event, contextPath + '/operation/changeTaskController', 'deleted');
    });
    $('#fixTaskButton').on('click', function (event) {
        onClickButton(event,contextPath + '/operation/changeTaskController', 'fixed')
    });
    $('#restoreTaskButton').on('click', function (event) {
        onClickButton(event, contextPath + '/operation/changeTaskController', 'actual')
    });
    $('#createTaskButton').on('click', function (event) {
        event.preventDefault();
        var day = $('.todo.visible .todoHeader p').text();
        $.post(contextPath + "/pages/taskPages/createTask.jsp", function (data) {
            $('html').html(data);
            var stringDate = getInputDate(day);
            if (stringDate !== null){
                var inputDate = $('#importDate');
                inputDate.attr('value', stringDate);
                $('#dateCreateTaskHeader').hide();
                inputDate.hide();
            }
        })
    });
    $('#clearSelectedTaskButton').on('click', function (event) {
        onClickButton(event,contextPath + "/operation/deleteTaskController")
    });
    $('#clearAllTaskButton').on('click', function (event) {
        onClickButton(event,contextPath +  "/operation/deleteTaskController", 'clearAll')
    });
}

function getInputDate(day) {
    var stringDate = null;
    var date = new Date();
    if (day === 'Today'){
        stringDate = getStringDate(date);
    }
    if (day === 'Tomorrow'){
        date.setDate(date.getDate() + 1);
        stringDate = getStringDate(date);
    }
    return stringDate;
}
function getStringDate(date) {
    return date.toISOString().match(/\d{4}-\d{2}-\d{2}/g)[0];
    
}

$(document).ready(function () {
    setOnClickButtons();
});

