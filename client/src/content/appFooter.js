import { Container, Divider, Typography } from '@mui/material'
import React, { useEffect, useState } from 'react'
import AppInfoService from '../service/appInfoService.js'

export default function AppFooter() {

  const [version, setVersion] = useState('N/A')

  useEffect(() => {
    AppInfoService.getBuildInfo().then(resp => {
      const { version } = resp.data
      setVersion(version)
    })
  }, [])

  return (<>
    <Container maxWidth="xl">
      <Divider/>
      <Typography variant="body1" gutterBottom>
        BooTI &copy; 2022 v{version}
      </Typography>
    </Container>
  </>)
}
