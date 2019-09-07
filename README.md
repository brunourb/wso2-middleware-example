# WSO2

WSO2 offers a platform of middleware products for:

* Enterprise Integration
* API Management
* Identity and Access Management (IAM)
* Analytics and Stream Processing

WSO2 Product	Supported product profiles
Enterprise Integration	ESB, Business Process, Message Broker, MSF4J, Analytics Dashboard, Analytics Worker, Micro Integrator
API Management	API Manager All In One, Analytics Dashboard, Analytics Worker, Identity Server as Key Manager
IAM	Identity Server, Analytics Dashboard, Analytics Worker
Analytics and Stream Processing	Dashboard, Editor, Manager, Worker

  # WSO2 Products
  | WSO2 Product | Supported product profiles |
  | ---  | --- |
  | Enterprise Integration | ESB, Business Process, Message Broker, MSF4J, Analytics Dashboard, Analytics Worker, Micro Integrator |
  | API Management | API Manager All In One, Analytics Dashboard, Analytics Worker, Identity Server as Key Manager |
  | IAM | Identity Server, Analytics Dashboard, Analytics Worker |
  | Analytics and Stream Processing | Dashboard, Editor, Manager, Worker
  
  # WSO2 Stack
  ```console
  /usr/local/bin/docker-compose -up -d  
  ```
  
  # Dockerfile for WSO2 API Manager
  ```console
    docker run -itd -p 8280:8280 -p 8243:8243 -p 9443:9443 --name api-manager -h api-manager wso2/wso2am:2.6.0
  ```
  
  # Access to WSO2 API Managment
    
  Access API Manager’s Carbon management console, Publisher and Store:
  * Carbon management console: https://localhost:9443/carbon
  * Publisher: https://localhost:9443/publisher
  * Store: https://localhost:9443/store
  
  # WSO2 Identity Server
  WSO2 Identity Server, a part of the WSO2 Integration Agile Platform, is a uniquely flexible, open source IAM product optimized for identity federation and single sign-on (SSO) with comprehensive support for adaptive and strong authentication
  ```console
  docker run -itd -p 9443:9443 --name wso2-is -h wso2-is wso2/wso2is:5.7.0
  ```
  Access to Carbon management console: https://localhost:9443/carbon
    
  # WSO2 Data Services Server
  Setting up the drivers:
  * Copy it to the <PRODUCT_HOME>/repository/components/lib/ directory.
  * Copy the JAR file only to <PRODUCT_HOME>/repository/components/lib. Files will be copied automatically to the dropins folder at the server startup.
  
  
  
  # References
  * http://svn.wso2.org/repos/wso2/carbon/kernel/branches/4.2.0/samples/student-manager/
  * https://wso2.com/library/articles/2015/02/how-to-write-a-web-application-backed-by-wso2-middleware-part-1/
  * https://wso2.com/library/articles/2015/02/how-to-write-a-web-application-backed-by-wso2-middleware-part-2/
  * https://wso2.com/library/articles/2015/02/how-to-write-a-web-application-backed-by-wso2-middleware-part-3/
  * https://medium.com/@chirangaalwis/wso2-products-dockerized-an-introduction-5045a197befa
  * https://medium.com/@lahirugmg/how-to-generate-saml-2-0-assertions-with-wso2-identity-server-rest-api-84144a818e80
  * http://chanikageeganage.blogspot.com/2014/10/customized-login-pages-in-wso2-is-in.html
  