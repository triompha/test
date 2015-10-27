package test.netty.spdy;

import javax.net.ssl.SSLEngine;

import org.eclipse.jetty.npn.NextProtoNego;

import io.netty.channel.ChannelInboundHandler;
import io.netty.handler.codec.spdy.SpdyOrHttpChooser;

public class DefaultSpdyOrHttpChooser extends SpdyOrHttpChooser {

    protected DefaultSpdyOrHttpChooser(int maxSpdyContentLength, int maxHttpContentLength){
        super(maxSpdyContentLength, maxHttpContentLength);
    }

    @Override
    protected SelectedProtocol getProtocol(SSLEngine engine) {
        DefaultServerProvider provider = (DefaultServerProvider) NextProtoNego.get(engine);
        String protocol = provider.getSelectedProtocol();
        if (protocol == null) {
            return SelectedProtocol.UNKNOWN;
        }
        switch (protocol) {
            case "spdy/3":
                return SelectedProtocol.SPDY_3_1;
            case "http/1.0":
            case "http/1.1":
                return SelectedProtocol.HTTP_1_1;
            default:
                return SelectedProtocol.UNKNOWN;
        }
    }

    @Override
    protected ChannelInboundHandler createHttpRequestHandlerForHttp() {
        return new HttpRequestHandler();
    }

    @Override
    protected ChannelInboundHandler createHttpRequestHandlerForSpdy() {
        return new SpdyRequestHandler();
    }

}
