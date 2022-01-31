import React from "react";
import FileDownload from "js-file-download"
import QuestionsService from "../../service/questionsService";
import Question from "./question";

class QuestionsContainer extends React.Component {

  state = {
    questions: [], selected: new Set()
  }

  componentDidMount() {
    QuestionsService.getQuestions().then(resp => {
      const questions = resp.data
      this.setState({questions})
    }).catch(err => console.error(err))
  }

  handleOnChange = (item) => {
    const {selected} = this.state
    const {id} = item.target
    if (selected.has(id)) {
      selected.delete(id)
    } else {
      selected.add(id)
    }
    this.setState({selected})
  }

  handleExport = () => {
    const {selected} = this.state
    QuestionsService.exportQuestions([...selected])
      .then(resp => FileDownload(resp.data, 'export_' + Date.now().valueOf() + ".txt"))
      .catch(err => console.error(err))
  }

  render() {
    const {questions, selected} = this.state
    const hasQuestions = questions && questions.length > 0;
    const hasSelectedItems = selected && selected.size > 0;
    return <>
      {hasQuestions ? <h1>List of Questions</h1> : <h1>No Questions</h1>}
      {
        questions
          .sort((qOne, qTwo) => {
            const textOne = qOne.text
            const textTwo = qTwo.text
            return textOne.localeCompare(textTwo)
          })
          .map(item => {
            const {id, text} = item
            return <Question key={id} id={id} text={text} onChange={this.handleOnChange}/>
          })
      }
      {hasQuestions && <div>
        <button type={"button"} onClick={this.handleExport} disabled={!hasSelectedItems}>Export</button>
      </div>}
    </>
  }
}

export default QuestionsContainer
