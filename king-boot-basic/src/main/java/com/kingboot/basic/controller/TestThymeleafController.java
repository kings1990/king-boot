/*
 * ahhahah
 */

/*
 * ahhahah
 */

package com.kingboot.basic.controller;


import com.alibaba.fastjson.JSONObject;
import com.kingboot.basic.config.common.KingParam;
import com.kingboot.basic.model.MappingDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.NameValueExpression;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequestMapping ("/thymeleaf")
@Controller
public class TestThymeleafController {
    @Resource (name = "requestMappingHandlerMapping")//排除controllerEndpointHandlerMapping
    private RequestMappingHandlerMapping handlerMapping;
    
    @GetMapping (name = "返回thymeleaf页面", value = "/1")
    public String test1(Model model) throws Exception {
        if (1 == 1) {
            throw new Exception("业务异常");
        }
        model.addAttribute("name", "kings1");
        return "thymeleaf/test";
    }
    
    @GetMapping (name = "映射集", value = "/mappings")
    public String test2(Model model, HttpServletRequest request) {
        Map<RequestMappingInfo, HandlerMethod> map = this.handlerMapping.getHandlerMethods();
        List<MappingDetail> result = new ArrayList<>();
        
        Set<Map.Entry<RequestMappingInfo, HandlerMethod>> set = map.entrySet();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> object : set) {
            RequestMappingInfo info = object.getKey();
            Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
            HandlerMethod handlerMethod = object.getValue();
            Object[] methodsArray = methods.toArray();
            if (methodsArray.length > 0) {
                RequestMethod method = (RequestMethod) methodsArray[0];
                String reqURIs = info.getPatternsCondition().toString();
                reqURIs = reqURIs.replace("[", "").replace("]", "");
                String[] reqURIArray = reqURIs.split("\\|\\|");
                for (String reqURI : reqURIArray) {
                    //reqURI = reqURI.substring(1, reqURIs.length() - 1);
                    reqURI = reqURI.trim();
                    //params
                    Set<NameValueExpression<String>> expressions = info.getParamsCondition().getExpressions();
                    String params;
                    if (! expressions.isEmpty()) {
                        //a=1&b=2
                        params = expressions.toString().replace(", ", "&").replace("[", "").replace("]", "");
                        if (reqURI.contains("&")) {
                            reqURI = reqURI + "&" + params;
                        } else {
                            reqURI = reqURI + "?" + params;
                        }
                    }
                    
                    MappingDetail mappingDetail = new MappingDetail();
                    mappingDetail.setMethod(method.name());
                    mappingDetail.setName(info.getName());
                    KingParam kingParam = handlerMethod.getMethod().getAnnotation(KingParam.class);
                    mappingDetail.setData(kingParam == null ? "" : kingParam.value());
                    String contextPath = request.getContextPath();
                    
                    /*正则替换参数*/
                    String pattern = "\\{(\\w+)\\}";
                    Pattern p = Pattern.compile(pattern);
                    Matcher matcher = p.matcher(reqURI);
                    String urlWithParam = reqURI;
                    while (matcher.find()) {
                        String group = matcher.group();
                        String groupWithoutSymbol = group.replace("{", "").replace("}", "");
                        
                        if (kingParam != null) {
                            Object parse = JSONObject.parse(kingParam.value());
                            JSONObject jsonObject = JSONObject.parseObject(parse.toString());
                            if (jsonObject.getString(groupWithoutSymbol) != null) {
                                urlWithParam = reqURI.replace(group, jsonObject.getString(groupWithoutSymbol));
                            }
                        }
                        
                    }
                    /*正则替换参数*/
                    mappingDetail.setUrlWithParam(contextPath + urlWithParam);
                    mappingDetail.setUrl(contextPath + reqURI);
                    result.add(mappingDetail);
                }
            }
        }
        result.sort(Comparator.comparing(MappingDetail :: getUrl));
        model.addAttribute("mappings", result);
        return "thymeleaf/mappings";
    }
}