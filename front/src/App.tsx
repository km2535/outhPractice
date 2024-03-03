import './App.css';
import { Route, Routes } from 'react-router-dom';
import SignUp from './views/Authentication/signUp';
import SignIn from './views/Authentication/signIn';

function App() {
  return (
    <Routes>
      <Route path='/auth'>
        <Route path='sign-up' element={<SignUp/>}></Route>
        <Route path='sign-in' element={<SignIn/>}></Route>
     </Route>
    </Routes>
  );
}

export default App;
