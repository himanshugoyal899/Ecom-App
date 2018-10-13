const React = require('react');
const ReactDOM = require('react-dom');
import AdminNavigation from './admin-navigation';
const client = require('./client');

class App extends React.Component {

    render() {
        return (
            <AdminNavigation />
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)