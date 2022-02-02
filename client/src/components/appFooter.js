import { Container, Divider, Typography } from '@mui/material'
import React from 'react'

export default function AppFooter() {
  return (<>
    <Container maxWidth="xl">
      <Divider/>
      <Typography variant="body1" gutterBottom>
        BooTI &copy; 2022
      </Typography>
    </Container>
  </>)
}
