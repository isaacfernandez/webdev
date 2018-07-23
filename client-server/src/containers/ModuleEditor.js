import React from 'react';
import LessonTabs from "./LessonTabs";


export default class ModuleEditor extends React.Component {

    render() {
        if (this.props.module.title === '') {
            return null;
        } else {
            return (
                <div>
                    <h2>{this.props.module.title}</h2>
                    <LessonTabs/>
                </div>

            );
        }
    }
}