class Day{
    constructor(day, year, month, today){
        this.day = day
        this.year = year
        this.month = month
        this.today = today
    }

    isPast(){
        return this.month > (this.day.month() + 1)
    }

    get date(){
        let m = this.day.months() +1
        if(m.toString().length==1){
            m = "0"+m
        }
        let d = this.day.date()
        if(this.day.date().toString().length==1){
            d = "0"+d
        }
        return this.day.get('y')+'-'+m+'-'+d
    }
}

function getPrevStartDay(year, month){
    let day = moment(year+"-"+month+"-1")
    let dayNum = day.day()
    day.subtract(dayNum, 'day')
    return day
}

let tmpl = `
<div class="fc-event fc-event-hori fc-event-draggable fc-event-start fc-event-end">
    <div class="fc-event-inner">
        <span class="fc-event-title">Birthday Party</span>
    </div>
    <div class="ui-resizable-handle ui-resizable-e">&nbsp;&nbsp;&nbsp;</div>
</div>`

var app = new Vue({
    el: '#calendar',
    data: {
        message: 'Hello Vue!',
        year:0,
        month:0,
        weeks: [],
        today: null
    },
    methods:{
        initToday(){
            const t = moment()
            this.year = t.get('y')
            this.month = t.months() + 1
            this.today = t
        },
        left(){
            if(this.month == 1){
                this.year--
                this.month = 12;
            }
            else this.month--;
            this.drawDays()
        },
        right(){
            if(this.month == 12){
                this.year++
                this.month = 1;
            }
            else this.month++;
            this.drawDays()
        },
        isPast(day){
            let result = []
            if(this.month != (day.month() + 1)){
                result.push('fc-other-month')
            }
            if(this.today.month() == day.month() && this.today.date()== day.date()){
                result.push('fc-state-highlight')
            }
            return result
        },
        drawDays(){
            $(".fc-event").remove()
            this.weeks = []
            let startDay = getPrevStartDay(this.year, this.month)
            let lastDay
            let daySum = 0;

            for (let j = 0; j < 6; j++) {
                let weekDays = []
                for (let i = 0; i < 7; i++) {
                    let d = moment(startDay).add(daySum,'day')
                    let dayWrap = new Day(d, this.year, this.month, this.today)
                    weekDays.push(dayWrap)
                    daySum++

                    if (j == 5 && i == 6) {
                        lastDay = dayWrap
                    }
                }
                this.weeks.push(weekDays)
            }

            const startDate = new Day(startDay, this.year, this.month, this.today).date
            const endDate = lastDay.date
            console.log('startDate: ', startDate, endDate)

            $.get(`/booking/list?start=${startDate}&end=${endDate}`, function(resp){
                console.log('data :', resp)

                $.each(resp, function(index,data){
                    const title = `${data.username}님(${data.time}) `
                    const t = `
                <div class="fc-event fc-event-hori fc-event-draggable fc-event-start fc-event-end">
                    <div class="fc-event-inner">
                        <span class="fc-event-title">${title}</span>
                    </div>
                    <div class="ui-resizable-handle ui-resizable-e">&nbsp;&nbsp;&nbsp;</div>
                </div>`
                    $("td[data-date="+data.day+"] div.fc-day-content div.content" ).append(t)
                })
            })

        },
        newBook(day) {
            console.log('day', day.date)
            $("#tempDay").val(day.date+" ");
            $('#modal-calendar').modal('show')
        }
    },
    mounted() {
        this.initToday()
        this.drawDays()
    }
})
