import {
  Box,
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
    text: null
  })

  useEffect(() => {
    ServiceCategories.getAllCategories()
      .then(resp => setCategories(resp.data))
      .catch(err => console.error(err))
    setQuestionData({})
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

  const handleSave = () => {
    ServiceQuestions.saveQuestion(questionData).then(() => onCancel.call())
  }

  const noValues = questionData?.category?.id || questionData?.text?.length === 0

  console.info(noValues)

  return (
    <Dialog disableEscapeKeyDown open={open} onClose={onCancel}>
      <DialogTitle>Add New Question</DialogTitle>
      <DialogContent>
        <Box component="form" sx={{ display: 'flex', flexWrap: 'wrap' }}>
          <FormControl sx={{ m: 1, minWidth: 300 }}>
            <InputLabel htmlFor="category">Category</InputLabel>
            <Select native onChange={handleCategoryChange} input={<OutlinedInput label="Category" id="category"/>}>
              <option aria-label="None" value=""/>
              {
                categories.map(category => {
                  const { id, name } = category
                  return <option value={id}>{name}</option>
                })
              }
            </Select>
            <TextField id="text" label="Text" variant="outlined" onChange={handleTextChange}/>
          </FormControl>
        </Box>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleSave} disabled={noValues}>Save</Button>
        <Button onClick={() => onCancel(questionData)}>Cancel</Button>
      </DialogActions>
    </Dialog>
  )
}
