import React from "react";
import Modal from "react-responsive-modal";
import { withRouter } from "react-router-dom";
import Homepage from "../Homepage";

class SignIn extends React.Component {

    constructor(props){
     super(props);

        this.state = {};
    }

    signIn ()  {
        let username = document.getElementById("signin_username").value;
        let password = document.getElementById("signin_password").value;
        const obj = { username, password };
        return this.props.signin(obj);
    };
    handleSignIn ()  {
        if (this.signIn()) {
            console.log("inside handlesignin");
            console.log("props ", this.props);
            this.props.history.push("/home");
        }
    };
    render() {
        return (
            <div>
                <Modal
                    id="signin"
                    open={this.props.isOpen}
                    onClose={this.props.closeModal}
                >
                    <form>
                        <div>
                            <label htmlFor="username">User Name : </label>
                            <input
                                type="text"
                                className="form-control"
                                id="signin_username"
                            />
                            <br />

                            <div>
                                <label htmlFor="signin_password">Password : </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="signin_password"
                                />
                            </div>
                            <button
                                type="button"
                                id="Sign_in"
                                className="btn btn-success"
                                onClick={this.handleSignIn}
                            >
                                Sign In
                            </button>
                        </div>
                    </form>
                </Modal>
            </div>
        );
    }
}

export default withRouter(SignIn);
