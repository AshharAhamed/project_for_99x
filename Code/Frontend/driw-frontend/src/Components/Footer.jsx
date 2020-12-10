import React, { Component } from 'react'
import { Container, Row, Col } from 'react-bootstrap'


export default class Footer extends Component {
    render() {
        return (
            <footer>
                <Container>
                <Row>
                    <Col className="text-center py-3">
                            <h7>Copyright &copy; <a href="https://www.linkedin.com/in/ashhar-ahmed-se/">Ashhar</a></h7>
                    </Col>
                </Row>
                </Container>
            </footer>
        )
    }
}
