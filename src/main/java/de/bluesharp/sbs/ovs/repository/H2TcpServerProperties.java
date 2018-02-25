/*
 * Copyright 2012-2017 the original author or authors.
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

package de.bluesharp.sbs.ovs.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static org.h2.engine.Constants.DEFAULT_TCP_PORT;

@ConfigurationProperties(prefix = "spring.h2.tcp-server")
public class H2TcpServerProperties {
    /**
     * Enable the console.
     */
    private boolean enabled = false;

    private final Settings settings = new Settings();

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Settings getSettings() {
        return this.settings;
    }

    public static class Settings {

        /**
         * Enable trace output.
         */
        private boolean trace = false;

        /**
         * Enable remote access.
         */
        private boolean tcpAllowOthers = false;

        /**
         * Define the port of the H2 TCP Server
         */
        private int port = DEFAULT_TCP_PORT;

        public boolean isTrace() {
            return this.trace;
        }

        public void setTrace(boolean trace) {
            this.trace = trace;
        }

        public boolean isTcpAllowOthers() {
            return this.tcpAllowOthers;
        }

        public void setTcpAllowOthers(boolean tcpAllowOthers) {
            this.tcpAllowOthers = tcpAllowOthers;
        }

        public int getPort() {
            return this.port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }
}
