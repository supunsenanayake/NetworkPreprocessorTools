import React, { Component } from 'react';

class Opt3Form extends Component {
    constructor(props) {
        super(props);
        // Initialize state
        this.state = {
        };
        this.enterTime = React.createRef();

    }



    // Lifecycle method example
    componentDidMount() {
        console.log('Component did mount!');
    }


    handleSubmit = (event) => {
        // Prevent the default form submission behavior
        event.preventDefault();

        // Access the input values directly from the refs
        const name = this.enterTime.current.value;

        // this.props.onCallChild2
        // Do something with the input values, like sending them to an API or logging them
        console.log('Name:', name);
    };

    // Render method
    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <div>
                    <table>
                        <tbody>
                            <tr>
                                <th className="label"> <label   >Enter Time Interval</label></th>
                                <th><input type="text" ref={this.enterTime} /></th>
                            </tr>
                        </tbody>
                    </table>

                </div>

                <button className="button button4" onClick={() => this.props.onCallChild2('opt3', this.enterTime.current.value)} type="submit">Click</button>
            </form>
        );
    }
}

export default Opt3Form;
