import ServiceBase from './serviceBase.js'

class ServiceQuestions extends ServiceBase {

  getAllQuestions = () => this.get('/api/questions')

  exportQuestions = data => this.post('/api/questions/export', data)

  saveQuestion = (data) => this.post('/api/questions', data)

  deleteQuestions = data => this.delete('/api/questions/', data)
}

export default new ServiceQuestions()
