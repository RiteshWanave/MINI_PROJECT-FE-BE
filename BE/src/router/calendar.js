const express = require('express');
const router = express.Router();
const {User, Temp} = require('../models/User.js');
const Calendar = require('../models/Calendar.js');
const auth = require('../middleware/auth.js');

router.post('/insertevent', auth, async (req, res) => {
    try{
        const user = req.user;
        const isAuthorized = await User.isAdminUser(user.id) || await User.isClubUser(user.id);
        if(isAuthorized){
            const calendar = new Calendar(req.body);
            calendar.createdBy = user.email;
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

router.post('/deleteEvent', auth, async (req, res) => {
    try{
        const user = req.user;
        const isAdmin = await User.isAdminUser(user.id);
        if(isAdmin){
            const calendar = await Calendar.deleteEvent(req.body.name, req.body.date, req.body.starttime, req.body.endtime);
            if(calendar) {
                res.status(200).send(calendar);
            }
            else {
                res.status(400).send({error: 'Event not found'});
            }
        }
        else{
            res.status(400).send({ error: 'Not admin user' });
        }
    }
    catch (error) {
        console.log(error);
        res.status(400).send(error);
    }
})

router.post('/requestToDelete', auth, async (req, res) => {
    try{
        const user = req.user;
        const isClubUser = await User.isClubUser(user.id);
        if(isClubUser){
            if(calendar.createdBy == user.email){
                const calendar = await Calendar.requestToDelete(req.body.name, req.body.date, req.body.starttime, req.body.endtime);
                if(calendar) {
                    res.status(200).send(calendar);
                }
                else {
                    res.status(400).send({error: 'Event not found'});
                }
            }
        }
        else{
            res.status(400).send({ error: 'Not club user' });
        }
    }
    catch (error) {
        console.log(error);
        res.status(400).send(error);
    }
})

router.get('/getEvents', async (req, res) => {
    try{
        // const user = req.user;
        let Date = await currDate();
        console.log(Date);
        Calendar.find( {date: {$gte: Date }}, (err, events) => {
            if(err) {
                res.status(400).send(err);
            }
            else {
                console.log(events);
                res.status(200).send(events);
            }
        }).sort({date: 1})
    }
    catch (error) {
        console.log(error);
        res.status(400).send(error);
    }
})





module.exports = router;    


currDate = async function() {
    const currdate = new Date()
    const result = currdate.toISOString().split('T')[0]
    return result;
}