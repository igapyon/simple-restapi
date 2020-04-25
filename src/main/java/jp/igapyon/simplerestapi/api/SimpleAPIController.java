/*
 * Copyright 2020 Toshiki Iga
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.igapyon.simplerestapi.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jp.igapyon.simplerestapi.json.JsonUser;

/**
 * Spring Web による API のシンプルサンプル。
 * 
 * @author Toshiki Iga
 */
@RestController
public class SimpleAPIController {
    /**
     * Spring Web + Jackson によるシンプルサンプル。
     * 
     * この API には、
     * http://localhost:8080/api/simpleget/id5535 などで到達できます。
     * 
     * @param id GET引数のid部分。
     * @return レスポンス JSONデータ。
     */
    @RequestMapping(path = "/api/simpleget/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String simpleget(@PathVariable String id) {
        // REST API として id を受信できたことを確認。
        System.err.println("trace: id:" + id);

        // サンプルとして出力したいユーザ情報を作成。
        List<JsonUser> resultUsers = new ArrayList<>();

        // 一人目
        JsonUser user = new JsonUser();
        user.setId(2);
        user.setName("hanako");
        user.setAge(22);
        resultUsers.add(user);

        // 二人目
        user = new JsonUser();
        user.setId(3);
        user.setName("takuya");
        user.setAge(25);
        resultUsers.add(user);

        // できあがったオブジェクトを Jackson で文字列化。
        ObjectMapper mapper = new ObjectMapper();
        // Jackson のデフォルトインデントつき。
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String result = mapper.writeValueAsString(resultUsers);

            // 出来上がりの JSON 文字列をお好みのインデントなどに改造。
            result = StringUtils.replace(result, "[ {", "[\n {");
            result = StringUtils.replace(result, "\n}, {", "\n },\n {");
            result = StringUtils.replace(result, "\n} ]", "\n }\n]");

            // 改造後の JSON 文字列を返却。O
            return result;
        } catch (IOException ex) {
            // エラー処理は実装していません。
            System.err.println("NO ERROR HANDLING IMPLEMENTED." + ex.toString());
            return "['ERROR']";
        }
    }
}
