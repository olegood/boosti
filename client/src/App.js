import './App.css';
import QuestionsList from "./components/QuestionsList.js";

function App() {
  return (<>
    <div className={'topnav'}>
      <a href="#">Questions</a>
      <a href="#">About</a>
    </div>

    <div className={"content"}>
      <QuestionsList/>
    </div>
    <div className={'footer'}>
      <p>Version: dev</p>
    </div>
  </>);
}

export default App;
