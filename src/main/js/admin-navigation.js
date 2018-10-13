import {BrowserRouter as Router, Link, Route} from "react-router-dom";
import SeeDiv from "./see-div";
import AdminHome from "./admin-home";
import AdminProfile from "./admin-profile";
import AdminSellers from "./admin-sellers";
import AdminProducts from "./admin-products";
import React from "react";


function AdminNavigation() {
    return (
        <Router>
            <div>
                <div>
                    <Link to="/">
                        <SeeDiv id="home"
                                body="Home"
                        />
                    </Link>


                    <Link to="/profile">
                        <SeeDiv id="see-profile"
                                body="My profile"
                        />
                    </Link>

                    <Link to="/sellers">
                        <SeeDiv id="see-sellers"
                                body="See Sellers"
                        />
                    </Link>

                    <Link to="/products">
                        <SeeDiv id="see-products"
                                body="See Products"
                        />
                    </Link>
                </div>

                <Route exact path="/" component={AdminHome}/>
                <Route path="/profile" component={AdminProfile}/>
                <Route path="/sellers" component={AdminSellers}/>
                <Route path="/products" component={AdminProducts}/>

            </div>
        </Router>
    )
}

export default AdminNavigation;