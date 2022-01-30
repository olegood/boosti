import BaseService from "./baseService";

class AppInfoService extends BaseService {

  getBuildInfo() {
    return this.get(`/api/build-properties`)
  }

}

export default new AppInfoService()
