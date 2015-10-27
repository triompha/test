package test.netty.spdy;


public class SpdyRequestHandler extends HttpRequestHandler {
    @Override
    protected String getContent() {
      return "This content is come from Spdy\n\r" ;
    }
}
