import './App.css';
import AppInfo from "./components/appInfo.js";
import QuestionsContainer from "./components/questions/questionsContainer.js";

function App() {
  return (<>
    <div className='topnav'>
      <a href="#">Questions</a>
      <a href="#">About</a>
    </div>

    <div className='content'>
      <QuestionsContainer/>
    </div>
    <div className='footer'>
      <AppInfo/>
    </div>
  </>);
}

export default App;
