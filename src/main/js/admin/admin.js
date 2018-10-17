const React = require('react');
const ReactDOM = require('react-dom');
import Navigation from './navigation';
const client = require('../commons/client');

class Admin extends React.Component {

    render() {
        return (
            <Navigation path="/admin"/>
        )
    }
}

ReactDOM.render(
    <Admin />,
    document.getElementById('react')
)