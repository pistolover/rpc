package module;

import server.HttpMethod;
import server.annotation.Controller;
import server.annotation.Router;

@Controller
public class UserController {

    @Router(value = "/user/info", method = HttpMethod.GET)
    public String getInfo() {
        return "helloWord";
    }

}
