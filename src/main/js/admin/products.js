import React from 'react';
const ReactDOM = require('react-dom');
import ProductListItems from './ProductListItems';
import axios from "axios";

class Products extends React.Component {
    constructor() {
        super();
        this.state = {
            productList: null,
            receivedData: false
        };
    }

    handleOnClickDeleteButton(productId) {
        console.log(`Clicked on delete button of ${productId}`);

        axios.delete(`api/admin/products/${productId}`)
            .then(response => {
                console.log(response);

                this.getData();
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    getData() {


        axios.get("api/products")
            .then((response) => {
                console.log(response);

                this.setState({
                    productList: response.data.slice(),
                    receivedData: true
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
            <div id="product-list">
                <ProductListItems data={this.state.productList}
                                  onClickDeleteButton={(productId) => this.handleOnClickDeleteButton(productId)}/>
            </div>
        )
    }
}

export default Products;