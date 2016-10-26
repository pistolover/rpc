/*
 * Copyright 2011- Per Wendel
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package server;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Request {
    Map<String, List<String>> getParams();

    List<String> getParams(String name);

    String getParam(String name);

    String getMethod();

    String getUri();

    String getContentType();

    String getRemoteAddr();

    int getRemotePort();

    String getBodyAsString();

    byte[] getBodyAsBytes();

    String queryParams(String queryParam);

    Set<String> queryParams();

    String getHeader(String header);

    Set<String> getHeaders();

    FileItem getFile(String name);

}
