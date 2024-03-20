import React, { Component } from 'react';
import Opt1Form from './Form/Opt1Form';
import Opt2Form from './Form/Opt2Form';
import Opt3Form from './Form/Opt3Form';
class Header extends Component {
  constructor(props) {
    super(props);
    // Initialize state
    this.state = {

      selectedOption: ''
    };
    // Binding this to the event handler
    this.incrementCount = this.incrementCount.bind(this);
  }

  // Event handler for button click
  incrementCount() {
    this.setState({ count: this.state.count + 1 });
  }

  // Lifecycle method example
  componentDidMount() {
    console.log('Component did mount!');
  }
  handleDropdownChange = (event) => {
    const selectedValue = event.target.value;
    this.setState({
      selectedOption: selectedValue,
      textInput: selectedValue // Update text input based on selection
    });
  }

  // handleTextChange = (event) => {
  //   if (this.state.selectedOption === 'opt1') {
  //     this.setState({ textInputOpt1: event.target.value });
  //   }
  //   else if (this.state.selectedOption === 'opt2') {
  //     this.setState({ textInputOpt2a: event.target.value,textInputOpt2b: event.target.value });
  //   }
  //   else if (this.state.selectedOption === 'opt3') {

  //   }

  // }
  renderContent = () => {
    // const { value } = this.state;

    if (this.state.selectedOption === 'opt1') {
      return <Opt1Form onCallChild2={this.props.onCallChild2}></Opt1Form>
        // <div>
        //   <input
        //     type="text"
        //     value={this.state.textInputOpt1}
        //     onChange={this.handleTextChange}
        //     placeholder="Enter CMD"
        //   />
        //   <button class="button button4" onClick={this.props.onCallChild2}>Click</button>
        // </div>
        ;
    } else if (this.state.selectedOption === 'opt2') {
      return <Opt2Form onCallChild2={this.props.onCallChild2}></Opt2Form>
      // <div>
        {/* <input
          type="text"
          value={this.state.textInputOpt2a}
          onChange={this.handleTextChange}
          placeholder="Source Ip"
        />
        <input
          type="text"
          value={this.state.textInputOpt2b}
          onChange={this.handleTextChange}
          placeholder="Destination Ip"
        />
        <button class="button button4" onClick={this.props.onCallChild2}>Click</button> */}
      // </div>
      ;
    } else if (this.state.selectedOption === 'opt3') {
      return <Opt3Form onCallChild2={this.props.onCallChild2}></Opt3Form>;
    }
  };
  // Render method
  render() {
    return (
      <div className="header">
        <div className="container">
          <select value={this.state.selectedOption} onChange={this.handleDropdownChange}>
            <option value="">Select an option</option>
            <option value="opt1">CMD</option>
            <option value="opt2">Filter</option>
            <option value="opt3">Samples</option>
          </select>
          <div>{this.renderContent()}</div>
        </div>
      </div>
    );
  }
}

export default Header;
