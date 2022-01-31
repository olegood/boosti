import React from "react";
import AppInfoService from "../service/appInfoService";

export default class AppInfo extends React.Component {

  state = {}

  componentDidMount() {
    AppInfoService.getBuildInfo().then(resp => this.setState(resp.data))
  }

  render() {
    const {version, timestamp} = this.state
    return (<>
      <p><b>Version:</b> {version ? version : "N/A"} <b>Timestamp:</b> {timestamp ? timestamp : "N/A"}</p>
    </>)
  }
}
