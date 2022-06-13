const currDate = async function() {
    const currdate = new Date();
    
    // const date = currdate.getFullYear() + '-' + (currdate.getMonth() + 1) + '-' + currdate.getDate();
    // console.log(date);
    // console.log(currdate);
    const result = currdate.toISOString().split('T')[0]
    return result;
}

module.exports = (currDate);