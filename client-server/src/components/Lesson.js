import React from 'react';
import '../../node_modules/bootstrap/dist/css/bootstrap.css';
import '../../node_modules/font-awesome/css/font-awesome.min.css';

export default class Lesson extends React.Component {
    render() {
        return (
            <div className="card" style="width: 18rem;">
                    <div className="card-body">
                        <h5 className="card-title">Card title</h5>
                        <p className="card-text">Some quick example text to build on the card title and make up the bulk
                            of the card's content.</p>
                        <a href="#" className="btn btn-primary">Delete</a>
                    </div>
            </div>
        );
    }
}