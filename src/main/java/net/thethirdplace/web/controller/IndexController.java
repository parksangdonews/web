package net.thethirdplace.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 최초 페이지 컨트롤러
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){

        System.out.println("무언가내용을...");

        return "index";
    }

    @GetMapping("/reserve")
    public String reserve(){
        return "reserve";
    }
}
