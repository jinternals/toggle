# backbone-toggle-starter

backbone toggle is framework that provides Toggle toggling functionality.


![Architectural component of services](../../architecture/toggles_svg.png)

### Maven dependency :

             <dependency>
                  <groupId>com.jinternals.backbone</groupId>
                  <artifactId>backbone-toggle-starter</artifactId>
                  <version>${backbone.toggle.version}</version>
             </dependency>

### How to use :

Spring bean and controller level methods:

            @FeatureToggle(Toggle = "some.toggle", expectedToBeOn = true)

    
Code level toggle conditions:
    
            @Autowire
            private FeatureToggleDecider toggleDecider;
    
               
            if(toggleDecider.isToggleOn("some.toggle"))
            {
                       ...
            }
            if(toggleDecider.isToggleOff("some.toggle"))
            {
                       ...
            }

### How to enable and disable toggle : 

            Toggle.some.toggle.enabled=true
        
            Toggle.some.toggle.enabled=false

