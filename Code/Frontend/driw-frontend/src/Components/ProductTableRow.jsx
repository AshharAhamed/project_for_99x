import React, { Component } from 'react'
import {Link} from 'react-router-dom'
import { Navbar, Nav, NavDropdown, FormControl, Button, Table } from "react-bootstrap";
import ProductService from '../Services/ProductService'

export default class ProductTableRow extends Component {

    constructor(props){
        super(props);
        this.state = {
            initial: 'state',
            product: '',
            productDTO: null,
            valByProduct: null
        };
        this.productService = new ProductService();
        this.onNumberChange = this.onNumberChange.bind(this)
    }

    onNumberChange(e) {
        const productDTO = {
            "id": this.props.product.id,
            "uuid": this.props.product.uuid,
            "name": this.props.product.name,
            "unitPurchased": e.target.valueAsNumber,
            "total": 0
        }

        this.setState({
            valByProduct: e.target.valueAsNumber
        })
        console.log(productDTO)
        this.productService.getTotalPrice(productDTO).then(response => {
            this.setState({
                productDTO: response.data
            });
        }).catch(error => {
            console.log(error)
        })

    }

    render(props) {
        let price = this.props.product.cartonPrice * 1.3
        return (
            <>
                        <tr>
                            <td>{this.props.product.name}</td>
                            <td>{this.props.product.unitPerCarton}</td>
                            <td><input type="number" min={0} max={50} value={this.state.valByProduct == null ? this.props.product.unitPerCarton : this.state.valByProduct} onChange={this.onNumberChange}></input> </td>
                        <td>{this.state.productDTO == null ? /*this.state.product.cartonPrice*/price : this.state.productDTO.total } </td>
                            <td>
                                <Link to={'/purchase/' + this.props.product.uuid}>
                                    <Button className="btn btn-success" >
                                        Purchase  <i className="fa fa-credit-card" />
                                    </Button>
                                </Link>
                            </td>  
                        </tr>
                        
            </>
        )
    }
}
