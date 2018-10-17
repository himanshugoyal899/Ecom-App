const React = require('react');
const ReactDOM = require('react-dom');
import axios from 'axios';

import {
    BrowserRouter,
    Route,
    Redirect,
    Link,
    withRouter
} from "react-router-dom";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Inventory from "./inventory";
import Details from "./details";
import Products from "./Products";


class Seller extends React.Component {

constructor() {
    super();
    this.state = {
        showI: true,
        showA: false,
        showP: false,
        user: null,
        received: false
    };
}

    showInventory(){
        this.setState({
            showI: true,
            showP: false,
            showA: false
        });
    };

    showProducts() {
        this.setState({
            showI: false,
            showP: true,
            showA: false
        });
    };


    showDetails () {
        this.setState({
            showI: false,
            showP: false,
            showA: true
        });
    };
    getBadgesI () {
        return this.state.showI ? "badge badge-pill badge-light" : "";
    };
    getBadgesP ()  {
        return this.state.showP ? "badge badge-pill badge-light" : "";
    };
    getBadgesA ()  {
        return this.state.showA ? "badge badge-pill badge-light" : "";
    };
    signOut  ()  {
        window.open("/logout", "_self");
        this.props.history.push("/logout");
    };

    reloadInventory() {
        this.child_i.getData();
    }

    reloadProducts() {
        console.log("getProducts...");
        this.child_p.getInventory();
    }

    componentDidMount(){
        axios.get("api/seller/profile")
            .then((response) => {
                this.setState({
                    user: response.data,
                    received: true,
                })
            })
    }
    render() {
        if (this.state.received === false) {
            return (
                <React.Fragment></React.Fragment>
            )
        }

        return (
            <div>
                <div className="fixed-top">
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark" >s
                    <a className="navbar-brand" href="#">
                        <div className="display-4 m-2" id="dashboard">
                            Hello, {this.state.user.username}
                        </div>
                    </a>
                    <button
                        className="navbar-toggler m-4"
                        type="button"
                        data-toggle="collapse"
                        data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent"
                        aria-expanded="true"
                        aria-label="Toggle navigation"
                    >
                        <span className="navbar-toggler-icon" />
                    </button>
                    <div className="collapse navbar-collapse">
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item" onClick={()=>this.showInventory()}>
                                <a className="nav-link" href="#">
                                    <div className="navbar-text">
                    <span id="inv1" className={this.getBadgesI()}>
                      <h2 id="inv"> Inventory</h2>
                    </span>
                                    </div>
                                </a>
                            </li>

                            <li className="nav-item" onClick={() => this.showProducts()}>
                                <a className="nav-link" href="#">
                                    <div className="navbar-text">
                    <span id="pro" className={this.getBadgesP()}>
                      <h2 id="pro1">Products</h2>
                    </span>
                                    </div>
                                </a>
                            </li>
                            <li className="nav-item" onClick={()=>this.showDetails()}>
                                <a className="nav-link" href="#">
                                    <div className="navbar-text">
                    <span className={this.getBadgesA()}>
                      <h2>Account Details</h2>
                    </span>
                                    </div>
                                </a>
                            </li>
                        </ul>
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                <a className="nav-link" href="#">
                                    <div className="navbar-text" onClick={()=>this.signOut()}>
                                        <h2>
                                            <span className="glyphicon glyphicon-log-out" /> Sign Out
                                        </h2>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
                    </div>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <Inventory show={this.state.showI} user={this.state.user} ref={instance => { this.child_i = instance;}}
                reloadProducts={()=>{this.reloadProducts()}}/>
                <Details show={this.state.showA} user={this.state.user}/>
                <Products show={this.state.showP} user={this.state.user} ref={instance => { this.child_p = instance;}}
                          reloadInventory={()=>this.reloadInventory()}/>
            </div>
        );
    }
}

export default withRouter(Seller);


ReactDOM.render(
    <Seller />,
    document.getElementById('react')
)