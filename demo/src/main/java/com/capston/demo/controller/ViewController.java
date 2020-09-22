package com.capston.demo.controller;

import com.capston.demo.request.FileList;
import com.capston.demo.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ViewController {

    private final FileService fileService;

    @GetMapping("home")
    public ModelAndView indexPage() {

        ModelAndView modelAndView = new ModelAndView("home");

        List<FileList> fileList = fileService.getAllFileList();
        modelAndView.addObject("fileList", fileList);

        int price = fileService.getPrice();
        modelAndView.addObject("price", price);

        return modelAndView;
    }

    @GetMapping("day")
    public ModelAndView getPreviousDayList() {

        ModelAndView modelAndView = new ModelAndView("home");

        List<FileList> fileList = fileService.getAllFilePreviousDay();
        modelAndView.addObject("fileList", fileList);

        return modelAndView;
    }

    @GetMapping("week")
    public ModelAndView getWeekAgoList() {

        ModelAndView modelAndView = new ModelAndView("home");

        List<FileList> fileList = fileService.getAllFileWeekAgo();
        modelAndView.addObject("fileList", fileList);

        return modelAndView;
    }

    @GetMapping("month")
    public ModelAndView getMonthAgoList() {

        ModelAndView modelAndView = new ModelAndView("home");

        List<FileList> fileList = fileService.getAllFileOneMonthAgo();
        modelAndView.addObject("fileList", fileList);

        return modelAndView;
    }
}
