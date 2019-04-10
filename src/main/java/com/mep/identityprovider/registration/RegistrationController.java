package com.mep.identityprovider.registration;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ModelAndView registrationPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("reg-form", new RegistrationDTO());
        model.setViewName("registration");
        return model;
    }

    @PostMapping
    public ModelAndView registerUser(@ModelAttribute("reg-form") RegistrationDTO registrationDTO) {
        System.out.println(registrationDTO.toString());
        registrationService.registerUser(registrationDTO);
        ModelAndView model = new ModelAndView();
        model.setViewName("registration-sucess");
        model.addObject("username", registrationDTO.getUsername());
        model.addObject("intro", "Welcome to the site");
        model.addObject("help", "Help text goes here");
        return model;
    }
}
