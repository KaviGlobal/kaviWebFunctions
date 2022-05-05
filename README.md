# kaviWebFunctions
Spring boot cloud project for azure functions

Maven functions for development:-

1) Run azure function in local environment    
    mvn azure-functions:run
2) Run azure function in debug mode in local environment
	mvn azure-functions:run -DenableDebug
3) Deploy functions to cloud
	mvn azure-functions:deploy

Note : Please avoid commiting any unwanted settings file . We can keep adding the unwanted file names in the git ignore file , if needed.
