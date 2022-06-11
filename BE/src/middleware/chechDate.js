currDate = async function() {
    const currdate = new Date()
    const result = currdate.toISOString().split('T')[0]
    return result;
}

module.exports = (currDate);