const jwt = require('jsonwebtoken');
const {User, Temp} = require('../models/User.js');
const redis = require('redis');
const client = redis.createClient();
const { setCache, getCache } = require('../middleware/redis.js');


const auth = async (req, res, next) => {
    // const token = req.header('Authorization').replace('Bearer ','');
    const token = getCache();
    const decoded = jwt.verify(token, process.env.SECRET_KEY);
    try{
        const user = await User.findOne({_id: decoded._id, 'tokens.token': token})
        if(!user){
            throw new Error()
        }
        req.user = user;
        req.token = token;
        next();
    }
    catch (error) {
        res.status(400).send({ error: 'Not authorized to access this resource' });
    }
}

module.exports = auth;