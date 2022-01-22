// import axios from "axios";
//
// axios.defaults.baseURL = '/api';
// const requestAxios = axios.create({
//     timeout: 5000,
//     headers: {
//         'Content-Type': 'application/json; charset=utf-8'
//     }});
// requestAxios.interceptors.request.use(function(config) {
//     let token = Tool.getLoginUser();
//     console.log(token);
//     if (token !== '') {
//         config.headers['Authorization'] = token;
//     }
//     return config;
// }, error => {})
//
// requestAxios.interceptors.response.use(function(response) {
//     console.log("返回结果：", response);
//     return response;
// }, error => {
//     console.log("lanjie:", error);
// });
//
//
// export default requestAxios;