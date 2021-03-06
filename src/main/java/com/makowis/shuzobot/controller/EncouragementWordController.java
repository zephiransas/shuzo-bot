package com.makowis.shuzobot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.makowis.shuzobot.dao.EncouragementWordDao;
import com.makowis.shuzobot.entity.EncouragementWord;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/encouragementWords")
public class EncouragementWordController {
    @Autowired
    EncouragementWordDao encouragementWordDao;

    @GetMapping
    public String index(Model model) {
        List<EncouragementWord> encouragementWords = encouragementWordDao.selectAll();
        model.addAttribute("encouragementWords", encouragementWords);
        return "encouragementWords/list";
    }
}
