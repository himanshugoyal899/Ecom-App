import React, { Component } from "react";
import axios from 'axios';
class PageNumbers extends Component{
    constructor(props){
        super(props);
    }


    getButtonClass(i) {
        let classes = "btn btn-md btn-";
        classes += this.props.currentPage === i ? "dark" : "default";
        return classes;
    };

    render(){

        return (<div>
            <button
                className="btn btn-primary btn-lg m-4"
                disabled={this.props.currentPage === 1}
                onClick={this.props.previousClicked}
            >
                <b>Previous</b>
            </button>
        {this.props.pageNumbers().map(i => (
            <button
                key={i}
                className={this.getButtonClass(i)}
                onClick={()=>{this.props.pageClicked(i)}}
            >
                <strong> {i}</strong>
            </button>
        ))}
        <button
            disabled={this.props.nextDisabled()
            }
            className="btn btn-warning btn-lg m-4"
            onClick={this.props.nextClicked}
        >
            <b>Next</b>
        </button>
            </div>

        )
    }
}
export default PageNumbers;