# simple-restapi

シンプルな Spring Web + REST API + Jackson のサンプルです。

最初に想定 JSON データを作成します。

simplesample.json

```json
[
    {
        "id": 2,
        "name": "hanako",
        "age": 22
    },
    {
        "id": 3,
        "name": "takuya",
        "age": 25
    }
]
```

次に この JSONデータをもちいて Java Bean を生成します。

```sh
http://www.jsonschema2pojo.org/
```

これで出来あがった Java Bean をプロジェクトに含めて開発します。

あとは、出力したいデータを作って、ObjectMapper に文字列化させれば完成。

```java
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String result = mapper.writeValueAsString(resultUsers);
```

このサンプルの起動方法は以下の通り。

```sh
mvn spring-boot:run
```

サンプル画面への到達方法は以下。

```
http://localhost:8080/api/simpleget/id5535
```

このサンプルの実行結果

```json
[
 {
  "id" : 2,
  "name" : "hanako",
  "age" : 22
 },
 {
  "id" : 3,
  "name" : "takuya",
  "age" : 25
 }
]
```

