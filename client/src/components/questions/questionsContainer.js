import React from 'react';
import FileDownload from 'js-file-download'
import QuestionsService from '../../service/questionsService';
import Question from './question';
import { Button, Container, Typography } from '@mui/material';
import Header from '../common/header/header';

class QuestionsContainer extends React.Component {

  state = {
    questions: [], selected: new Set()
  }

  componentDidMount() {
    QuestionsService.getQuestions().then(resp => {
      const questions = resp.data
      this.setState({ questions })
    }).catch(err => console.error(err))
  }

  handleOnChange = (item) => {
    const { selected } = this.state
    const { id } = item.target
    if (selected.has(id)) {
      selected.delete(id)
    } else {
      selected.add(id)
    }
    this.setState({ selected })
  }

  handleExport = () => {
    const { selected } = this.state
    QuestionsService.exportQuestions([...selected])
      .then(resp => FileDownload(resp.data, 'export_' + Date.now().valueOf() + '.txt'))
      .catch(err => console.error(err))
  }

  render() {
    const { questions, selected } = this.state
    const hasQuestions = questions && questions.length > 0;
    const hasSelectedItems = selected && selected.size > 0;
    return <>
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
            return <Question id={id} key={id} text={text} onChange={this.handleOnChange}/>
          })
      }
      {hasQuestions &&
        <Button variant="contained" onClick={this.handleExport} disabled={!hasSelectedItems}>Export</Button>
      }
      </Container>
    </>
  }
}

export default QuestionsContainer
