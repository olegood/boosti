import Header from "../header";
import {render} from '@testing-library/react'

test.skip('header should have text', () => {
  const {getByRole, debug} = render(<Header text={'This is a header'}/>)
  //expect(result).toContain(/This is a header/i)
  fail('Not implemented yet.')
})
