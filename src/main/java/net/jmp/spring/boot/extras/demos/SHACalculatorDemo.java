package net.jmp.spring.boot.extras.demos;

/*
 * (#)SHACalculatorDemo.java    0.1.0   01/25/2025
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

import net.jmp.util.extra.demo.*;

import net.jmp.util.extra.sha.*;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

/// The service class that demonstrates the MD5 calculator.
///
/// @version    0.1.0
/// @since      0.1.0
@DemoClass
@DemoVersion(0.1)
@Service
public final class SHACalculatorDemo implements Demo {
    /// An enumeration of the supported SHA types.
    enum SHAType {
        /// SHA-1
        SHA1,
        /// SHA-256
        SHA256,
        /// SHA-512
        SHA512
    }

    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The default constructor.
    public SHACalculatorDemo() {
        super();
    }

    /// The demo method.
    @Override
    public void demo() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        if (this.logger.isInfoEnabled()) {
            final String string = "The quick brown fox jumps over the lazy dog";

            this.sha1Demo(string);
            this.sha256Demo(string);
            this.sha512Demo(string);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Demonstrates the SHA-1 calculator.
    ///
    /// @param  string  java.lang.String
    private void sha1Demo(final String string) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(string));
        }

        final SHA1Calculator sha1Calculator = new SHA1Calculator();
        final String sha1 = sha1Calculator.calculate(string);

        this.logResults(string, sha1, SHAType.SHA1);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Demonstrates the SHA-256 calculator.
    ///
    /// @param  string  java.lang.String
    private void sha256Demo(final String string) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(string));
        }

        final SHA256Calculator sha256Calculator = new SHA256Calculator();
        final String sha256 = sha256Calculator.calculate(string);

        this.logResults(string, sha256, SHAType.SHA256);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Demonstrates the SHA-512 calculator.
    ///
    /// @param  string  java.lang.String
    private void sha512Demo(final String string) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(string));
        }

        final SHA512Calculator sha512Calculator = new SHA512Calculator();
        final String sha512 = sha512Calculator.calculate(string);

        this.logResults(string, sha512, SHAType.SHA512);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Logs the results of the SHA calculation.
    ///
    /// @param  string  java.lang.String
    /// @param  sha     java.lang.String
    /// @param  shaType net.jmp.util.extra.SHA.Shatype
    private void logResults(final String string, final String sha, final SHAType shaType) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(string, sha, shaType));
        }

        this.logger.info("String: {}", string);

        switch (shaType) {
            case SHA1:
                this.logger.info("SHA1:   {}", sha);
                break;
            case SHA256:
                this.logger.info("SHA256: {}", sha);
                break;
            case SHA512:
                this.logger.info("SHA512: {}", sha);
                break;
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }
}
