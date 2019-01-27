# backbone-toggle-generator-maven-plugin

backbone toggle maven plugin generates Features interface containing all the toggles define in toggle defination file

### Maven dependency :

              <plugin>
                     <groupId>com.jinternals.backbone</groupId>
                     <artifactId>backbone-toggle-generator-maven-plugin</artifactId>
                     <configuration>
                         <packageName>some package name</packageName>
                         <!-- toggleDefinitions.yml -->
                         <fileName>Toggle-defiantion file name</fileName>
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
