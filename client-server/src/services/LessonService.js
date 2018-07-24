
const MODULE_API_URL =
    'http://localhost:8080/api/course/CID/module/MID';
const BASE_URL =
    'http://localhost:8080/api/module/MID'

let _singleton = Symbol();
export default class LessonService {
    constructor(singletonToken) {
        if (_singleton !== singletonToken)
            throw new Error('Singleton!!!');
    }

    findAllLessonsForModule(moduleId) {
        return fetch(
            MODULE_API_URL
                .replace('MID', moduleId))
            .then(function (response) {
                return response.json();
            })
    }

    createLesson(courseId, module) {
        return fetch(MODULE_API_URL.replace('CID', courseId),
            {
                body: JSON.stringify(module),
                headers: { 'Content-Type': 'application/json' },
                method: 'POST'
            }).then(function (resp)
        { return resp.json(); })
    }

    deleteModule(moduleId) {
        return fetch(BASE_URL.replace('MID', moduleId),
            {
                method: 'DELETE'
            }).then(function(resp) {
            return "empty";
        });
    }

    static get instance() {
        if(!this[_singleton])
            this[_singleton] = new LessonService(_singleton);
        return this[_singleton]
    }
}
