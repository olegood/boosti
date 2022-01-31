import BaseService from "./baseService.js";

class QuestionsService extends BaseService {

  getAll = () => this.get(`/api/questions/all`)

  getQuestions = () => this.get(`/api/questions`)

  exportQuestions = data => this.post(`/api/questions/export`, data)

  uploadFile = data => this.post('/api/file/upload', data, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

export default new QuestionsService()
