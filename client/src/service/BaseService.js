import axios from "axios";

export default class BaseService {

  get(url) {
    const baseUrl = 'http://localhost:8082'
    return axios.get(`${baseUrl}${url}`)
  }
}
