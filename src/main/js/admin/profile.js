import React from 'react';
import client from '../commons/client';


class Profile extends React.Component {
    constructor() {
        super();
        this.state = {
            username: "",
            firstName: "",
            lastName: "",
            emailId: "",
            receivedData: false,
        };
    }

    getData() {
        client({method: 'GET', path: 'api/admin/profile'}).done(response => {

            this.setState({
                username: response.entity.username,
                firstName: response.entity.firstName,
                lastName: response.entity.lastName,
                emailId: response.entity.emailId,
                receivedData: true,
            })
        });
    }

    componentDidMount() {
        this.getData();
    }

    render() {
        if (this.state.receivedData === false) {
            return (
              <React.Fragment></React.Fragment>
            );
        }

        return (
            <div id="admin-profile">
                <p><strong>Username: </strong>{this.state.username}</p>
                <p><strong>First Name: </strong>{this.state.firstName}</p>
                <p><strong>Last Name: </strong>{this.state.lastName}</p>
                <p><strong>Email Id: </strong>{this.state.emailId}</p>
            </div>
        )
    }
}

export default Profile;