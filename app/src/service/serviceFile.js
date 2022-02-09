import ServiceBase from './serviceBase.js'

class ServiceFile extends ServiceBase {

  uploadFile = data => this.post('/api/file/upload', data, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })

}

export default new ServiceFile()
