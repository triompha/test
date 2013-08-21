package net.csdn.modules.scan.component;

import java.io.IOException;
import java.net.URL;


public interface DirectoryIteratorFactory {
    StreamIterator create(URL url, Filter filter) throws IOException;
}
