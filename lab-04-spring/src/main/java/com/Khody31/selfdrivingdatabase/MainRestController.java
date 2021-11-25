package com.Khody31.selfdrivingdatabase;

import com.Khody31.selfdrivingdatabase.model.Comment;
import com.Khody31.selfdrivingdatabase.repositories.CommentsRepository;
import com.Khody31.selfdrivingdatabase.repositories.ManeuverRepository;
import com.Khody31.selfdrivingdatabase.repositories.RidesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/rest")
public class MainRestController {

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    RidesRepository ridesRepository;
    @Autowired
    ManeuverRepository maneuverRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(value="/comment", produces="application/json")
    public String getComments(@RequestParam Integer id) {
        try {
            return objectMapper.writeValueAsString(commentsRepository.findById(id).get());
        } catch (NoSuchElementException err) {
            return "there is no comment with such id";
        } catch (JsonProcessingException err) {
            return "somethng went wrong";
        }
    }

    @GetMapping(value="/ride", produces="application/json")
    public String getRide(@RequestParam Integer id) {
        try {
            return objectMapper.writeValueAsString(ridesRepository.findById(id).get());
        } catch (NoSuchElementException err) {
            return "there is no ride with such id";
        } catch (JsonProcessingException err) {
            return "somethng went wrong";
        }
    }
}