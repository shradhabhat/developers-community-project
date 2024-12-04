import './App.css';
import Menu from './components/Menu'
import { BrowserRouter } from 'react-router-dom';
function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Menu />
      </BrowserRouter>
    </div>
  );
}

export default App;
