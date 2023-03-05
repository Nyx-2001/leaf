package com.leaf.utils;

/**
 * @Author Hanami
 * @Date 2023-03-01
 */
public class ElasticsearchConstants {
    public static final String  SHOP_MAPPING_TEMPLATE = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\":{\n" +
            "      \"id\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"name\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"typeId\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"images\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"area\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"address\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"location\":{\n" +
            "        \"type\": \"geo_point\"\n" +
            "      },\n" +
            "      \"avgPrice\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"sold\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"comments\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"score\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"openHours\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"createTime\":{\n" +
            "        \"type\": \"date\"\n" +
            "      },\n" +
            "      \"updateTime\":{\n" +
            "        \"type\": \"date\"\n" +
            "      },\n" +
            "      \"x\":{\n" +
            "        \"type\": \"double\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"y\":{\n" +
            "        \"type\": \"double\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"all\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}\n";
}
