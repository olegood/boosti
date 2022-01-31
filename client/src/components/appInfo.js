import React, {useState} from "react";
import AppInfoService from "../service/appInfoService";
import {Typography} from "@mui/material";

export default function AppInfo() {

  const [version, setVersion] = useState('')

  AppInfoService.getBuildInfo().then(resp => {
    const {version} = resp.data
    setVersion(version)
  });

  return (<>
    <Typography variant="overline" display="block">
      <b>Version:</b> {version ? version : 'N/A'}
    </Typography>
  </>)
}
