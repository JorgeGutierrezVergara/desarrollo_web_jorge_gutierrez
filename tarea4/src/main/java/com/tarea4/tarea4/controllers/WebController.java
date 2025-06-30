package com.tarea4.tarea4.controllers; 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/evaluaciones")
    public String showEvaluacionesPage() {
        return "evaluaciones"; 
    }
}