package test.netty.spdy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.jetty.npn.NextProtoNego.ServerProvider;

public class DefaultServerProvider implements ServerProvider {

    private static final List<String> PROTOCOLS = Collections.unmodifiableList(Arrays.asList("spdy/3", "http/1.1",
                                                                                             "http/1.0"));
    private String                    protocol;

    @Override
    public void unsupported() {
        protocol = "http/1.1";
    }

    @Override
    public List<String> protocols() {
        return PROTOCOLS;
    }

    @Override
    public void protocolSelected(String protocol) {
        this.protocol = protocol;
    }

    public String getSelectedProtocol() {
        return protocol;
    }
}
