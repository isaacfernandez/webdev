package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Lesson;
import webdev.models.Widget;
import webdev.repositories.LessonRepository;
import webdev.repositories.WidgetRepository;

import java.util.List;
import java.util.Optional;


@RestController
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    LessonRepository lessonRepository;

    @CrossOrigin(origins = "*")
    @GetMapping("/api/widget")
    public List<Widget> findAllWidgets() {
        return (List<Widget>) widgetRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/api/widget/{widgetId}")
    public Optional<Widget> findWidgetById(@PathVariable("widgetId") String widgetId) {
        int id = Integer.parseInt(widgetId);
        return widgetRepository.findById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/api/lesson/{lessonId}/widget")
    public List<Widget> findWidgetsByLesson(@PathVariable("lessonId") int lessonId) {
        Optional<Lesson> maybe = lessonRepository.findById(lessonId);
        if(maybe.isPresent()) {
            Lesson lesson = maybe.get();
            return lesson.getWidgets();
        }
        return null;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/api/widget/{widgetId}")
    public void updateWidget(@PathVariable("widgetId") int widgetId, @RequestBody Widget widget) {
        Optional<Widget> optional = widgetRepository.findById(widgetId);

        if (optional.isPresent()) {
            Widget thisWidget = optional.get();
            thisWidget.setTitle(widget.getTitle());
            thisWidget.setData(widget.getData());
            thisWidget.setSize(widget.getSize());
            thisWidget.setId(widget.getId());
            thisWidget.setLink(widget.getLink());
            thisWidget.setSource(widget.getSource());
            thisWidget.setListType(widget.getListType());
            thisWidget.setListItems(widget.getListItems());
            thisWidget.setLesson(widget.getLesson());
            widgetRepository.save(thisWidget);
        }
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/api/lesson/{lessonId}/widget")
    public void createWidget(@PathVariable("lessonId") int lessonId,
                             @RequestBody Widget newWidget) {
        Optional<Lesson> thisLesson = lessonRepository.findById(lessonId);

        if (thisLesson.isPresent()) {
            Lesson lesson = thisLesson.get();
            newWidget.setLesson(lesson);
            widgetRepository.save(newWidget);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/api/lesson/{lessonId}")
    public void saveWidgets(@PathVariable("lessonId") int lessonId,
                            @RequestBody List<Widget> widgets) {
        Optional<Lesson> thisLesson = lessonRepository.findById(lessonId);

        if (thisLesson.isPresent()) {
            Lesson lesson = thisLesson.get();
            widgetRepository.deleteAll(lesson.getWidgets());
            for (Widget widget : widgets) {
                widget.setLesson(lesson);
            }
            widgetRepository.saveAll(widgets);
        }
    }


}