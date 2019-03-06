
function createTimes(){
    let sumTemplate=''
    for(let i=9;i<=21;i++){

        if(i.toString().length < 2){
            i = "0"+i
        }
        let template = `
            <option>${i}:00</option>
            <option>${i}:30</option>
        `
        sumTemplate = sumTemplate + template
    }
    sumTemplate += "<option>22:00</option>"
    $("#bookStart").html(sumTemplate)
    $("#bookEnd").html(sumTemplate)
}

createTimes()


function submitBooking(e){

    const username = $("#name").val()
    const contact = $("#contact").val()
    const start = $("#tempDay").val() + $("#bookStart option:selected").val()
    const end = $("#tempDay").val() + $("#bookEnd option:selected").val()
    const people = $("#people").val()

    if(username===""){
        alert("이름을 입력해주세요")
        return;
    }
    if(contact===""){
        alert("연락처를 입력해주세요")
        return;
    }

    const req = {
        username : username,
        contact: contact,
        start: start,
        end: end,
        people: people
    }

    $.ajax({
        type: "POST",
        url: '/booking/new',
        data: JSON.stringify(req),
        dataType: "JSON",
        contentType: 'application/json',
        success: function (data) {
            const title = `${data.username}님(${data.time}) `
            const t = `
                <div class="fc-event fc-event-hori fc-event-draggable fc-event-start fc-event-end">
                    <div class="fc-event-inner">
                        <span class="fc-event-title">${title}</span>
                    </div>
                    <div class="ui-resizable-handle ui-resizable-e">&nbsp;&nbsp;&nbsp;</div>
                </div>`
            $("td[data-date="+data.day+"] div.fc-day-content div.content" ).append(t)
            $('#modal-calendar').modal('hide')
        },
    });
}

$(function(){
    $("#submitBooking").click(submitBooking)
})
