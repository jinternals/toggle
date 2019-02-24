# backbone-toggle-generator-maven-plugin

backbone toggle maven plugin generates Toggles interface containing all the toggleDefinitions define in toggle defination file

### Maven dependency :

                        <plugin>
                             <groupId>com.jinternals.toggle</groupId>
                             <artifactId>jinternals-toggle-generator-maven-plugin</artifactId>
                             <version>${project.version}</version>
                             <configuration>
                                 <packageName>com.cloud.example.toggle</packageName>
                             </configuration>
                             <executions>
                                 <execution>
                                     <phase>generate-sources</phase>
                                     <goals>
                                         <goal>generate</goal>
                                     </goals>
                                 </execution>
                             </executions>
                         </plugin>
