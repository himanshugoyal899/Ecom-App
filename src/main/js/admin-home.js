import React from 'react';
import SeeDiv from './see-div';
import AdminProfile from './admin-profile';
import AdminSellers from './admin-sellers';
import AdminProducts from './admin-products';
import {BrowserRouter as Router, Link, Route} from "react-router-dom";

class AdminHome extends React.Component {

    render() {
        return (
            <h1>Welcome Home!</h1>
        )
    }
}

export default AdminHome;