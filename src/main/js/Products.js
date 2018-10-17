import React, { Component } from "react";
import axios from 'axios';
import PageNumbers from "./PageNumbers";
import Inventory from "./inventory";
class Products extends Component {
    constructor(props){
        super(props);
        this.state = {
            showD: false,
            products: null,
            currentPage: 1,
            inventory: []
        };
    }
    /*<ProductDetails inventory={this.state.inventory} isOpen={this.state.showD} isClose={()=>this.hideDetails()} product={product}/>*/
    // showDetails ()  {
    //     this.setState({ showD: true });
    //
    // };
    //
    // hideDetails ()  {
    //     this.setState({ showD: false });
    // };
    inInventory(prod){
        for(let i = 0; i  < this.state.inventory.length; i++)
        {
            if(this.state.inventory[i].product.id - prod.id === 0)
            {
                return true;
            }
        }

        return false;
            }
    pageNumbers() {
        var arr = [];
        for (
            let i = 1;
            i <= Math.ceil(this.state.products.length / 10);
            i++
        ) {
            arr.push(i);
        }
        return arr;
    }

    addToInventory(product){
        const username = this.props.user.username;
        const productId = product.id;
        axios.post(`/api/seller/${username}/inventory/${productId}`, { "quantity": 0, "price": 0})
            .then(response=>{
                this.getInventory();
                this.props.reloadInventory();

            })
    }
    getProducts(){

        axios.get("/api/products").then((response) => {this.setState({products: response.data})});
    }
    getInventory(){
        this.getProducts();
        axios.get(`api/seller/${this.props.user.username}/inventory/products`)
            .then((response)=>{
                this.setState({inventory: response.data.slice()});
            });
    }
    componentDidMount(){
        this.getInventory();
    }
    render() {
        if (!this.props.show || this.state.products === null) {
                return null;
            }

        let currentList = this.state.products;
        let end = this.state.currentPage*10;
        currentList = currentList.slice(end - 10, end);
        return (
            <div className="container">
                <div className="row">
                    <div className="table-responsive table-bordered movie-table">
                        <table className="table movie-table">
                            <thead>
                            <tr className="table-active">
                                <th width="60">
                                    <h4>
                                        <strong>
                                            <div className="text-primary">Product ID</div>
                                        </strong>
                                    </h4>
                                </th>
                                <th>
                                    <div align="center">
                                        <div className="text-primary">
                                            <h4>
                                                <strong>Category</strong>
                                            </h4>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div align="center">
                                        <div className="text-primary">
                                            <h4>
                                                <strong>Brand</strong>
                                            </h4>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div align="center">
                                        <div className="text-primary">
                                            <h4>
                                                <strong>Product Name</strong>
                                            </h4>
                                        </div>
                                    </div>
                                </th>
                                <th>
                                    <div align="center">
                                        <div className="text-primary">
                                            <h4>
                                                <strong>Action</strong>
                                            </h4>
                                        </div>
                                    </div>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            {currentList.map(product => (<tr className="dark-row">
                                <td>
                                    <strong>
                                        <h3>{product.id}</h3>
                                    </strong>
                                </td>
                                <td align="center">
                                    <h3>
                                        <b>{product.category}</b>
                                    </h3>
                                </td>
                                <td align="center">
                                    <h3>
                                        <b>{product.brandName}</b>
                                    </h3>
                                </td>
                                <td align="center">
                                    <h3>
                                        {product.name}
                                    </h3>
                                </td>
                                <td align="center">
                                    <button
                                        className="btn btn-success btn-lg"
                                        disabled={this.inInventory(product)}
                                        id={`add_button_${product.id}`}
                                        onClick={()=>{
                                            this.addToInventory(product)}}

                                    >
                                        <b>ADD</b>
                                    </button>

                                    </td>
                            </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                </div>


                <div align="center"> <PageNumbers currentPage={this.state.currentPage}
                                                  previousClicked={() => {

                                                      this.setState({ currentPage: this.state.currentPage - 1 } );

                                                  }}
                                                  nextClicked={() => {
                                                      this.setState({ currentPage: this.state.currentPage + 1 });

                                                  }}
                                                  nextDisabled={()=>{
                                                      return this.state.currentPage ===
                                                          Math.ceil(this.state.products.length / 10)}}
                                                  pageClicked={(i) => {
                                                      this.setState({ currentPage: i });
                                                  }}
                                                  pageNumbers={()=>this.pageNumbers()}
                /></div>
            </div>
        );
    }
}

export default Products;
