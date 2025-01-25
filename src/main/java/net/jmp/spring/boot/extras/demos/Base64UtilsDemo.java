package net.jmp.spring.boot.extras.demos;

/*
 * (#)Base64UtilsDemo.java  0.1.0   01/25/2025
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

import net.jmp.util.extra.utils.Base64Utils;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

/// The service class that demonstrates the base 64 utilities.
///
/// @version    0.1.0
/// @since      0.1.0
@DemoClass
@DemoVersion(0.1)
@Service
public final class Base64UtilsDemo implements Demo {
    /// The logger instance.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The default constructor.
    public Base64UtilsDemo() {
        super();
    }

    /// The demo method.
    @Override
    public void demo() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final String string = "The quick brown fox jumps over the lazy dog";

        final String encoded = Base64Utils.encode(string, 2);
        final String decoded = Base64Utils.decode(encoded, 2);

        if (this.logger.isInfoEnabled()) {
            this.logger.info(string);
            this.logger.info(encoded);
            this.logger.info(decoded);
        }

        if (this.logger.isTraceEnabled()) {
            logger.trace(exit());
        }
    }
}
