import React from 'react';
import Lesson from "../components/Lesson";


export default class LessonTabs extends React.Component {
    constructor() {
        super();
        this.state = {
            lessons: []
        };
        this.setLessons = this.setLessons.bind(this);
        this.renderAllLessons = this.renderAllLessons.bind(this);

    }

    setLessons(lessons) {
        this.setState({lessons: lessons});
    }

    findLessonsForModule(moduleId)

    renderAllLesson() {
        let lessons = this.state.lessons.map(function (lessons) {
            return <Lesson
                module={module}
                key={module.id}
                expand={self.expand}
                del={self.deleteModule}/>
        });
    }

    render() {
        return (
            <div>
            <h2>{this.props.title}</h2>
            <div>
                {this.renderAllLessons()}
            </div>
            </div>);
    }
}