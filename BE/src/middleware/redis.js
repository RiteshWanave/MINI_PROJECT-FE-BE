const redis = require('redis');
const client = redis.createClient();
client.connect()
.then(() => {
    console.log('Connected to Redis');
})
.catch((error) => {
    console.log(error);
});

async function setCache (token) {
    try {
        await client.set('user_token', token);
        console.log('token set');
    }
    catch (error) {
        console.log(error);
    }
}

async function getCache () {
    try {
        const token = await client.get('user_token');
        console.log('token is : ' + token);
        return token;
    }
    catch (error) {
        console.log(error);
    }
}

module.exports = { setCache, getCache };