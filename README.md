<h1>Microservices</h1>

Microservices is an architectural style that decomposes an application into a smaller, independent, and loosely coupled
modules. Individual modules are responsible for highly defined and discrete tasks and communicate with other modules through
simple, universally accessible APIs. This architecture enables the continuous delivery/deployment of large, complex
applications.

https://user-images.githubusercontent.com/19601740/32704949-82a27018-c7d3-11e7-99bc-ac116ac5345a.jpeg

<b>Pros</b>

1. Microservice are relatively small, easy to understand for developers.
2. Independent and parallel development makes project delivery fast.
3. Failue of one service doesn’t cause to bring down the entire application.
4. Each service can be developed and deployed independently.
5. Flexibility to choose right technology for each service based on business requirement.
6. Easier to scale up/down based on the load.
7. Makes use of full CPU power.

<b>Cons</b>

1. Decomposing big application into meaningful services is a complex task.
2. Increased effort for operations, deployment and monitoring
3. Testing a microservices-based application can be cumbersome
4. Multiple databases and transaction management can be difficult to manage.


<H1>Microservices using Spring Boot and Spring Cloud APIs</H1>

<b>Creating Eureka Server</b>

In Microservices architecture, there is a need to call to one/more services from other services. This requires a mechanism to
store URLs of services are being called. There are different ways to keep these URLs make available to calling service but URLs will be different
for different environments and zones. Keeping track of all these URLs is more difficult and error prone.

Netflix came up with a solution to resolve this issue called Eureka Discovery. There would be Eureka server where services can be
registered and clients can access those service instances using application name instead of full URLs. This gives more flexibility
to register services themselves deployed on multizone.

<b>Creating Eureka server</b>

Step1: Create a spring boot project named "SpringCloudEurekaServer" with spring-cloud-starter-eureka-server dependency

Step2: Add @EnableEurekaServer annotation to make this service as Eureka Discovery Server in application java file

Step3: Add below properties in application.yml file under src/main/resources. Server.port is for starting the eureka server on
this port. eureka.client.registerWithEureka: false is for preventing register this service itself to eureka.

server:
  port: ${PORT:8761}

eureka:
  client:
    registerWithEureka: false
    fetchRegistry: false
    server:
      waitTimeInMsWhenSyncEmpty: 0

<b>Registering services with Eureka Server</b>

Step1: Create a spring project named "SpringCloudEurekaClientFirstName" with spring-cloud-starter-eureka-server, spring-boot-starter-web, spring-boot-actuator dependencies

Step2: Add @EnableDiscoveryClient annotation to make this register with Eureka server

Step3: Add below properties in application.yml file under src/main/resources. eureka.client.serviceUrl.defaultZone property is for specifying eureka server url. server.port: ${PORT:${SERVER_PORT:0}} is starting this service with dynamic avaialable port on the target machine.

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
server:
  port: ${PORT:${SERVER_PORT:0}}

Step4: Add below properties in bootstrap.yml file under src/main/resources. This is to specify the name of the application in eureka server. client can use this name to invoke this service.

 spring:
  application:
    name: springFirstName

Step5: Add controller to perform some action

@RestController
public class FirstNameController {

	@GetMapping("/getFirstName")
	public @ResponseBody String getFirstName(){
		return "Satyanarayana";
	}
}

Repeat steps for creating another service "SpringCloudEurekaClientLastName".

<b>Create Rest Service to invoke Eureka registered services</b>

Step1: Create a spring project named "SpringCloudEurekaClientFullName" with spring-cloud-starter-eureka-server, spring-boot-starter-web, spring-boot-actuator dependencies

Step2: Add @EnableDiscoveryClient annotation to make this register with Eureka server

Step3: Add below properties in application.yml file under src/main/resources. eureka.client.serviceUrl.defaultZone property is for specifying eureka server url. server.port: ${PORT:${SERVER_PORT:0}} is starting this service with dynamic avaialable port on the target machine.
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
server:
  port: 8080

Step4: Add below properties in bootstrap.yml file under src/main/resources. This is to specify the name of the application in eureka server. client can use this name to invoke this service.

 spring:
  application:
    name: springFullName

Step5: Add controller to perform some action

@RestController
public class FullNameController {

	@Autowired
	DiscoveryClient client;

	@GetMapping("/getFullName")
	public @ResponseBody String getFullName(){
		return getNamePart("SPRINGFIRSTNAME") +" "+getNamePart("SPRINGLASTNAME");
	}

	public String getNamePart(String service) {
		System.out.println("Service Name:"+service);
	    List<ServiceInstance> list = client.getInstances(service);
	    System.out.println("service list:"+list.size());
	    if (list != null && list.size() > 0 ) {
	      URI uri = list.get(0).getUri();

		  if (uri !=null ) {
			if("SPRINGFIRSTNAME".equals(service))
				return (new RestTemplate()).getForObject(uri.resolve("/getFirstName"),String.class);
			else if("SPRINGLASTNAME".equals(service))
				return (new RestTemplate()).getForObject(uri.resolve("/getLastName"),String.class);
		  }
	    }
	    return null;
	  }
}

<b>Deployment process:</b>

1. Start the Eureka server by right mouse click->Run->Spring Boot App. verify url "http://localhost:8761" once service is started. It should show the eureka services registry page

2. Start SpringCloudEurekaClientFirstName service and check Eureka registry page again. It should show the App name "FIRSTNAME"

3. Start SpringCloudEurekaClientLastName service and check Eureka registry page again. It should show the App name "LASTNAME"

4. Start SpringCloudEurekaClientFullName service and check Eureka registry page again. It should show the App name "FULLNAME"

5. open url http://localhost:8080/getFullName, it should return concatenation of response from FIRSTNAME and LASTNAME services


<b>Adding Ribbon client side loadbalancing for FullName Service </b>

Step1: Add the org.springframework.cloud / spring-cloud-starter-ribbon dependency in POM.xml

Step2: Add RestTemplate bean in SpringCloudEurekaRibbonFullNameApplication.java

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
	    return new RestTemplate();
	}
Step3: Replace the @Autowired DiscoveryClient with an @Autowired RestTemplate in FullNameController.java. code should look like below

@RestController
public class FullNameController {

	@Autowired
	RestTemplate client;

	@GetMapping("/getFullName")
	public @ResponseBody String getFullName(){
		return getNamePart("SPRINGFIRSTNAME") +" "+getNamePart("SPRINGLASTNAME");
	}

	public String getNamePart(String service) {
		System.out.println("Service Name:"+service);		
		if("SPRINGFIRSTNAME".equals(service))
			return client.getForObject("http://" + service+"/getFirstName", String.class);
		else if("SPRINGLASTNAME".equals(service))
			return client.getForObject("http://" + service+"/getLastName", String.class);
	    return null;
	  }
}

<b>Feign Declarative Client implementation</b>

Feign Rest client is a declarative solution for calling one/more services from another service. It works with Ribbon client side load balancer. Below are the steps to be followed for implementing Feign client.

Step1: Create a Spring boot project using STS with name "SpringCloudFeinClientFullName" with dependencies spring-boot-starter-actuator, spring-cloud-starter-eureka, spring-cloud-starter-feign, spring-cloud-starter-ribbon, spring-boot-starter-web along with spring cloud dependencies

Step2: Add below annotations in SpringCloudFeinClientFullNameApplication.java along with @SpringBootApplication
	@EnableDiscoveryClient
	@EnableFeignClients

Step3: Create an interface under dao package and add methods to handle operations in the actual called service. Here /getFirstName is the operation in service named "springFirstName"(Eureka registered name). Implemenatation would be taken care by Feign client
	@FeignClient("springFirstName")
	public interface FirstNameClient {
		@GetMapping("/getFirstName")
		public String getFirstName();
	}
Step4: Repeat above step for invoking last name method as well.

Step5: Create Service interface and implementation class for to invoke the method created in above step.
	//Service interface
	public interface FullNameService {
	public String buildFullName();
	}

	//Service implementation
	@Service
	public class FullNameServiceImpl implements FullNameService{
		FirstNameClient fn;
		LastNameClient ln;

		@Autowired
		public void setFn(FirstNameClient fn) {
			this.fn = fn;
		}

		@Autowired
		public void setLn(LastNameClient ln) {
			this.ln = ln;
		}

		@Override
		public String buildFullName() {
			String fullName = fn.getFirstName()+", "+ln.getLastName();
			return fullName;
		}

	}

Step6: Create a rest controller to test the above two methods.
	@RestController
	public class FullNameRestController {
		@Autowired
		FullNameService fns;

		@GetMapping("/getFullName")
		public String getFullName(){
			System.out.println("******Feign Client Service******");
			return fns.buildFullName();
		}

	}

Step7: add bootstrap.yml and application.yml with below properties
	//Application.yml
	eureka:
  	   client:
    	      serviceUrl:
      		defaultZone: http://localhost:8761/eureka/
	server:
  	   port: 8020

	//bootstrap.yml
	---
	spring:
  	   application:
    	      name: FullName

<b>Circuite Breaker pattern using Netflix Hystrix</b>

In microservices architecture, sometimes one service is dependant on multiple other services. So failure of one service should not cascade failure to following service calls. There should be a mechanism to handle service failures such crashing, network failures etc. Netflix implemented a very good solution called Hystrix circuit breaker to handle service failures. Following are the steps to implement circuit breaker pattern using Netflix Hystrix circuit breaker.

Step1: Create a Spring boot project using STS with name "SpringCloudHystrixFullName" with dependencies spring-boot-starter-actuator, spring-cloud-starter-eureka, spring-cloud-starter-ribbon, spring-boot-starter-web, spring-cloud-starter-hystrix along with spring cloud dependencies

Step2: Add below annotations in SpringCloudHystrixFullNameApplication.java along with @SpringBootApplication
	@EnableDiscoveryClient
	@EnableCircuitBreaker

Step3: Create a service class to invoke rest services using RestTemplate
@Service
public class RestClientService {
	@Autowired
	RestTemplate client;

	@HystrixCommand(fallbackMethod = "alternativeFName")
	public String getFirstName(){
			return client.getForObject("http://" + "SPRINGFIRSTNAME" +"/getFirstName", String.class);
	}

	public String alternativeFName(){
		System.out.println("*****fallbackmethod1****");
		return "Satya";
	}

	@HystrixCommand(fallbackMethod = "alternativeLName")
	public String getLastName(){
			return client.getForObject("http://" + "SPRINGLASTNAME" +"/getLastName", String.class);
	}

	public String alternativeLName(){
		System.out.println("*****fallbackmethod2****");
		return "Vannem";
	}
}

Step4: Add rest controller to call the service methods
@RestController
public class FullNameController {

	@Autowired
	RestClientService client;

	@GetMapping("/getFullName")
	public @ResponseBody String getFullName(){
		return client.getFirstName() +" "+client.getLastName();
	}
}

Step5: Add below properties in application.yml

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
server:
  port: 8080

Step6: Add service name in bootstrap.yml
---
spring:
  application:
    name: springRibbonFullName

<b>Testing</b>
1. Deploy Eureka server
2. Deploy FirstNameService
3. Deploy LastNameService
4. Deploy FullNameHystrixService
5. Access url http://localhost:8080/getFullName
6. Bring down one of the service firstName/lastName
7. Hit the above url again, it should go to fallback method

<b>Zuul Proxy Gateway implementation</b>

Microservices would be deployed on multiple machines/containers so each service has its own domain url to call the service. This is an issue when we call these multi-domain services from UI application, we get CORS(Cross Origin Resource Sharing) issue. To fix this, we need reverse proxy the service urls. Zuul Proxy is the one of the solutions implemented by Netflix OSS.
Below are the steps to create zuul proxy.

Step1: Create a Spring boot project using STS with name "SpringCloudZuulProxy" with dependencies  along with spring cloud dependencies spring-cloud-starter-zuul

Step2: Add @EnableZuulProxy annotation along with @SpringBootApplication in starter application java

@EnableZuulProxy
@SpringBootApplication
public class SpringCloudZuulProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudZuulProxyApplication.class, args);
	}
}

Step3: Add application.yml under src/main/resources with below entries.

server:
  port: 8005

zuul:
  routes:
    person:
      path: /person/**
      url: http://localhost:8002/person

 Step4: Run the person service and zuul service, try to call person service with gateway url.
