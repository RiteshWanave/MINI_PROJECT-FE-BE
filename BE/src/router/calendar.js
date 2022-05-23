const express = require('express');
const router = express.Router();
const {User, Temp} = require('../models/User.js');
const Calendar = require('../models/Calendar.js');

router.post('/insertevent', auth, async (req, res) => {
    try{
        const user = req.user;
        const isAuthorized = await User.isAdminUser(user.id) || await User.isClubUser(user.id);
        if(!isAuthorized){
            const calendar = new Calendar(req.body);
            const checkIsAvailable = await Calendar.checkIsAvailable(calendar.date, calendar.starttime, calendar.endtime);
            if(checkIsAvailable) {
                await calendar.save();
                res.status(200).send(calendar);
            }
            else {
                res.status(400).send({error: 'Event is not available'});
            }
        }
        else{
            res.status(400).send({ error: 'Not authorized to access this resource' });
        }
    }
    catch (error) {
        console.log(error);
        res.status(400).send(error);
    }
})