package com.wms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ：LiCan
 * @date ：Created in 2019/1/8 13:31
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/page")
/**
 * @Title: toPage
 * 	@Description: 二级页面跳转，例如 WEB-INF/user/login.jsp
 * 	 @param: @param pageName1	 例如 user
 * 	  @param: @param pageName2	例如 login
 * 	  @param: @return

 */
public class PageController {
    @RequestMapping("/{pageName1}/{pageName2}")
    public ModelAndView toPage(@PathVariable("pageName1") String pageName1, @PathVariable("pageName2") String pageName2) {
        ModelAndView mv = new ModelAndView(pageName1 + "/" + pageName2);
        return mv;
    }




    @RequestMapping(value = "{pageName1}", method = RequestMethod.GET)
    public ModelAndView toPage(@PathVariable("pageName1") String pageName1) {
        ModelAndView mv = new ModelAndView(pageName1);
        return mv;
    }


}
