import {Typography} from "@mui/material";
import React from "react";
import PropTypes from "prop-types";

export default function Header(props) {
  const {text} = props
  return (<>
      <Typography variant="h3" component="div">
        {text}
      </Typography>
    </>
  )
}

Header.propTypes = {
  text: PropTypes.string.isRequired
};
