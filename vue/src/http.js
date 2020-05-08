import {getCookie} from './components/global/cookie';
import axios from 'axios';
// import { Message } from 'element-ui'
 
axios.defaults.timeout = 5000; //超时终止请求
axios.defaults.baseURL ='http://localhost:8080/'; //配置请求地址
 
 
//http request 拦截器
axios.interceptors.request.use(
  config => {
    config.data = JSON.stringify(config.data);
    config.headers = {
      'Content-Type':'application/json;charset=UTF-8' 
    }
    var token =  getCookie("token")
    if(token){
      config.headers.token = token
    }
    return config;
  },
  error => {
    return Promise.reject(err);
  }
);
 
//http response 拦截器
axios.interceptors.response.use(
  response => {
    if(response.data.code === 0){
      router.replace("/")
      alert("请重新登录")
    }
    return response;
  },
  error => {
    return Promise.reject(error)
  }
)
 
 
/**
 * 封装get方法
 * @param url
 * @param data
 * @returns {Promise}
 */
 
export function fetch(url,params={}){
  return new Promise((resolve,reject) => {
    axios.get(url,{
      params:params
    })
      .then(response => {
        resolve(response.data);
      })
      .catch(err => {
        reject(err)
      })
  })
}
 
/**
 * 封装post请求
 * @param url
 * @param data
 * @returns {Promise}
 */
 
export function post(url,data = {}){
  return new Promise((resolve,reject) => {
    axios.post(url,data)
      .then(response => {
        resolve(response.data);
      },err => {
        reject(err)
      })
  })
}
 
/**
 * 封装patch请求
 * @param url
 * @param data
 * @returns {Promise}
 */
 
export function patch(url,data = {}){
  return new Promise((resolve,reject) => {
    axios.patch(url,data)
      .then(response => {
        resolve(response.data);
      },err => {
        reject(err)
      })
  })
}
 
/**
 * 封装put请求
 * @param url
 * @param data
 * @returns {Promise}
 */
 
export function put(url,data = {}){
  return new Promise((resolve,reject) => {
    axios.put(url,data)
      .then(response => {
        resolve(response.data);
      },err => {
        reject(err)
      })
  })
}