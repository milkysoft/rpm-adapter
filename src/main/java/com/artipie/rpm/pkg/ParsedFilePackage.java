/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 artipie.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.artipie.rpm.pkg;

import com.artipie.rpm.Digest;
import com.jcabi.log.Logger;
import java.io.IOException;
import java.nio.file.Path;
import org.redline_rpm.header.Header;

/**
 * Package with parsed RPM headers.
 * @since 0.8
 */
final class ParsedFilePackage implements Package {

    /**
     * Package metadata.
     */
    private final Header header;

    /**
     * Package file.
     */
    private final Path file;

    /**
     * The RPM file location relatively to the updated repository.
     */
    private final String location;

    /**
     * Ctor.
     * @param meta Package metadata
     * @param file File path
     * @param location File relative location
     */
    ParsedFilePackage(final Header meta, final Path file, final String location) {
        this.header = meta;
        this.file = file;
        this.location = location;
    }

    @Override
    public void save(final PackageOutput out, final Digest digest) throws IOException {
        Logger.debug(this, "accepting %s", this.file.getFileName());
        out.accept(new FilePackage.Headers(this.header, this.file, digest, this.location));
    }
}
