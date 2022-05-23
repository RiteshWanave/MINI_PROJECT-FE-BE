const mongoose = reqiure('mongoose');

const calendarSchema = new mongoose.Schema({
    eventname: {
        required : true,
        type: String,
    },
    date: {
        required : true,
        type: date,
    },
    starttime: {
        required : true,
        type: String,
    },
    endtime: {
        required : true,
        type: String,
    },
    description: {
        required : true,
        type: String,
    },
    areaofinterest: [{
        index: {
            type: String,
        }
    }],
    branches: [{
        branch: {
            type: String,
        }
    }],
    mode: {
        required : true,
        type: String,
    },
    location: {
        type: String,
    },
    link: {
        type: String,
    }
})

calendarSchema.statics.checkIsAvailable = function(date, starttime, endtime) {
    const calendar = this.find({
        $and: [
            {date: date},
            { $or: [
                { $and: [
                    {starttime: {$lte: starttime}},
                    {endtime: {$gte: starttime}}
                ]},
                { $and: [
                    {starttime: {$lte: endtime}},
                    {endtime: {$gte: endtime}}
                ]}
            ]}
        ]
    })
    if(calendar) {
        return false;
    }
    return true;
}


const Calendar = mongoose.model('Calendar', calendarSchema);
module.exports = Calendar;