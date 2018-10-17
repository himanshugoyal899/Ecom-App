const React = require('react');
import { BrowserRouter as Route, Link} from "react-router-dom";

function SeeDiv(props) {
    return (
                <div className="see-div"
                     style={{"marginTop": "1em",
                         "marginBottom": "1em",
                         "marginRight": "1em",
                         "marginLeft": "1em",
                         "backgroundColor": "cadetblue",
                         "color": "firebrick",
                         "width": "50em"}}
                     id={props.id}>
                    <h1>{props.body}</h1>
                </div>


    );
}

export default SeeDiv;