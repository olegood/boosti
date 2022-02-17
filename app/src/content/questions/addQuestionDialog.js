import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  FormControl,
  InputLabel,
  OutlinedInput,
  Select,
  TextField
} from '@mui/material'
import { useEffect, useState } from 'react'
import ServiceCategories from '../../service/serviceCategories.js'
import ServiceQuestions from '../../service/serviceQuestions.js'

export default function AddQuestionDialog({ open, onSave, onCancel }) {

  const [categories, setCategories] = useState([])

  const [questionData, setQuestionData] = useState({
    category: {
      id: null
    },
    text: null,
    answer: null
  })

  useEffect(() => {
    ServiceCategories.getAllCategories()
      .then(resp => setCategories(resp.data))
      .catch(err => console.error(err))
  }, [])

  const handleCategoryChange = (event) => {
    const { value } = event.target
    setQuestionData(() => ({
        ...questionData,
        category: {
          id: (value === '' ? null : value)
        }
      })
    )
  }

  const handleTextChange = (event) => {
    const { value } = event.target
    setQuestionData(() => ({
        ...questionData,
        text: value
      })
    )
  }

  const handleAnswerChange = (event) => {
    const { value } = event.target
    setQuestionData(() => ({
        ...questionData,
        answer: value
      })
    )
  }

  const handleSave = () => {
    ServiceQuestions.saveQuestion(questionData).then(() => onCancel.call())
  }

  return (
    <Dialog disableEscapeKeyDown open={open} onClose={onCancel} maxWidth={'md'} fullWidth={true}>
      <DialogTitle>New Question</DialogTitle>
      <DialogContent>
        <FormControl>
          <InputLabel htmlFor="category">Category</InputLabel>
          <Select sx={{ minWidth: 150 }} native onChange={handleCategoryChange}
                  input={<OutlinedInput label="Category" id="category"/>}>
            <option aria-label="None" value=""/>
            {
              categories.map(category => {
                const { id, name } = category
                return <option value={id}>{name}</option>
              })
            }
          </Select>
          <TextField id="text" label="Text" variant="outlined" onChange={handleTextChange}/>
          <TextField id="answer" label="Answer" variant="outlined" onChange={handleAnswerChange}/>
        </FormControl>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleSave}>Save</Button>
        <Button onClick={() => onCancel(questionData)}>Cancel</Button>
      </DialogActions>
    </Dialog>
  )
}
