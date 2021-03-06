import React, { useContext} from 'react';
import {Form} from 'react-bootstrap';
import { Context } from '../context';
import "./sidePanel.css";

function ListFilter({filter}) {

    const {filterLists} = useContext(Context);

    const handleOnChange = (e) => {
        console.log(e.target.value)
        filterLists({search: e.target.value});
    };

    return(
        <Form className="filter-form">
            <Form.Group>
                <Form.Control onChange={handleOnChange} className='filter_form_input' type="text" placeholder=" Search for list..." />
            </Form.Group>
        </Form>
    );
}

export default ListFilter;