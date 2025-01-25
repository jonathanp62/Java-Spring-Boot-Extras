package net.jmp.spring.boot.extras.demos;

/*
 * (#)DictionaryDemo.java   0.1.0   01/25/2025
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

import java.util.Map;

import net.jmp.util.extra.demo.*;

import net.jmp.util.extra.Dictionary;

import static net.jmp.util.logging.LoggerUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

/// The service class that demonstrates the dictionary.
///
/// @version    0.1.0
/// @since      0.1.0
@DemoClass
@DemoVersion(0.1)
@Service
public final class DictionaryDemo implements Demo {
    /// The logger instance.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The default constructor.
    public DictionaryDemo() {
        super();
    }

    /// The demo method.
    @Override
    public void demo() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        if (this.logger.isInfoEnabled()) {
            this.basics();
            this.advanced();
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Demonstrate the dictionary basics.
    private void basics() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final Dictionary<String, String> dictionary= new Dictionary<>();

        dictionary.put("key1", "value1");
        dictionary.put("key2", "value2");
        dictionary.put("key3", "value3");

        this.logger.info("Dictionary size: {}", dictionary.size());

        this.logValues(dictionary);

        this.logger.info("isEmpty?: {}", dictionary.isEmpty());

        dictionary.remove("key1");

        this.logger.info("contains key1?: {}", dictionary.containsKey("key1"));

        dictionary.replace("key2", "value2", "value22");

        this.logger.info("value2  : {}", dictionary.get("key2"));
        this.logger.info("value3  : {}", dictionary.get("key3"));

        this.logger.info("Dictionary size: {}", dictionary.size());

        dictionary.putIfAbsent("key1", "value1");

        this.logger.info("Dictionary size: {}", dictionary.size());

        this.logValues(dictionary);

        dictionary.clear();

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Demonstrate the dictionary advanced features.
    private void advanced() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final Dictionary<String, String> dictionary= new Dictionary<>();

        dictionary.put("key1", "value1");
        dictionary.put("key2", "value2");
        dictionary.put("key3", "value3");

        dictionary.forEach((key, value) -> {
            this.logger.info("key: {}, value: {}", key, value);
        });

        final String computedValue = dictionary.compute("key1", (key, value) -> {
            return "My " + value + " for " + key;
        });

        this.logger.info("key1: {}", computedValue);

        dictionary.merge("key4", "value4", (oldValue, newValue) -> newValue.toUpperCase());

        dictionary.merge("key1", "value1", (oldValue, newValue) -> oldValue + "-" + newValue.toUpperCase());
        dictionary.merge("key2", "value2", (oldValue, newValue) -> oldValue + "-" + newValue.toUpperCase());
        dictionary.merge("key3", "value3", (oldValue, newValue) -> oldValue + "-" + newValue.toUpperCase());

        this.logValues(dictionary);

        dictionary.put("key5", "value5");

        dictionary.merge("key5", "value5", (oldValue, newValue) -> null);   // Key 5 is removed

        this.logValues(dictionary);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Log the values of a dictionary.
    ///
    /// @param  dictionary  net.jmp.util.extra.Dictionary<String, String>
    private void logValues(final Dictionary<String, String> dictionary) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        for (final Map.Entry<String, String> entry : dictionary.entrySet()) {
            this.logger.info("key: {}, value: {}", entry.getKey(), entry.getValue());
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }
}
