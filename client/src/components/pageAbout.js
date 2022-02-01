import React, { useEffect, useState } from 'react';
import AppInfoService from '../service/appInfoService';
import Header from './common/header/header';
import { Container, List, ListItem } from '@mui/material';

export default function PageAbout() {

  const [appInfo, setAppInfo] = useState({})

  useEffect(() =>{
    AppInfoService.getBuildInfo().then(resp => {
      setAppInfo(resp.data)
    })
  });

  const { name, group, artifact, version } = appInfo

  return (<>
    <Container maxWidth="xl">
      <Header text={'About'}/>
      <List>
        <ListItem>Name: {name}</ListItem>
        <ListItem>Group: {group}</ListItem>
        <ListItem>Artifact: {artifact}</ListItem>
        <ListItem>Version: {version}</ListItem>
        <ListItem>React: {React.version}</ListItem>
      </List>
    </Container>
  </>)
}
