import React from 'react';
import axios from 'axios';
import ListItems from './ListItems';
import client from "../commons/client";

class Sellers extends React.Component {
    constructor() {
        super();
        this.state = {
            sellerList: null,
            receivedData: false,
        };
    }

    handleOnClickListItem(i) {
        console.log(`Clicked on ${i}`);
    }

    handleOnClickDeleteButton(sellerId, index) {
        console.log(`Clicked on delete button of ${sellerId}`);

        axios.delete("api/admin/sellers", {
                params: {
                    sellerId: sellerId
                }
            })
            .then(response => {
                console.log(response);

                this.getData();
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    handleOnClickEnableDisableSellerButton(sellerId, index) {
        console.log(`Clicked on enable or disable button of ${sellerId}`);

        console.log(this.state.sellerList);

        let seller = this.state.sellerList[index];



        axios.put(`api/admin/sellers/${sellerId}`, {
                enabled: !seller.enabled
            })
            .then(response => {
                console.log(response);

                this.getData();
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    getData() {
        client({method: 'GET', path: 'api/admin/sellers'}).done(response => {
            console.log(response);
            this.setState({
                sellerList: response.entity.slice(),
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
            )
        }

        return (
          <div id="seller-list">
              <ListItems data={this.state.sellerList}
                         onClickListItem={(i) => this.handleOnClickListItem(i)}
                         onClickDeleteButton={(sellerId, index) => this.handleOnClickDeleteButton(sellerId, index)}
                         onClickEnableDisableSellerButton={(sellerId, index) => this.handleOnClickEnableDisableSellerButton(sellerId, index)}
              />
          </div>
        );
    }
}

export default Sellers;