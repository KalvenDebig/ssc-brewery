# Spring Security

## Many levels of security

* In the context of computing, there  are many levels of security:
    * Hardware Security - prevent unauthorized code execution
    * Operation System - access to the computer and action you can take
    * Database - access to the database and actions you can take
    * Message Brokers - read/write access to message queues
    * Network Security - wifi, VPCs, etc
    * Application Security - Access to the application and actions within application

## Spring Security

* Spring Security focuses on **Application Security**
    * Spring Security does not address other levels of security
* Application Security focuses on who can do what within the context of an application
* Spring Security provides:
    * Protection from common security exploits
    * Integration with external security products, such as LDAP
    * Provides utilities for password encoding

## Application Security Key Terms

* Identity - A unique actor, typically an individual aka user
* Credentials - Usually a user id and password
* Authentication - Is how the application verifies the identity of the requestor
    * Spring Security has a variety of methods for Authentication
    * Typically the user provides credentials, which are validated
* Authorization - Can a user perform an action?
    * Using the user's identity, Spring Security determines if they are authorized to perform action

## Authentication Providers

* Authentication Provides - verify users identities

* Authentication Providers supported by Spring Security:
    * In Memory
    * JDBC/Database
    * Custom
    * LDAP/Active Directory
    * Keycloak
    * ACL(Access Control List)
    * OpenID
    * CAS

## Password Storage

* Spring Security supports a variety of methods to store an verify passwords
    * NoOp password encoder - plain text, not recommended
    * BCrypt - uses bcrypt password hashing
    * Argon2 - Uses Argon2 algorithm
    * Pbkdf2
    * SCrypt

## Spring Security Modules

* Core - Core modules of Spring Security
* Remoting - Only needed for support of RMI operations
* Web - Support of web applications
* Config - Provides support for XML and Java configuration
* LDAP - for integration with LDAP identity providers
* OAuth 2.0 Core - Core of OAuth 2.0 Authorization and OpenID
* OAuth 2.0 Client - Client support for OAuth 2.0 and OpenID clients


## Common Web Vulnerabilities

* OWASP - Open Web Application Security Project

1. Injection - Injection of malicious code, such as SQL Injection attacks
    * Mitigation typically using proper encoding and bind variables
2. Broken Authentication - Authentication and session management implemented incorrectly
    * Mitigation - Use framework, don't roll your own
3. Sensitive Data Exposure - Not protecting sensitive data
    * Mitigation - Proper error handling, don't expose stack traces
4. XML External entities - Poorly configured XML processors
    * Mitigation - Patch XML Processors frequently
5. Broken Access Control - User Restrictions not properly enforced
    * Mitigation - Automated Testing, verify restrictions
6. Security Misconfiguration - Unintentionally not protecting resources
    * Mitigation - Security Audits
7. CrossSite Scripting - XSS Allows Users to inject HMTL or javascript
    * Mitigation - Use proper validation and escaping
8. Insecure Deserialization - Insecure deserialization can allow remote code execution
    * Mitigation - Use open source, patch frequently
9. Using Components with Know Vulnerabilities - Popular components often have known vulnerabilities
    * Migigation - Patch frequently
10. Insufficient Logging and Monitoring - Time to detect breaches often over 200 days
    * Mitigation - Properly monitor systems


## Spring Security for Common Vulnerabilities

* Spring Security has built in support to address several common vulnerabilities
    * Cross-site Scripting (XSS)
    * Cross-site Request Forgery (CSRF)
    * Security HTTP Response Headers
        * Variety of headers can be set to improve browser security
    * Redirect to HTTPS

## Anatomy of a XXS Attack

* User enters text in an input field, within text is javascript code
* Server accepts text without encoding or sanitizing
* User enter text displayed to user on page, JavaScript code executes

## Prevention of a XXS Attack

* Entered text should be scrubbed of JavaScript characters
* Special characters should be HTML encoded
* For complete precautions refer to OWASP

## Spring Security XXS Prevention

* Header `X-XSS-Protection` is set to `1; mode=block`
    * Tells browser to block XSS code when detected
    * Modern Browsers are starting to deprecate this in favor of Content Security Policy
* Content Security Policy - Spring Security does not implement a default value
    * Spring Security can easily be configured

## Cross-Site Request Forgery - CSRF

* A Cross-Site Request Forgery (CSRF) attack is when a site tricks the user's web browser to send a request to a cite where the user is authenticated
* CSRF attacks work because the browser is tricked into sending session cookies to the trusted site
* Thus, the trusted site cannot distinguish the request is not form the authenticated user

## Synchronizer Token Pattern

* The Synchronizer Token Pattern requires in addition to the session cookie, a secure random CSRF token must be in request
* CRSF Token must be part of HTTP Request not automatically sent by browser
    * Do not store CRSF token is cookies
    * Use:
        * HTTP Headers
        * Hidden Form Fields

## SameSite Cookie Attribute

* The SameSite cookie attribute can be set to tell browser to not send cookie when request is coming from other sites
* SameSite cookie attribute is supported on all modern browsers
* Supports - None, Lax(~subdomain), Strict
* Modern browsers transitioning from None to Lax if not explicitly set.
* Should not solely rely on SameSite attribute for CSRF prevention


## When to use CSRF Protection?

* Spring security Team recommends:
    * Use CRSF when request are processed by a browser by normal users
        - ie - HTML, Single page apps (Angular, React, etc)
    * If only used by non-browser clients, disable CSRF
        * ie - programmatic clients, like spring resttemplate or webclient

## Overview HTTP Basic Authentication

* HTTP Basic Authentication is part of the HTTP Specification
    * Originally from RFC 2617, 1999, updated in RFC 7617 in 2015
* Basic Authentication provides a standard way for HTTP clients to submit user name and password
* URL Encoding - `https://username:password@www.example.com`
* HTTP Header - Key: Authorization, Value: Basic <Base64 encoded string>
    * String - username:password

* URL Encoding and Header Encoding are not secure
* To protect users credentials, use of HTTPS is recommended
* HTTP Basic Authentication also criticized for sending user credentials in every request
    * Increases risk of compromise
    * Other methods send an authentication token in each request

## Spring Boot Spring Security Auto-Configuration

* When Spring Boot detects Spring Security on the classpath, it will auto-configure Spring Security for HTTP Basic Authentication
    * Default User - user
        * Set Property spring.security.user.name to override
    * Default Password - Random UUID, check console output
        * Set Property  spring.security.user.password to override
* All paths secured - except actuator info and health


## Spring Security Authentication Components

* Authentication Filter - A filter for a specific Authentication type in the Spring Security filter chain (ie basic auth, remember me cookies, etc)
* Authentication Manager - Standard API interface used by filter
* Authentication Provider - The implementation of Authentication (in memory, database, etc)
* User Details Service - Service to provide information about user
* Password Encoder - Service to encrypt and verify passwords
* Security Context - Holds details about authenticated entity

## In Memory User Details Manager

* Implements User Details Service
* Used by Spring Boot Auto-configuration
* Non-persistent implementation - uses in memory map
* Mainly used for testing and demonstration purposes
    * Not normally used in production systems

## Password Storage and Encoding

* When logging in, the application needs to verify the entered password matchers the password value stored in the system
* Legacy systems sometimes store passwords as plain text
    * Obviously not ideal
* Other systems encrypt the password in the database, then decrypt to verify
    * Again not ideal - can be decrypted to original value


## Password Hash

* A hash is a one-way mathematical algorithm applied to the password
    * One way meaning the hash value can be generated from a password
    * But the password cannot be generated from the hash value
* Example
    * password: password 1
    * hash value: 5f4dcc3b5aa765d61d8327deb882cf99
* In this theoretical example, the stirng `password 1` will always hash to `hash value`


## Password Salt

* Problem where hash functions generating known hash values
    * Became a dictionary attack to guess passwords from hash value
* Solution is to use a salt value
* A salt is additional data added to the value being hashed
* Example of password with salt: password1{ThisIsMyReallyLongPasswordSaltValue}
* Modern algorithms use random salt values
    * Thus hash value changes each time

## Password Hash Functions

* The security area of Hash functions is effectively an arms race
    * As computational power increases, researchers find more vulnerabilities
* Spring Security supports plain text and order hash functions for compatibility with legacy systems
* These encoders are marked as deprecated to warn you they are not recommended for use


## Delegating Password Encoder

* Spring Security 5 introduced a delegating password encoder
* Allows storage of password hashes in multiple formats
* Password hashes stores as - {encodername}<somepasswordhashvalue>
* Thus allows you to support multiple hash algorithms while migrating.


## Password Encoder Recommendation

* The Spring Security team recommends using an adaptive one way encoding function such as:
    * BCrypt (Default)
    * Pbkdf2
    * SCrypt
* These are also considered `slow`, which are computationally expensive to guard against brute force attacks


## Spring Security Filters

* All Spring Security Filters implement the Filter interface
    * Part of the Java Servlet API
    * Accepts Servlet Request, Servlet Response, and Filter Chain
* Can be used to implement actions on the Request or Response
* HTTP Basic Authentication is using the filter `BasicAuthenticationFilter`
    * Inspects Request for HTTP Basic credentials and performs Authentication

## Custom Spring Security Filter Use Case

* Hypothetically speaking we have a REST API using cutom headers for Authentication
* Goal here is to mimic a legacy application
    * Thi is not a recommended approach for Authentication
* Legacy Application sending API key and API Secret in HTTP Headers
* Create a Spring Security filter for this legacy Authentication
    * Extend Spring Security's AbstractAuthenticationProcessingFilter
    * Configure Spring Security to use Custom Filter

---

## Spring Security Database Authentication

* Using a traditional databse for Authenticatio is amatter of providing an alternate User Details Service
    * Spring security provides the interface, you provide the implementation
    * Can be in memory, JDBC, NoSQL, external service, tc.
* Spring Security does provide a JDBC implementation with database schemas
    * Typically a starting point and then customized to your application

## Spring Security JPA Authentication

* Provide custom Database Authentication using Spring Data JPA
* Need User and Authority JPA Entities
* Spring Data JPA Repositories
* Custom implementation of User Details Service using Spring Data Repostiories
* Configure Sping Security to use custom imlementation of User Details Service  


---

## Authorization in Spring Security  

* Authorization is the approval to perform an action within the application  
* Authorization can be as simple as allow all or is authenticated  
* Specific actions can be limited to specific roles to authorities  
* By default, Spring Security roles start with "ROLE_"  
    * Example: ROLE_ADMIN  
* Spring Security authorities may be any string value 


## Roles vs Authorities  

* Typically a role is considered a group of one or more authorities  
* In a Spring Security context:  
    * Roles by default start with "ROLE_"  
        * Configuration uses methods of `hasRole()` or `hasAnyRole()` - requires prefix  
    * Authorities are any string  
        * Configuration uses methods of `hasAuthority()` or `hasAnyAuthority()`  

## Access Decision Voters  

* Access Decision Voters provide a vote on allowing access  
    * ACCESS_ABSTAIN - Voters has no opinion  
    * ACCESS_DENIED - Voters does not approve  
    * ACCESS_GRANTED = Voter approves access  

## Role Voter  

* Most commonly used voter in Spring Security  
* Uses role names to grant access  
* If Authenticated user has role, access is granted  
    * If no authorities begin with prefix of `ROLE_` this voter will abstain  

## Authenticated Voter  

* Grants Access based on level of authentication  
    * Anonymously - Not Authenticated  
    * Remembered - Authenticated via Remember me cookie  
    * Fully - Fully Authenticated  

## Consensus Voter  

* Accepts list of Access Decision voters  
* Polls each voter  
* Access granted based on total of allowed vs denied responses  

## Role Hierarchy Voter  

* Allows configuration of Role Hierarchies  
* Example:  
    * ROLE_USER  
    * ROLE_ADMIN > ROLE_USER > ROLE_FOO  
* ROLE_ADMIN will have all of its authorities, and those of ROLE_USER and ROLE_FOO  
## Security Expressions 

* permitAll - Allows all access  
* denyAll - Denies all access 
* isAnonymous - Is Authenticated Anonymously  
* isAuthenticated - Is Authenticated (Fully or Remembered)  
* isRememberMe - Is Authenticated with Remember Me Cookie  
* isFullyAuthenticated - Is Fully Authenticated  

* hasRole - Has authority with ROLE_**  
* hasAnyRole - Accepts list of ROLE_** strings  
* hasAuthority - Has authority string value  
* hasAnyAuthority - Accepts list of stirng authority values  
* hasIpAddress - Accepts Ip Address or IP/Netmask  

## Http Filter Security Interceptor  

* Securing specific URLs is done using Spring Security Filters  
* Filters use configured voters to determine authorization  
* Security expressions available for use in Java configuration of HttpSecurity  

## Method Security  

* Spring Security also has method level security  
* Enable using `@EnableGlobalMethodSecurity` configuration annotation  
* `@Secured` - accepts list of roles, or `IS_AUTHENTICATED_ANONYMOUSLY`  
* `@PreAuthorize` - acceots security expressions  
* Under convers Spring Security is using AOP to intercept and use the `AccessDecisionManager`  
    * Same technique as Filter  

---  

## Configuring User Roles  

* Update Spring Security for the following  
    * Prepend ROLE_ to ADMIN, CUSTOMER, and USER  
    * Require ADMIN ROLE to Delete Beer  
    * Require ADMIN or CUSTOMER Role for Breweries  
    * Require ADMIN or CUSTOMER or USER ROle for Find Beer  
    * Require ADMIN or CUSTOMER Role for list customers  
    * Require ADMIN for Register Customer  
    
---

## Refactor User Authorities  

* Current user roles are very broad in scope  
* Do not match granularity of application  
* How to describe different of access of CRUD type operations  
* This problem would only grow in scope with complexity of application  
* Solution: Refactor to traditional model of roles with authorities  
* User assigend a role, which has a set of defined authorities  

---

## Multi-tenancy Security  

* Multi-tenant software architectures allows multiple users to share a single instance of a software application  
* Tenants are uses, can be individuals or groups  
    * Individuals - example Gmail  
    * Groups - example GitHub Organizations  
* Benefit of multi-tenancy is efficiency  
* Multi-tenancy comes in many different forms  

* Shared Databases can use a schema per tenant or shared tables with a tenant attribute  
* When the application and/or database is shared application security needs to prevent unauthorized access  
    * Customer A should not be able to read Customer B's data  
    * Customer A should not be able to udpate or delete Customer B's data  
    * A super user may need access to all customer data  
    * Service accounts many need access to all customer data  

## Multi-tenancy Securioty with Spring Security  

* Use Case: Allow Customers to Place Orders, Read Orders, and Cancel Orders  
* A customer can have one or more users  
* A custom UserDetails Object is used to hold necessary attributes in Security Context  
* SPeL is used to access attributes to make access decisions  
* For our use case, we will add Customer to the UserDetails object 
    * Rather than using the Spring Security User Object, we will implement our own  

    