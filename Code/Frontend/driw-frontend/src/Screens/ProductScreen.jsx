import React, { Component } from 'react'
import { Row, Col, Table } from 'react-bootstrap'
import ProductTableRow from '../Components/ProductTableRow'
import ProductService from '../Services/ProductService'


export default class PrudctScreen extends Component {

    constructor(props) {
        super(props);
        this.state = {
            initial: 'state',
            productList: ''
        };
        this.productService = new ProductService();
        this.loadProductList();
    }

    loadProductList() {
        this.productService.getProducts().then(response => {
            this.setState({
                productList: response.data
            });
            console.log("This is Data From server :",response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    tableRow(){
        if (this.state.productList !== null && this.state.productList !== '') {
            return this.state.productList.map(function (product, i) {
                return <ProductTableRow product={product} key={i} />;
            });
        }
    }
    render() {
        return (
            <div>
                <h1>Latest products</h1>
                <Row>
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Unit Per Carton</th>
                                <th>Buying Qty</th>
                                <th>Carton Price</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.tableRow()}
                            {/* {
                                this.state.productList.map((product, i) =>(
                                    <ProductTableRow product={product} key={i} />
                                ))
                            } */}
                        </tbody>
                        </Table>
                </Row>
            </div>
        )
    }
}
