package server;

import server.impl.RequestFacade;
import server.impl.ResponseFacade;

public class WebContext {
    private Request request;
    private Response response;

    private Object attachObj;

    public WebContext(Request request, Response response) {
        this.request = new RequestFacade(request);
        this.response = new ResponseFacade(response);
    }

    public Request getRequest() {
        return request;
    }

    public Response getResponse() {
        return response;
    }

    public <T> T getAttachObj(Class<T> aClass) {
        return aClass.cast(attachObj);
    }

    public void setAttachObj(Object attachObj) {
        this.attachObj = attachObj;
    }

}
