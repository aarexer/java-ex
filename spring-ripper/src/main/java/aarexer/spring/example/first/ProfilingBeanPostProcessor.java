package aarexer.spring.example.first;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProfilingBeanPostProcessor implements BeanPostProcessor {
    private ProflingController controller = new ProflingController();
    private Map<String, Class> store = new HashMap<>();

    public ProfilingBeanPostProcessor() throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(controller, new ObjectName("profiling", "name", "controller"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> cls = bean.getClass();
        if (cls.isAnnotationPresent(Profiling.class)) {
            store.put(beanName, cls);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanCls = store.get(beanName);
        if (Objects.nonNull(beanCls)) {
            return Proxy.newProxyInstance(beanCls.getClassLoader(), beanCls.getInterfaces(), (proxy, method, args) -> {
                if (controller.isEnabled()) {
                    System.out.println("Profiling...");
                    long before = System.nanoTime();
                    Object retVal = method.invoke(bean, args);
                    long after = System.nanoTime();
                    System.out.println(after - before);
                    System.out.println("End.");

                    return retVal;
                } else {
                    return method.invoke(bean, args);
                }
            });
        }

        return null;
    }
}
