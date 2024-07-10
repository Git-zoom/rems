/**
 * @function 日期格式化 将 "yyyy-mm-dd HH:mm:ss" 转化为 "yyyy-mm-dd"
 * @param date 时间  "yyyy-mm-dd HH:mm:ss"
 * @returns {string} 返回日期格式 "yyyy-mm-dd"
 */
function dateToStr(date) {
    console.log(date)
    const time = new Date(date);
    console.log("time==>", time)
    const y = time.getFullYear();
    let M = time.getMonth() + 1;
    M = M < 10 ? ("0" + M) : M;
    let d = time.getDate();
    d = d < 10 ? ("0" + d) : d;
    return y + "-" + M + "-" + d;
}

function timestampToYMDHMS(timestamp) {
    const date = new Date(timestamp);
    const year = date.getFullYear();
    const month = date.getMonth() + 1 < 10 ? `0${date.getMonth() + 1}` : date.getMonth() + 1; // 注意月份从 0 开始，所以需要加 1
    const day = date.getDate() < 10 ? `0${date.getDate()}` : date.getDate();
    const hours = date.getHours() < 10 ? `0${date.getHours()}` : date.getHours();
    const minutes = date.getMinutes() < 10 ? `0${date.getMinutes()}` : date.getMinutes();
    const seconds = date.getSeconds() < 10 ? `0${date.getSeconds()}` : date.getSeconds();
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}