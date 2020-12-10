import './App.css';
import {Container} from 'react-bootstrap'
import Header from './Components/Header'
import Footer from './Components/Footer'
import ProductScreen from './Screens/ProductScreen'
import PurchaseScreen from './Screens/PurchaseScreen'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

function App() {
  return (
    <Router>
      <Header/>
        <main>
        <Container>
          <Switch>
            <Route path='/' component={ProductScreen} exact/>
            <Route path='/purchase/:productUuid' component={PurchaseScreen} />
          </Switch>
        </Container>
        </main>
      <Footer/>
    </Router>
  );
}

export default App;
