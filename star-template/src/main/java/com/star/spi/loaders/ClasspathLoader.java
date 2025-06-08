/*
 * Copyright 2011-2013 HTTL Team.
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
package com.star.spi.loaders;

import com.star.Resource;
import com.star.spi.Loader;
import com.star.spi.loaders.resources.ClasspathResource;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ClasspathLoader implements Loader {

    private String cleanPath(String path) {
        return path.startsWith("/") ? path.substring(1) : path;
    }

    public List<String> list(String suffix) throws IOException {
        return null;
    }

    public boolean exists(String name, Locale locale) {
        return false;
    }

    public Resource load(String name, Locale locale, String encoding) throws IOException {
        return new ClasspathResource(name, locale, encoding, name);
    }
}