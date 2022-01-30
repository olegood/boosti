import PropTypes from 'prop-types';
import React from "react";

function Question(props) {
  const {id, text, onChange} = props
  return (
    <>
      <input type={"checkbox"} id={id} value={id} onChange={onChange}/>
      <label htmlFor={id}> {text}</label><br/>
    </>
  )
}

Question.propTypes = {
  id: PropTypes.number.isRequired,
  text: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired
};

export default Question