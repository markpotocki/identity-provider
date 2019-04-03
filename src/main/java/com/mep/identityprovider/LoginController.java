package com.mep.identityprovider;

import com.fasterxml.jackson.databind.JsonNode;
import com.mep.identityprovider.hydra.HydraAuthorityGuard;
import com.mep.identityprovider.hydra.HydraBodyBuilder;
import com.mep.identityprovider.hydra.HydraLoginHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private HydraLoginHandler loginHandler;
    private HydraAuthorityGuard authorityGuard;

    @GetMapping("/login")
    public ModelAndView loginPage(HttpServletRequest request, @RequestParam("challenge") String challenge) {
        ModelAndView model = new ModelAndView();

        JsonNode loginRequest = loginHandler.getLoginRequest(challenge);
        request.setAttribute("login_request", loginRequest);
        request.setAttribute("challenge", challenge);

        if(loginRequest.findValue("skip").asBoolean(false) == true) {
            // redirect to accept url right away. User should have remember already set so will bypass login filter
        } else {
            // skip is not on. show login screen
            model.setViewName("login");
        }

        return model;
    }

    @GetMapping("/login/hook")
    public String forward(HttpServletRequest request) {
        JsonNode loginRequest = (JsonNode) request.getAttribute("login_request");
        String challenge = (String) request.getAttribute("challenge");

        if(authorityGuard.isLoginApproved(loginRequest)) {
            return "redirect:/" + loginHandler.acceptLoginRequest(challenge, HydraBodyBuilder.empty());
        } else {
            return "redirect:/" + loginHandler.denyLoginRequest(challenge, HydraBodyBuilder.empty());
        }
    }

}
