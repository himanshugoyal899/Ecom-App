import React from 'react';

function ProductListItems(props) {
    let data = props.data.slice();
    let listItems = data.map((e, index) => {
        return (
            <ProductListItem key={e.id}
                             value={<p>Product name: {e.name}</p>}
                             onClickDeleteButton={() => props.onClickDeleteButton(e.id)}
            />
        )
    });
    return (
        <ol>
            {listItems}
        </ol>
    );
}

function ProductListItem(props) {

    return (
        <div>
            <li>
                {props.value}
            </li>
            <button onClick={() => props.onClickDeleteButton()}>
                Delete
            </button>
        </div>
    );
}

export default ProductListItems;