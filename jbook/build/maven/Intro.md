//todo


build plugin

```
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-jar-plugin</artifactId>
                    <configuration>
                        <!-- DO NOT include log4j.properties file in your Jar -->
                        <excludes>
                            <exclude>**/log4j.properties</exclude>
                        </excludes>
                        <archive>
                            <manifest>
                                <!-- Jar file entry point -->
                                <mainClass>utils.filesystem.DirectoryUtils</mainClass>
                            </manifest>
                        </archive>
                    </configuration>
                </plugin>
```