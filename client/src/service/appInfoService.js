import BaseService from "./BaseService";

class AppInfoService extends BaseService {

  getBuildInfo() {
    return this.get(`/api/build-properties`)
  }

}

export default new AppInfoService()
