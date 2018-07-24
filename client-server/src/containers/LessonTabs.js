import React from 'react';
import Lesson from "../components/Lesson";
import LessonService from "../services/LessonService";


export default class LessonTabs extends React.Component {
    LessonService;

    constructor() {
        super();
        this.state = {
            lessonTitle: '',
            lessons: []
        };
        this.setLessons = this.setLessons.bind(this);
        this.renderAllLesson = this.renderAllLesson.bind(this);
        this.titleChanged = this.titleChanged.bind(this);
        this.createLesson = this.createLesson.bind(this);
        this.lessonService = LessonService.instance;
    }

    setLessons(lessons) {
        this.setState({lessons: lessons});
    }

    findLessonsForModule(moduleId) {

    }

    createLesson() {
        this.lessonService
            .createLesson(this.props.id, this.state.lessonTitle);
    }


    renderAllLesson() {
        let lessons = this.state.lessons.map(function (lesson) {
            return <Lesson
                module={lesson}
                key={lesson.id}
                del={this.deleteModule}/>
        });
    }

    titleChanged(event) {
        console.log("changing state");
        this.setState({lessonTitle: event.target.value});
    }

    render() {
        return (
            <div>
                <h2>{this.props.title}</h2>
                <div className="card" style={{width: '18rem'}}>
                    <div className="card-body">
                        <input onChange={this.titleChanged}
                               value={this.state.lessonTitle}
                            type="text" placeholder="Title here" className="card-title"></input>
                        <p className="card-text">Create a new Lesson</p>
                        <button onClick={() => this.createLesson(this.state.lessonTitle)}
                                className="btn btn-primary">Create</button>
                    </div>
                </div>
                {this.renderAllLesson()}
            </div>);
    }
}