package spring_basic.core.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import spring_basic.core.common.MyLogger;

@Service
@RequiredArgsConstructor
public class LogDemoService {

//    private final ObjectProvider<MyLogger> myLoggerProvide;
    private final MyLogger myLogger;

    public void logic(String id) {
        System.out.println("LogDemo");
//        MyLogger myLogger = myLoggerProvide.getObject();
        myLogger.log("Service id = " + id);
        System.out.println("o o o o o o o o o o");
    }
}
