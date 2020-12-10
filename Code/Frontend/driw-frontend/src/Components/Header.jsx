import React, { Component } from 'react'
import { Navbar, Nav, NavDropdown, FormControl, Button } from "react-bootstrap";
import { Container, Row, Col } from 'react-bootstrap'


export default class Header extends Component {
    render() {
        return (
            <header>
                <Navbar bg="primary" variant='dark' expand="lg" collapseOnSelect>
                    <Container>
                        <Navbar.Brand href="/">Drive Shop </Navbar.Brand>
                        <Navbar.Toggle aria-controls="basic-navbar-nav" />
                        <Navbar.Collapse id="basic-navbar-nav">
                            <Nav className="ml-auto">
                                <Nav.Link href="/"><i className='fas fa-shopping-cart'> Products</i></Nav.Link>
                                <Nav.Link href=""><i className='fas fa-user'> about Me</i></Nav.Link>
                            </Nav>
                        </Navbar.Collapse>
                    </Container>
                </Navbar>
            </header>
        )
    }
}
