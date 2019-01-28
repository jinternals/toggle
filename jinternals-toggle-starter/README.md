# toggle-starter

### Maven dependency :

             <dependency>
                  <groupId>com.jinternals.toggle</groupId>
                  <artifactId>toggle-starter</artifactId>
                  <version>${backbone.toggle.version}</version>
             </dependency>

### How to use :

Spring bean and controller level methods:

            @Toggle(Toggle = "some.toggle", expectedToBeOn = true)

    
Code level toggle conditions:
    
            @Autowire
            private ToggleDecider toggleDecider;
    
               
            if(toggleDecider.isToggleOn("some.toggle"))
            {
                       ...
            }
            if(toggleDecider.isToggleOff("some.toggle"))
            {
                       ...
            }

### How to enable and disable toggle : 

            toggle.demo.enabled=true
        
            toggle.demo.enabled=false

