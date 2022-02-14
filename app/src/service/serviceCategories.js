import ServiceBase from './serviceBase.js'

class ServiceCategories extends ServiceBase {

  getAllCategories = () => this.get('/api/categories')

}

export default new ServiceCategories()
