import React, { useState, useEffect } from 'react';
import { useLocation } from "react-router-dom";
import { useHistory } from 'react-router-dom';

const Context = React.createContext([]);

const ContextProvider = (props) => {
    const [lists, setLists] = useState([]);
    const [list, setList] = useState({});
    const [listUsers, setListUsers] = useState([]);
    const location = useLocation();
    const [user, setUser] = useState({});
    const history = useHistory();


    useEffect(() => {
        if (location.state === undefined) {
            alert("invalid user");
            history.push('/');
        } else {
            setUser(location.state.user);
        }
        fetchLists();
    }, [location]);

    const fetchLists = () => {
        document.body.style.cursor='wait';
        let url = 'http://springboottodolist-env-1.eba-dmpcuc7f.us-east-2.elasticbeanstalk.com/user/getUserLists?email=' + location.state.user.email;
        fetch(url)
            .then(response => {
                return response.text()
            })
            .then((data) => {
                console.log("fetching lists data from server from endpoint " + url);
                let newData = JSON.parse(data);
                console.log(newData);
                setLists(newData);
                setFilterResults(newData);
                document.body.style.cursor='default';
            })
            .catch((error) => {
                console.log(error)
                document.body.style.cursor='default';
            })
    };

    const fetchList = (id) => {
        fetch('http://springboottodolist-env-1.eba-dmpcuc7f.us-east-2.elasticbeanstalk.com/list/getListById/' + id)
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setList(data);
            }).catch((exception) => {
                console.log(exception);
            })
    }


    const fetchListUsers = (list) => {
        fetch('http://springboottodolist-env-1.eba-dmpcuc7f.us-east-2.elasticbeanstalk.com/list/getListUsers/' + list.id)
            .then(res => res.json())
            .then(data => {
                console.log(data)
                setListUsers(data)
            })
            .catch((exception) => {
                console.log(exception);
            });
    }

    const [filterResults, setFilterResults] = useState([]);

    const filterLists = (filter) => {
        console.log(filter.search.toLowerCase());
        let results = [];
        results = lists.filter(value =>
            value.list_name.toLowerCase().includes(filter.search.toLowerCase())
        );
        setFilterResults(results);
    };

    return (
        <Context.Provider value={{user, lists, list, listUsers, setList, setListUsers, filterResults, filterLists, fetchLists, fetchListUsers, fetchList}}>
            {props.children}
        </Context.Provider>
    )
};

export { Context, ContextProvider };