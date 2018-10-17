import React, { Component } from "react";
import axios from 'axios';
class Details extends Component {

    constructor(props) {
        super(props);

        this.state = {};
    }
        updateDetails(){
            let obj = this.props.user;
                obj.firstName = document.getElementById("FirstName").textContent;
            obj.lastName =  document.getElementById("LastName").textContent;
            obj.emailId =  document.getElementById("Email").textContent;
            console.log("obj = ",obj);
            axios.put(`api/seller/${obj.username}`, obj);
        }

    render() {
        if (!this.props.show) {
            return null;
        }
        return (
            <div align="center">
                <form
                    style={{
                        width: 400,
                        padding: 50,
                        transform: "scale(1.25)"
                    }}
                >   <div className="form-group">
                    <label>Username : </label>
                    <div className="form-control" id="UserName"  style={{"background-color": "#AFEEEE"}} ><b>{this.props.user.username}</b></div>
                </div>
                        <div className="form-group">
                            <label>First Name : </label>
                            <div className="form-control" contentEditable="true" id="FirstName" >{this.props.user.firstName}</div>
                        </div>
                        <div className="form-group">
                            <label>Last Name : </label>
                            <div className="form-control" id="LastName"  contentEditable="true" >{this.props.user.lastName}</div>
                        </div>

                        <div className="form-group">
                            <label>Email : </label>
                            <div className="form-control" id="Email"  contentEditable="true" >{this.props.user.emailId}</div>
                        </div>
                        <button type="button" className="btn btn-primary btn-lg" onClick={()=>this.updateDetails()}>
                            Update
                        </button>

                </form>
            </div>
        );
    }
}

export default Details;
