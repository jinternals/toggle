# toggle-starter

### Maven dependency :

             <dependency>
                  <groupId>com.jinternals.toggle</groupId>
                  <artifactId>toggle-starter</artifactId>
                  <version>${backbone.toggle.version}</version>
             </dependency>

### How to use :

Spring bean and controller level methods:

            @ToggleDefinition(ToggleDefinition = "some.toggle", expectedToBeOn = true)

    
Code level toggle conditions:
    
            @Autowire
            private ToggleDecider toggleService;
    
               
            if(toggleService.isToggleOn("some.toggle"))
            {
                       ...
            }
            if(toggleService.isToggleOff("some.toggle"))
            {
                       ...
            }

### How to enable and disable toggle : 

            toggle.demo.enabled=true
        
            toggle.demo.enabled=false

