import React, { Component } from 'react';
import './style.css';
class Footer extends Component {
    constructor(props) {
        super(props);
        // Initialize state
        this.state = {
        };
        
    }

    

    // Lifecycle method example
    componentDidMount() {
        console.log('Component did mount!');
    }
    

    

    // Render method
    render() {
        return (
            <div className="fixed-footer">
                <div className="container">Network Monitor Tool</div>
            </div>
        );
    }
}

export default Footer;
