import { Container, List, ListItem, ListSubheader } from '@mui/material'
import React, { useEffect, useState } from 'react'
import AppInfoService from '../service/appInfoService'
import Header from './common/header/header'

export default function PageAbout() {

  const [appInfo, setAppInfo] = useState({
    name: 'N/A',
    group: 'N/A',
    artifact: 'N/A',
    version: 'N/A'
  })

  useEffect(() => {
    AppInfoService.getBuildInfo().then(resp => {
      setAppInfo(resp.data)
    })
  })

  const { name, group, artifact, version } = appInfo

  return <>
    <Container maxWidth="xl">
      <Header text={'About'}/>
      <List>
        <ListSubheader>Backend:</ListSubheader>
        <ListItem>Name: {name}</ListItem>
        <ListItem>Group: {group}</ListItem>
        <ListItem>Artifact: {artifact}</ListItem>
        <ListItem>Version: {version}</ListItem>

        <ListSubheader>Frontend:</ListSubheader>
        <ListItem>React: {React.version}</ListItem>
      </List>
    </Container>
  </>
}
