/**
 * Copyright 1999-2014 dangdang.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.rpc.protocol.resteasy;

import com.alibaba.dubbo.remoting.http.HttpBinder;

/**
 * Only the server that implements servlet container
 * could support something like @Context injection of servlet objects.
 *
 * @author lishen
 */
public class RestServerFactory {

    private HttpBinder httpBinder;

    public void setHttpBinder(HttpBinder httpBinder) {
        this.httpBinder = httpBinder;
    }

    public RestServer createServer(String name) {
        // TODO move names to Constants
        if ("servlet".equalsIgnoreCase(name) || "jetty".equalsIgnoreCase(name) || "tomcat".equalsIgnoreCase(name)) {
            return new DubboHttpServer(httpBinder);
            // } else if ("tjws".equalsIgnoreCase(name)) {
            // return new TjwsServer();
        } else if ("netty".equalsIgnoreCase(name)) {
            return new NettyServer();
        } else if ("sunhttp".equalsIgnoreCase(name)) {
            return new SunHttpServer();
        } else {
            throw new IllegalArgumentException("Unrecognized server name: " + name);
        }
    }
}
