import React, { Component } from "react";
import axios from 'axios';
import PageNumbers from './PageNumbers';
class Inventory extends React.Component {
    constructor(props){
        super(props);

        this.state = {
            inventory: [],
            currentPage: 1
        };
    }

     getData() {
        axios.get(`api/seller/${this.props.user.username}/inventory/products`)
            .then((response)=>{
                this.setState({inventory: response.data});

            });
    }

    componentDidMount(){
        this.getData();
            }


    updateItem(item, newPrice){
        let arr = this.state.inventory;
        for(let i = 0; i < arr.length; i++)
        {
            if(arr[i].id == item.id)
            {
                arr[i].price = newPrice;
                break;
            }

        }
        this.setState({inventory: arr});

        // Update inventory
        const username = item.user.username;
        const inventoryId = item.id;
        axios.put(`api/seller/${username}/inventory/${inventoryId}`, {
            price: newPrice,
            quantity: item.quantity
        }).then(response => {
            console.log(response);
            this.getData();
        })

    }
    decreaseQuantity(item){

        let arr = this.state.inventory;
        for(let i = 0; i < arr.length; i++)
        {
            if(arr[i].id === item.id)
            {
                arr[i].quantity -= 1;
                break;
            }

        }
        this.setState({inventory: arr});
    }
    increaseQuantity(item){

        let arr = this.state.inventory;
        for(let i = 0; i < arr.length; i++)
        {
            if(arr[i].id === item.id)
            {
                arr[i].quantity += 1;
                break;
            }
        }
        this.setState({inventory: arr});

    }
    deleteItem(item){
        const username = item.user.username;
        const inventoryId = item.id;
        axios.delete(`api/seller/${username}/inventory/${inventoryId}`).then(response=>{
            this.props.reloadProducts();
            this.getData();})
    }
    pageNumbers() {
        var arr = [];
        for (
            let i = 1;
            i <= Math.ceil(this.state.inventory.length / 10);
            i++
        ) {
            arr.push(i);
        }
        return arr;
    }
    getBorderClass(value){

        //const x = `price_field_${item.id}`;
        console.log("x ");
        //const content = parseInt(document.getElementById("x").textContent);
        //console.log("content ", content);
        //return parseInt(document.getElementById(`price_field_${item.id}`).textContent) > 0 ? "border border-primary" : "border border-danger";
        //return value > 0 ? "border border-primary" : "border border-danger";
    }


    render() {

        if (this.state.inventory.length === 0) {
            return <div></div>;
        }

        if (!this.props.show) {
            return null;
        }

        let currentList = this.state.inventory;
        let end = this.state.currentPage * 10;
        currentList = currentList.slice(end - 10, end);


        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <div className="table-responsive">
                                <table id="mytable" className="table table-striped">
                                    <thead className="thead-dark" style={{ fontSize: 15}}>
                                    <th>Product Name</th>
                                    <th style={{"text-align": "center"}}>Product ID</th>
                                    <th style={{"text-align": "center"}}>Price</th>
                                    <th style={{"text-align": "center"}}>Quantity</th>

                                    <th style={{"text-align": "center"}}>Action</th>
                                    </thead>

                                    <tbody style={{ fontSize: 16}}>
                                    {currentList.map(item =>(

                                    <tr key={item.id}>
                                        <td>{item.product.name}</td>
                                        <td style={{"text-align": "center"}}>{item.product.id}</td>
                                        <td style={{"text-align": "center"}}>
                                            <div id={`price_field_${item.id}`} style={{"background-color": "snow"}}
                                          className="border border-primary"
                                          contentEditable="true">{item.price}</div></td>
                                        <td style={{"text-align": "center"}}>
                                            <p>
                                                <button className="btn btn-secondary btn-xs" disabled={item.quantity === 0} id="decrement_button" onClick={()=>this.decreaseQuantity(item)}>
                                                    <span className="glyphicon glyphicon-minus" />
                                                </button>
                                                <span id={`quantity_field_${item.id}`} value={item.quantity} className="badge badge-light m-2" style={{width: 30}}>
                            {item.quantity}
                          </span>

                                                <button className="btn btn-secondary btn-xs" onClick={()=>this.increaseQuantity(item)}>
                                                    <span className="glyphicon glyphicon-plus" />
                                                </button>
                                            </p>
                                        </td>
                                        <td style={{"text-align": "center"}}>
                                            <button className="btn btn-success btn-lg m-2"
                                            onClick={()=>this.updateItem(item, parseInt(document.getElementById(`price_field_${item.id}`).textContent))}
                                        >
                                                <strong>Update</strong>
                                            </button>
                                            <button className="btn btn-danger btn-lg m-2" onClick={()=>this.deleteItem(item)}>
                                                <strong>
                                                    <span className="glyphicon glyphicon-trash" />
                                                </strong>
                                            </button>

                                        </td>
                                    </tr>)
                                    )}

                                    </tbody>
                                </table>
                               <div align="center"> <PageNumbers currentPage={this.state.currentPage}
                                             previousClicked={() => {
                                    this.setState({ currentPage: this.state.currentPage - 1 } );

                                }}
                                             nextClicked={() => {
                                    this.setState({ currentPage: this.state.currentPage + 1 });

                                             }}
                                nextDisabled={()=>{
                                    return this.state.currentPage ===
                                    Math.ceil(this.state.inventory.length / 10)}}
                                             pageClicked={(i) => {
                                                 this.setState({ currentPage: i });
                                             }}
                                             pageNumbers={()=>this.pageNumbers()}
                               /></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default Inventory;
