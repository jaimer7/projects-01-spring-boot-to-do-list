import "./userValidationStyle.css";
import { useState } from "react";
import {Link} from 'react-router-dom';
import {useHistory} from 'react-router-dom';



export default function SignIn() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const history = useHistory();

    const handleSubmit = evt => {
        evt.preventDefault();
        makeRequest();
        setEmail("");
        setPassword("");
    };

    const makeRequest = () => {
        document.body.style.cursor = 'wait';
        let data = {"email": email, "password": password};
        console.log(data);
        console.log('checking server');
    
        fetch('http://springboottodolist-env-1.eba-dmpcuc7f.us-east-2.elasticbeanstalk.com/user/login?email=' + email + "&user_password=" + password)
            .then(res => res.json())
            .then(data => {
                console.log(data);
                document.body.style.cursor = 'default';
                if (data) {
                    history.push({
                        pathname: '/toDoList',
                        state: {'user': data}
                    });
                } else {
                    console.log(data);
                    alert("Incorrect information, please try again.");
                }
            }).catch((exception) => {
                document.body.style.cursor='default';
                alert("Incorrect information, please try again.");
            }); 
        };
    

    return (
        <div className="userValidation">
            <h1>SPRING BOOT TO DO LIST</h1>
            <div className="form">
                <form onSubmit={handleSubmit}>
                    <h2>Sign In:</h2>
                    <div className="form-group">
                        <input type="email" value={email} onChange={e => setEmail(e.target.value)}  className="form-control" placeholder="Email" required="required" />
                    </div>
                    <div className="form-group">
                        <input type="password" value={password} onChange={e => setPassword(e.target.value)}  className="form-control" placeholder="Password" required="required" />
                    </div>
                    <div className="form-group">
                        <button type="submit" value="submit">Sign in</button>
                    </div>
                </form>
                <div className="text-center">Don't have an account? <Link to="/signUp">Sign Up Here!</Link></div>
            </div>
        </div>
    );
}
