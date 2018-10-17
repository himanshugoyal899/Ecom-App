import React from "react";
import "./App.css";
//import Loader from "./loaders/loader";
import Signup from "./Signup";
import { BrowserRouter, Route, Redirect } from "react-router-dom";
import Homepage from "./Homepage";
import Inventory from "./inventory";
import Details from "./details";

class App extends React.Component {
    constructor(){
        super();
        this.state = {
            data: [],

            open: false
        };}
    signUp (obj) {
        this.setState({ data: [...this.state.data, obj] });
        window.alert("Registered!");
    };
    signIn (obj) {
        for (let i = 0; i < this.state.data.length; i++) {
            if (
                this.state.data[i].username === obj.username &&
                this.state.data[i].password === obj.password
            ) {
                window.alert("Successfully Signed In!");
                return true;
            } else {
                window.alert("Username / Password is incorrect!");
                return false;
            }
        }
    };
    render() {
        return (
            <BrowserRouter>
                <div>
                    <Route exact path="/home" component={Seller} />
                    <Route
                        exact
                        path="/"
                        render={() => <Signup signin={this.signIn} signup={this.signUp} />}
                    />
                    <Route exact path="/inventory" component={Inventory} />
                    <Route exact path="/details" component={Details} />
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
