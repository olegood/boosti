import { Checkbox, FormControlLabel, FormGroup } from '@mui/material'
import PropTypes from 'prop-types'
import React from 'react'

export default function Question(props) {
  const { id, text, onChange } = props
  return (<>
      <FormGroup>
        <FormControlLabel control={<Checkbox id={`${id}`} value={id} onChange={onChange}/>} label={text}/>
      </FormGroup>
    </>
  )
}

Question.propTypes = {
  id: PropTypes.number.isRequired,
  text: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired
}
