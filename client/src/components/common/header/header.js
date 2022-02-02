import { Typography } from '@mui/material'
import PropTypes from 'prop-types'
import React from 'react'

export default function Header(props) {
  const { text } = props
  return (<>
    <Typography variant="h3" component="div">
      {text}
    </Typography>
  </>)
}

Header.propTypes = {
  text: PropTypes.string.isRequired
}
