import BaseService from "./baseService.js";

class QuestionsService extends BaseService {

  getAll = () => this.get(`/api/questions/all`)

  getQuestions = () => this.get(`/api/questions`)

  exportQuestions = data => this.post(`/api/questions/export`, data)
}

export default new QuestionsService()