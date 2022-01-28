import './App.css';
import AppInfo from "./components/appInfo.js";
import QuestionsList from "./components/QuestionsList.js";

function App() {
  return (<>
    <div className='topnav'>
      <a href="#">Questions</a>
      <a href="#">About</a>
    </div>

    <div className='content'>
      <QuestionsList/>
    </div>
    <div className='footer'>
      <AppInfo/>
    </div>
  </>);
}

export default App;
