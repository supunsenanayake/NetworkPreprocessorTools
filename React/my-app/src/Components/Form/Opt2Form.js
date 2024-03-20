import React, { Component } from 'react';

class Opt2Form extends Component {
    constructor(props) {
        super(props);
        // Initialize state
        this.state = {
        };
        this.srcIp = React.createRef();
        this.desIp = React.createRef();
    }



    // Lifecycle method example
    componentDidMount() {
        console.log('Component did mount!');
    }


    handleSubmit = (event) => {
        // Prevent the default form submission behavior
        event.preventDefault();

        // Access the input values directly from the refs
        const srcIp = this.srcIp.current.value;
        const desIp = this.desIp.current.value;

        // Do something with the input values, like sending them to an API or logging them
        console.log('Name:', srcIp);
    };

    // Render method
    render() {
        return (
            <form onSubmit={this.handleSubmit}>

                <table>
                    <tbody>
                        <tr>
                            <th className="label" > <label >Source Ip</label></th>
                            <th ><input type="text" ref={this.srcIp} /></th>
                        </tr>
                        <tr>
                            <th className="label"> <label  >Destination Ip</label></th>
                            <th><input type="text" ref={this.desIp} /></th>
                        </tr>
                    </tbody>
                </table>
                {/* <div>
                    <label>
                        Source Ip
                        <input type="text" ref={this.srcIp} />
                    </label>
                </div>
                <div>
                    <label>
                        Destination Ip
                        <input type="text" ref={this.desIp} />
                    </label>
                </div> */}
                <button className="button button4" onClick={() =>this.props.onCallChild2('opt2',this.srcIp.current.value,this.desIp.current.value)} type="submit">Filter</button>
            </form>
        );
    }
}

export default Opt2Form;
