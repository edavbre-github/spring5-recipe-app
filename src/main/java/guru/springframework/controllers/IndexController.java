package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by David Brennan, 05/03/2019, 18:15 (copied from Pet Clinic)
 *
 * @author edavbre
 */
@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String index() {
        System.out.println("Dummy message fom Dagger zzzzzzzzz");
        // Causes Thymeleaf to lookup index.html
        return "index";
    }
}
