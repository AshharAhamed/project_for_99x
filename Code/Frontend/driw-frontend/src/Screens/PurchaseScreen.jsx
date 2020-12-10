import React, { Component } from 'react'
import { BrowserRouter, Route, Link } from "react-router-dom";
import {Row, Col, ListGroup, Card, Button} from 'react-bootstrap'
import ProductService from '../Services/ProductService'
import NumericInput from 'react-numeric-input';

export default class PurchaseScreen extends Component {

    constructor(props) {
        super(props);
        this.state = {
            initial: 'state',
            product: '',
            productDTO:null,
            valByProduct:null
        };
        this.productService = new ProductService();
        this.loadProduct(this.props.match.params.productUuid);
        this.onNumberChange = this.onNumberChange.bind(this)
    }

    loadProduct(uuid) {
        this.productService.getProductByUuid(uuid).then(response => {
            this.setState({
                product: response.data
            });
            console.log("This is Data From server :", response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    onNumberChange(e){
        const productDTO = {
            "id": this.state.product.id,
            "uuid": this.state.product.uuid,
            "name": this.state.product.name,
            "unitPurchased": e.target.valueAsNumber,
            "total": 0
        }

        this.setState({
            valByProduct: e.target.valueAsNumber
        })
        console.log(productDTO)
        this.productService.getTotalPrice(productDTO).then(response=>{
            this.setState({
                productDTO:response.data
            });
        }).catch(error => {
            console.log(error)
        })
       
    }
    
    render() {
        const uuid = this.props.match.params.productUuid
        let price = this.state.product.cartonPrice * 1.3
        return (
            <>
                <Row>
                    <Col md={9}>
                        <h2>Product Name : {this.state.product.name}</h2>
                    </Col>
                </Row>
                <br />
                <hr />
                <Row>
                    <Col md={8}>
                        <h3>Number Of Product in a carton: {this.state.product.unitPerCarton} </h3>
                    </Col>
                    <Col md={4}>
                        <h3>Number Of Product Need to buy</h3>
                        {/* <div className="def-number-input number-input">
                            <button onClick={this.decrease} className="minus"></button>
                            <input className="quantity" name="quantity" value={this.state.value} onChange={() => console.log('change')}
                                type="number" />
                            <button onClick={this.increase} className="plus"></button>
                        </div> */}
                        {/* <NumericInput min={0} max={this.state.product.stock} value={this.state.product.unitPerCarton} onChange={this.onChange} /> */}
                        <input type="number" value={this.state.valByProduct == null ? this.state.product.unitPerCarton : this.state.valByProduct } onChange={this.onNumberChange}></input>
                        
                    </Col>
                </Row>
                <Row>
                    <Col md={9}>
                        <h3>Total Price for {this.state.valByProduct == null ? 1 : (this.state.valByProduct - (this.state.valByProduct % this.state.product.unitPerCarton)) / this.state.product.unitPerCarton} Carton and {this.state.valByProduct == null ? 0 : this.state.valByProduct % this.state.product.unitPerCarton} Products :</h3><br /><h3> {this.state.productDTO == null ? /*this.state.product.cartonPrice*/price : this.state.productDTO.total} </h3>
                    </Col>
                </Row>
            </>
        )
    }
}
