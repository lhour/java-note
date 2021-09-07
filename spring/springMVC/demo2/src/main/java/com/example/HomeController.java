package com.example;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Hola (ed)Mundo!
 */
@Controller
public class HomeController {

    /**
     * Maneja las solicitudes que se le hacen a la raíz del sitio
     * 
     * @return un objeto {@link ModelAndView} con la respuesta al cliente
     */
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView mostrarHome(@RequestParam(value = "iname", required = false)String id) {
        return new ModelAndView("home", "mensaje", "Hola (ed)Mundo!");
    }

    @RequestMapping(path = "/some.do", method = RequestMethod.GET)
    public ModelAndView dosome(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                HttpSession httpSession) {
        ModelAndView mav = new ModelAndView();
        // request.setAttribute();
        mav.addObject("msg", "dosome");
        // request.getRequestDispath();
        mav.setViewName("/show");
        return mav;
    }
}
