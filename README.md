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

