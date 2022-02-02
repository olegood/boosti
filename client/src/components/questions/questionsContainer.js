import { Button, Container, Typography } from '@mui/material'
import FileDownload from 'js-file-download'
import React, { useEffect, useState } from 'react'
import QuestionsService from '../../service/questionsService'
import Header from '../common/header/header'
import Question from './question'

/**
 * Will be cleaned up in future releases.
 *
 * @deprecated
 * @see #24
 */
export default function QuestionsContainer() {

  const [questions, setQuestions] = useState([])
  const [selected, setSelected] = useState(new Set())

  useEffect(() => {
    QuestionsService.getQuestions().then(resp => {
      const questions = resp.data
      setQuestions(questions)
    }).catch(err => console.error(err))
  }, [])

  const handleOnChange = (item) => {
    const { id } = item.target
    selected.has(id) ? selected.delete(id) : selected.add(id)
    setSelected(selected)
  }

  const handleExport = () => {
    QuestionsService.exportQuestions([...selected])
      .then(resp => FileDownload(resp.data, 'export_' + Date.now().valueOf() + '.txt'))
      .catch(err => console.error(err))
  }

  const hasQuestions = questions && questions.length > 0
  const hasSelectedItems = selected && selected.size > 0
  return (<>
    <Container maxWidth="xl">
      <Header text={'List of Questions'}/>
      {!hasQuestions ?
        <Typography variant="body1" display="block">
          No questions.
        </Typography>
        : ''}
      {
        questions
          .sort((qOne, qTwo) => {
            const textOne = qOne.text
            const textTwo = qTwo.text
            return textOne.localeCompare(textTwo)
          })
          .map(item => {
            const { id, text } = item
            return <Question id={id} key={id} text={text} onChange={handleOnChange}/>
          })
      }
      {hasQuestions &&
        <Button variant="contained" onClick={handleExport} disabled={!hasSelectedItems}>Export</Button>
      }
    </Container>
  </>)
}
