import React from 'react';
// import './index.css';
class Body extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            textData: '',
        };
        // Binding this to the function
        this.changeText = this.changeText.bind(this);

    }
    changeText(value) {
        this.setState((prevState) => ({
            textData: prevState.textData + '\n' + new TextDecoder("utf-8").decode(value),
        }));
    }

    someFunction = (param1,param2,param3) => {
        console.log('Function in Child2 called',param1,param2,param3);
        const xx = new URLSearchParams({
            opt: param1,
            parm2: param2,
            parm3: param3

          }).toString();
        const streamUrl = `http://192.168.1.4:8080/stream-data?${xx}`;

        fetch(streamUrl).then(response => {
            // The response body is a ReadableStream
            const reader = response.body.getReader();

            // Function to handle each chunk of data
            const processStream = ({ done, value }) => {
                // function processStream({ done, value }) {
                if (done) {
                    console.log('Stream completed');
                    return;
                }

                // Process each chunk (value is a Uint8Array)
                //console.log();
                this.changeText(value);
                // Read the next chunk
                reader.read().then(processStream);
            }

            // Start reading the stream
            reader.read().then(processStream);
        }).catch(error => {
            console.error('Fetch error:', error);
        });
    };
    render() {
        return (

            <div className="dataPackets">
                <pre>{this.state.textData}</pre>
            </div>


        );
    }
}

export default Body;
