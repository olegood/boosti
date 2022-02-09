import { Container, Divider, Typography } from '@mui/material'
import React, { useEffect, useState } from 'react'
import ServiceAppInfo from '../service/serviceAppInfo.js'

export default function AppFooter() {

  const [version, setVersion] = useState('N/A')

  useEffect(() => {
    ServiceAppInfo.getBuildInfo()
      .then(resp => {
        const { version } = resp.data
        setVersion(version)
      })
      .catch(() => {
        setVersion('N/A')
      })
  }, [])

  return (
    <React.Fragment>
      <Container maxWidth="xl">
        <Divider/>
        <Typography variant="body1" gutterBottom>
          BooTI &copy; 2022 v{version}
        </Typography>
      </Container>
    </React.Fragment>
  )
}
