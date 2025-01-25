package net.jmp.spring.boot.extras.config;

/*
 * (#)JsonConfig.java   0.1.0   01/25/2025
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

import com.google.gson.Gson;

import java.io.*;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.env.Environment;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/// The JSON configuration bean.
///
/// @version    0.1.0
/// @since      0.1.0
@Configuration
public class JsonConfig {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The environment.
    private final Environment environment;

    /// The default constructor.
    ///
    /// @param  environment org.springframework.core.env.Environment
    public JsonConfig(final Environment environment) {
        super();

        this.environment = environment;
    }

    /// Return the configuration.
    ///
    /// @return net.jmp.spring.boot.extras.config.Config
    @Bean
    public Config config() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final String appConfigFileName = this.environment.getProperty("app.config.json.filename");

        if (appConfigFileName == null) {
            throw new RuntimeException("The app.config.json.filename property is not set");
        }

        this.logger.info("Reading JSON config file: {}", appConfigFileName);

        final Resource resource = new ClassPathResource(appConfigFileName);

        Config config;

        final Gson gson = new Gson();

        try (final InputStream inputStream = resource.getInputStream()) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            config = gson.fromJson(reader, Config.class);
        } catch (final IOException ioe) {
            throw new RuntimeException("Failed to read JSON config file: " + appConfigFileName, ioe);
        }

        this.logger.info("Loaded JSON config file: {}", appConfigFileName);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(config));
        }


        return config;
    }
}
