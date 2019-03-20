package training.busboard.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.*;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class Website {

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) {
        boolean isPostcodeValid = ValidatePostCode.isPostCodeValid(postcode);
        if (isPostcodeValid) {
            List<BusStopInfo> busDepartures = DepartureBoard.getBusDepartures(postcode);
            return new ModelAndView("info", "busInfo", new BusInfo(postcode, busDepartures));
        } else {
            return new ModelAndView("error");
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}