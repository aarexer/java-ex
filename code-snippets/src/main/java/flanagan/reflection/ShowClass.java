package flanagan.reflection;

import java.lang.reflect.*;

public class ShowClass {
    public static void printClassInfo(String classname) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(classname);
        if (clazz.isInterface()) {
            System.out.println(Modifier.toString(clazz.getModifiers()) + " " + typeName(clazz));
        } else if (clazz.getSuperclass() != null) {
            System.out.println(Modifier.toString(clazz.getModifiers()) + " class " + typeName(clazz) + " extends " + typeName(clazz.getSuperclass()));
        } else {
            System.out.println(Modifier.toString(clazz.getModifiers()) + " class " + typeName(clazz));
        }

        Class[] interfaces = clazz.getInterfaces();
        if ((interfaces != null) && (interfaces.length > 0)) {
            if (clazz.isInterface()) {
                System.out.println(" extends ");
            } else {
                System.out.println(" implements ");
            }

            for (int i = 0; i < interfaces.length; i++) {
                if (i > 0) {
                    System.out.println(", ");
                }
                System.out.println(typeName(interfaces[i]));
            }
        }

        System.out.println(" { ");

        System.out.println("// Constructors ");
        Constructor constructors[] = clazz.getConstructors();
        for (Constructor c : constructors) {
            printMethodOfConstructor(c);
        }

        System.out.println("// Fields ");
        Field fields[] = clazz.getDeclaredFields();
        for (Field f : fields) {
            printField(f);
        }

        System.out.println("// Methods ");
        Method methods[] = clazz.getDeclaredMethods();
        for (Method m : methods) {
            printMethodOfConstructor(m);
        }
    }

    private static String typeName(Class<?> clazz) {
        StringBuilder builder = new StringBuilder();
        while (clazz.isArray()) {
            builder.append("[]");
            clazz = clazz.getComponentType();
        }
        String name = clazz.getName();
        int pos = name.lastIndexOf(".");
        if (pos != -1) {
            name = name.substring(pos + 1);
        }
        return name + builder.toString();
    }

    private static String modifiers(int m) {
        if (m == 0) {
            return "";
        }

        return Modifier.toString(m) + " ";
    }

    private static void printField(Field field) {
        System.out.println(" " + modifiers(field.getModifiers()) + typeName(field.getType()) + " " + field.getName() + ";");
    }

    private static void printMethodOfConstructor(Member member) {
        Class returnType = null;
        Class parameters[], exceptions[];

        if (member instanceof Method) {
            Method m = (Method) member;
            returnType = m.getReturnType();
            parameters = m.getParameterTypes();
            exceptions = m.getExceptionTypes();

            System.out.println(" " + modifiers(member.getModifiers()) + typeName(returnType) + " " + member.getName() + "(");
        } else {
            Constructor c = (Constructor) member;
            parameters = c.getParameterTypes();
            exceptions = c.getExceptionTypes();

            System.out.println(" " + modifiers(member.getModifiers()) + typeName(c.getDeclaringClass()) + "(");
        }

        for (int i = 0; i < parameters.length; i++) {
            if (i > 0) {
                System.out.println(", ");
                System.out.println(typeName(parameters[i]));
            }
        }
        System.out.println(")");

        if (exceptions.length > 0) {
            System.out.println(" throws ");
        }

        for (int i = 0; i < exceptions.length; i++) {
            if (i > 0) {
                System.out.println(", ");
                System.out.println(typeName(exceptions[i]));
            }
        }

        System.out.println(";");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        printClassInfo("java.lang.Integer");
    }
}
