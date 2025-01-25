package net.jmp.spring.boot.extras;

/*
 * (#)Main.java 0.1.0   01/21/2025
 *
 * @author   Jonathan Parker
 *
 * MIT License
 *
 * Copyright (c) 2025 Jonathan M. Parker
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.util.function.Consumer;

import net.jmp.spring.boot.extras.config.Config;

import net.jmp.util.extra.demo.Demo;
import net.jmp.util.extra.demo.DemoUtilException;
import net.jmp.util.extra.demo.DemoUtils;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Component;

/// The main application class.
///
/// @version    0.1.0
/// @since      0.1.0
@Component
public class Main implements Runnable {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The application context.
    private final ApplicationContext applicationContext;

    /// The application name.
    @Value("${spring.application.name}")
    private String applicationName;

    /// The application version.
    @Value("${spring.application.version}")
    private String applicationVersion;

    /// The constructor.
    ///
    /// @param  applicationContext  org.springframework.context.ApplicationContext
    public Main(final ApplicationContext applicationContext) {
        super();

        this.applicationContext = applicationContext;
    }

    /// The run method.
    @Override
    public void run() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        if (this.logger.isInfoEnabled()) {
            this.logger.info("Hello from {}:{}", this.applicationName, this.applicationVersion);
        }

        this.runDemos(this.applicationContext.getBean(Config.class));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Run the demonstration service classes.
    ///
    /// @param config net.jmp.spring.boot.extras.config.Config
    private void runDemos(final Config config) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(config));
        }

        final Consumer<String> demoRunner = className -> {
            try {
                final double version = DemoUtils.getDemoClassVersion(className);

                if (version > 0) {
                    if (config.getVersion() >= version) {
                        this.runDemo(className);
                    }
                } else {
                    this.runDemo(className);
                }
            } catch (final ClassNotFoundException | DemoUtilException e) {
                this.logger.error(catching(e));
            }
        };

        config.getDemosAsStream()
                .map(demo -> config.getPackageName() + "." + demo)
                .forEach(demoRunner);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Run a single demonstration service class.
    ///
    /// @param  className   java.lang.String
    /// @throws             java.lang.ClassNotFoundException    When the class is not found
    private void runDemo(final String className) throws ClassNotFoundException {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(className);
        }

        final Class<?> clazz = Class.forName(className);
        final Demo demo = (Demo) this.applicationContext.getBean(clazz);

        demo.demo();

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }
}
