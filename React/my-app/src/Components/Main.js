import React from 'react';
import Header from './Hedder';
import Body from './Body';
import Footer from './Footer';
import './style.css';
class Main extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      textInput: '',
      selectedOption: ''
    };
    this.child2Ref = React.createRef();
  }
  callChild2Function = (param1,param2,param3) => {
    console.log('----->', param1,param2,param3);
    this.child2Ref.current.someFunction(param1,param2,param3); // Directly calling a function of Child2
  };



  render() {
    return (
      <div>
        <Header onCallChild2={this.callChild2Function}></Header>
        <br />
        <Body ref={this.child2Ref}></Body> <br />
        <Footer></Footer>
      </div>
    );
  }
}

export default Main;
