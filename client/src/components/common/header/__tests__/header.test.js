import Header from '../header'
import renderer from 'react-test-renderer'
import {Typography} from "@mui/material";

describe('<Header />', () => {
  test('snapshot', () => {
    // when
    const component = renderer.create(<Header text={'Header text'}/>)

    // then
    const tree = component.toJSON()
    expect(tree).toMatchSnapshot();
  })

  test('should render correctly', () => {
    // given
    const text = 'Header text'

    // when
    const testRenderer = renderer.create(<Header text={text}/>)
    const testInstance = testRenderer.root

    // then
    expect(testInstance.findByType(Typography).props.variant).toBe('h3');
    expect(testInstance.findByType(Typography).props.component).toBe('div');

    expect(testInstance.findByType(Typography).props.children).toEqual(text)
  })
})
