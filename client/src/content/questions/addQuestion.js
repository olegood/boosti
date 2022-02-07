import { Box, Button, Container, FormControl, TextField } from '@mui/material'
import React, { useState } from 'react'
import QuestionsService from '../../service/questionsService.js'
import Header from '../components/common/header/header.js'

export default function AddQuestion() {

  const [topic, setTopic] = useState('')
  const [text, setText] = useState('')

  const handleSave = () => {
    QuestionsService.saveQuestion({ topic, text })
      .then(resp => handleClear())
      .catch(resp => console.error(resp))
  }

  const handleClear = () => {
    setText('')
    setTopic('')
  }

  return (
    <React.Fragment>
      <Container maxWidth="xl">
        <Header text={'Add Question'}/>
        <Box sx={{ display: 'flex', flexWrap: 'wrap' }}>
          <FormControl>
            <TextField required id="question-topic" label="Topic" variant="standard" error={topic === ''}
                       value={topic}
                       onChange={(e) => {
                         const { value } = e.target
                         setTopic(value)
                       }}/>
            <TextField required id="question-text" label="Text" variant="standard" error={text === ''}
                       value={text}
                       onChange={(e) => {
                         const { value } = e.target
                         setText(value)
                       }}/>
            <Button variant={'contained'} onClick={handleSave} disabled={topic === '' || text===''}>Save</Button>
            <Button variant={'outlined'} onClick={handleClear}>Clear</Button>
          </FormControl>
        </Box>
      </Container>
    </React.Fragment>
  )
}
