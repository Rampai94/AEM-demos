# AEM-demos
Compilation of AEM helpx articles implementations and other use cases.

This repo is divided into two sections. 

First, the sample-aem project which is a multimodule project to be used as a reference implementation.

Second, the use-case specific files for just a quick overview to those who don't need a project and only the high-level approach.

# Instructions to use this repo:
Clone the repo into your local instance.
If you want to see a sample on a live AEM instance, step into the sample-aem folder and implement the commands mentioned below.
If you simply want the files just open the use-case specific folder and extract whatever you need.

# Sample AEM project template
This project is meant to be a collection of demonstrations for AEM based use-cases. We have started out with a simple approach to implement, test and compile the various helpx articles that are spread across the Adobe forums into the sample-aem project.

You can also find code not present in the Adobe docs but can be useful nevertheless.

## Minimum version of AEM Instance Used:
6.4 GA

## Archetype Used
18

## Modules

The main parts of the template are:

* core: Java bundle containing all core functionality like OSGi services, listeners or schedulers, as well as component-related Java code such as servlets or request filters.
* ui.apps: contains the /apps (and /etc) parts of the project, ie JS&CSS clientlibs, components, templates, runmode specific configs as well as Hobbes-tests
* ui.content: contains sample content using the components from the ui.apps
* ui.tests: Java bundle containing JUnit tests that are executed server-side. This bundle is not to be deployed onto production.
* ui.launcher: contains glue code that deploys the ui.tests bundle (and dependent bundles) to the server and triggers the remote JUnit execution

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

If you have a running AEM instance you can build and package the whole project and deploy into AEM with  

    mvn clean install -PautoInstallPackage
    
Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallPackagePublish
    
Or alternatively

    mvn clean install -PautoInstallPackage -Daem.port=4503

Or to deploy only the bundle to the author, run

    mvn clean install -PautoInstallBundle

## Testing

There are three levels of testing contained in the project:

* unit test in core: this show-cases classic unit testing of the code contained in the bundle. To test, execute:

    mvn clean test

* server-side integration tests: this allows to run unit-like tests in the AEM-environment, ie on the AEM server. To test, execute:

    mvn clean verify -PintegrationTests

* client-side Hobbes.js tests: JavaScript-based browser-side tests that verify browser-side behavior. To test:

    in the browser, open the page in 'Developer mode', open the left panel and switch to the 'Tests' tab and find the generated 'MyName Tests' and run them.


## Maven settings

The project comes with the auto-public repository configured. To setup the repository in your Maven settings, refer to:

    http://helpx.adobe.com/experience-manager/kb/SetUpTheAdobeMavenRepository.html
