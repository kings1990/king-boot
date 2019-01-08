/*
 * ahhahah
 */

/*
 * ahhahah
 */

package com.kingboot.basic.controller;


import com.kingboot.basic.model.MappingDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.condition.NameValueExpression;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping ("/thymeleaf")
@Controller
public class TestThymeleafController {
    @Resource(name = "requestMappingHandlerMapping")//排除controllerEndpointHandlerMapping
    private RequestMappingHandlerMapping handlerMapping;
    
    @RequestMapping (name = "返回thymeleaf页面", value = "/1", method = GET)
    public String test1(Model model) throws Exception {
        if(1 == 1){
            throw new Exception("业务异常");
        }
        model.addAttribute("name", "kings1");
        return "thymeleaf/test";
    }
    
    @RequestMapping (name = "映射集", value = "/mappings", method = GET)
    public String test2(Model model, HttpServletRequest request) {
        Map map = this.handlerMapping.getHandlerMethods();
        Set set = map.keySet();
        List<MappingDetail> result = new ArrayList<>();
        
        for (Object object : set) {
            RequestMappingInfo info = (RequestMappingInfo) object;
            Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
            
            Object[] methodsArray = methods.toArray();
            if (methodsArray.length > 0) {
                RequestMethod method = (RequestMethod) methodsArray[0];
                String reqURIs = info.getPatternsCondition().toString();
                reqURIs = reqURIs.substring(1, reqURIs.length() - 1);
                //params
                Set<NameValueExpression<String>> expressions = info.getParamsCondition().getExpressions();
                String params ;
                if (! expressions.isEmpty()) {
                    //a=1&b=2
                    params = expressions.toString().replace(", ", "&").replace("[", "").replace("]", "");
                    if (reqURIs.contains("&")) {
                        reqURIs = reqURIs + "&" + params;
                    } else {
                        reqURIs = reqURIs + "?" + params;
                    }
                }
                MappingDetail mappingDetail = new MappingDetail();
                mappingDetail.setMethod(method.name());
                mappingDetail.setName(info.getName());
                String contextPath = request.getContextPath();
                mappingDetail.setUrl(contextPath+reqURIs);
                result.add(mappingDetail);
            }
        }
        result.sort(Comparator.comparing(MappingDetail :: getUrl));
        model.addAttribute("mappings", result);
        return "thymeleaf/mappings";
    }
}