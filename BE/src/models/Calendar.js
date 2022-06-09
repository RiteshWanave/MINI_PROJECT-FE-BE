const mongoose = require('mongoose');

const calendarSchema = new mongoose.Schema({
    eventname: {
        required : true,
        type: String,
    },
    date: {
        required : true,
        type: String,
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
    },
    toDelete: {
        type: Boolean,
        default: false,
    },
    createdBy: {
        required : true,
        type: String,
    },
})

calendarSchema.statics.checkIsAvailable = async function(date, starttime, endtime) {
    const calendar = await Calendar.findOne({
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
    if(!calendar) {
        return true;
    }
    console.log(calendar.date);
    return false;
}

calendarSchema.statics.deleteEvent = function(name, date, starttime, endtime) {
    const calendar = this.findOneAndDelete({name: name, date: date, starttime: starttime, endtime: endtime});
    if(calendar) {
        return true;
    }
    return false;
}

calendarSchema.statics.requestToDelete = function(name, date, starttime, endtime) {
    const calendar = this.findOneAndUpdate({name: name, date: date, starttime: starttime, endtime: endtime}, {toDelete: true});
    if(calendar) {
        return calendar;
    }
    else {
        console.log('Event not found');
    }
}



const Calendar = mongoose.model('Calendar', calendarSchema);
module.exports = Calendar;