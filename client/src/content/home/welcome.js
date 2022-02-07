import { Container, List, ListItemText, ListSubheader, Typography } from '@mui/material'
import React from 'react'
import { Link } from 'react-router-dom'
import Header from '../components/common/header/header.js'

export default function Welcome() {
  return (
    <React.Fragment>
      <Container maxWidth="xl">
        <Header text={'Welcome'}/>
        <Typography variant="body1" gutterBottom>
          Start your work by <Link to="/questions">browsing</Link> existing questions database,
          or <Link to="/questions/add">add</Link> your own question.
        </Typography>
        <Typography variant="body1" gutterBottom>
          You can <Link to="/import">import</Link> file.
          <List>
            <ListSubheader>Supporting file formats:</ListSubheader>
            <ListItemText>comma-separated values, CSV</ListItemText>
          </List>
        </Typography>
        <Typography variant="body1" gutterBottom>
          Enjoy your work!
        </Typography>
      </Container>
    </React.Fragment>
  )
}
