import axios from 'axios'

const baseUrl = 'http://localhost:8082'

export default class ServiceBase {

  get = (url) => axios.get(`${baseUrl}${url}`)

  post = (url, body) => axios.post(`${baseUrl}${url}`, body)

  post = (url, body, config) => axios.post(`${baseUrl}${url}`, body, config)
}
