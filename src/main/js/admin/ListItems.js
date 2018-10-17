const React = require('react');

function ListItems(props) {
    let data = props.data.slice();
    let listItems = data.map((e, index) => {
        return (
                <ListItem key={e.id}
                          value={<p>Seller: {e.firstName} {e.lastName}, {e.username}</p>}
                          onClick={() => props.onClickListItem(e.id)}
                          onClickDeleteButton={() => props.onClickDeleteButton(e.id, index)}
                          onClickEnableDisableSellerButton={() => props.onClickEnableDisableSellerButton(e.id, index)}
                          isSellerEnabled={e.enabled}
                />
        )
    });
    return (
            <ol>
                {listItems}
            </ol>
    );
}

function ListItem(props) {

    let inEnableDisableButton = props.isSellerEnabled ? "Disable" : "Enable";

    return (
        <div>
            <li onClick={()=>props.onClick()}>
                {props.value}
            </li>
            <button onClick={() => props.onClickDeleteButton()}>
                Delete
            </button>
            <button onClick={() => props.onClickEnableDisableSellerButton()}>
                {inEnableDisableButton}
            </button>
        </div>
    );
}

export default ListItems;