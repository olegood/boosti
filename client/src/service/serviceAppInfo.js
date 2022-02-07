import ServiceBase from './serviceBase.js'

class ServiceAppInfo extends ServiceBase {

  getBuildInfo = () => this.get('/api/build-properties')

}

export default new ServiceAppInfo()
