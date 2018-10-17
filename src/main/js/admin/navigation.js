import {BrowserRouter as Router, Link, Route} from "react-router-dom";
import SeeDiv from "../commons/see-div";
import Home from "./home";
import Profile from "./profile";
import Sellers from "./sellers";
import Products from "./products";
import React from "react";

function Navigation() {
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

                <Route exact path="/" component={Home}/>
                <Route path="/profile" component={Profile}/>
                <Route path="/sellers" component={Sellers}/>
                <Route path="/products" component={Products}/>

            </div>
        </Router>
    )
}

export default Navigation;