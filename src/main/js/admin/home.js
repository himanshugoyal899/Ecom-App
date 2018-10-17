import React from 'react';
import SeeDiv from '../commons/see-div';
import Profile from './profile';
import Sellers from './sellers';
import Products from './products';
import {BrowserRouter as Router, Link, Route} from "react-router-dom";

class Home extends React.Component {

    render() {
        return (
            <h1>Welcome Home!</h1>
        )
    }
}

export default Home;