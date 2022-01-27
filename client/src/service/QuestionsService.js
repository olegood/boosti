import BaseService from "./BaseService.js";

class QuestionsService extends BaseService {

  getAll = () => this.get(`/api/questions/all`)

  getQuestions = () => this.get(`/api/questions`)
}

export default new QuestionsService()