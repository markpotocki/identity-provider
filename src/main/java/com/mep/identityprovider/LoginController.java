package com.mep.identityprovider;

import com.fasterxml.jackson.databind.JsonNode;
import com.mep.identityprovider.hydra.HydraAuthorityGuard;
import com.mep.identityprovider.hydra.HydraBodyBuilder;
import com.mep.identityprovider.hydra.HydraLoginHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private final HydraLoginHandler loginHandler;
    private final HydraAuthorityGuard authorityGuard;

    public LoginController(HydraLoginHandler loginHandler, HydraAuthorityGuard authorityGuard) {
        this.loginHandler = loginHandler;
        this.authorityGuard = authorityGuard;
    }

    @GetMapping("/authenticate")
    public ModelAndView loginPage(HttpServletRequest request, @RequestParam("login_challenge") String challenge) {
        ModelAndView model = new ModelAndView();

        JsonNode loginRequest = loginHandler.getLoginRequest(challenge);
        request.getSession().setAttribute("login_request", loginRequest);
        request.getSession().setAttribute("challenge", challenge);

        if(loginRequest.findValue("skip").asBoolean(false) == true) {
            // redirect to accept url right away. User should have remember already set so will bypass login filter
        } else {
            // skip is not on. show login screen
            model.setViewName("login");
        }

        return model;
    }

    @RequestMapping(value = "/login/hook", method = { RequestMethod.GET, RequestMethod.POST })
    public String forward(HttpServletRequest request) {
        JsonNode loginRequest = (JsonNode) request.getSession().getAttribute("login_request");
        String challenge = (String) request.getSession().getAttribute("challenge");

        if(authorityGuard.isLoginApproved(loginRequest)) {
            return "redirect:" + loginHandler.acceptLoginRequest(challenge,
                    HydraBodyBuilder.acceptLoginBody(request.getUserPrincipal().getName(), true)); // TODO add remember boolean handler
        } else {
            return "redirect:" + loginHandler.denyLoginRequest(challenge, HydraBodyBuilder.empty());
        }
    }

}
