import React from "react";
import QuestionsService from "../service/QuestionsService";

class QuestionsList extends React.Component {

  state = {
    questions: []
  }

  constructor(props) {
    super(props);
  }

  componentWillMount() {
    QuestionsService.getAll().then(resp => {
      const questions = resp.data
      this.setState({questions})
    }).catch(err => console.error(err))
  }

  createList = (questions) => {
    const result = [];
    for (const key in questions) {
      result.push(<h1>{key}</h1>)
      const arr = questions[key]
      arr.forEach(it => result.push(<p>{it.text}</p>))
    }
    if (result.length === 0) {
      result.push(<h1>No Questions</h1>)
    }
    return result
  }

  render() {
    const {questions} = this.state
    return <>{this.createList(questions)}</>
  }
}

export default QuestionsList