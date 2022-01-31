import axios from "axios";

const baseUrl = 'http://localhost:8082'

export default class BaseService {

  get(url) {
    return axios.get(`${baseUrl}${url}`)
  }

  post(url, body) {
    return axios.post(`${baseUrl}${url}`, body)
  }

  post(url, body, config) {
    return axios.post(`${baseUrl}${url}`, body, config)
  }
}
