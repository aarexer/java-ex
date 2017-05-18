package utils.marshaller;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Task: serialize deserialize classes like Node<T> when T - can be List<Integer>, String and etc
 * Node<Collection<Node<Collection<Map<String, String>>>
 */
public class Marshaller {
    private final Gson gson;

    Marshaller() {
        this.gson = new Gson();
    }

    public String serialize(Node<?> node) {
        return gson.toJson(node);
    }

    public Node<?> deserialize(String node) {
        try {
            Class<?> clazz = Class.forName(new JsonParser().parse(node).getAsJsonObject().get("type").getAsString());
            return gson.fromJson(node, getType(Node.class, clazz));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Get type for gson
     * @param rawClass
     * @param parameter
     * @return
     */
    private Type getType(Class<?> rawClass, Class<?> parameter) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{parameter};
            }

            @Override
            public Type getRawType() {
                return rawClass;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }
}
