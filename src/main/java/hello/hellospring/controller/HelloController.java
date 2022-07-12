package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  //써줘야함
public class HelloController {

    @GetMapping("hello") //웹 어플리케이션에 /hello가 들어오면 아래 메소드 호출
    public String hello(Model model){
        //model은 mvc의 모델을 뜻한다
        model.addAttribute("data","hello!!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value= "name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http에서 바디부에 이 데이터를 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name){
        return "hello"+name; //요청한 그대로 전달 뷰 필요없음
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //json으로 반환
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
