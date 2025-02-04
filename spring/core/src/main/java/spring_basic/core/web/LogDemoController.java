package spring_basic.core.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring_basic.core.common.MyLogger;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final ObjectProvider<MyLogger> myLoggerProvide;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requesetURL = request.getRequestURL().toString();
        System.out.println("Get Logger");
//        MyLogger myLogger = myLoggerProvide.getObject();
        System.out.println("Set URL");
        myLogger.setRequestURL(requesetURL);

        myLogger.log("Controller test");
        logDemoService.logic("testId");
        System.out.println("= = = = = = = = = = = =");
        return "OK";
    }
}
