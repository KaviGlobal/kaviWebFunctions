# kaviWebFunctions
Spring boot cloud project for azure functions


Azure authentication:
go to command prompt:

az login


Maven functions for development:-

to deploy:
mvn install

1) Run azure function in local environment    
    mvn azure-functions:run
2) Run azure function in debug mode in local environment
	mvn azure-functions:run -DenableDebug
3) Deploy functions to cloud
	mvn azure-functions:deploy

Note : Please avoid commiting any unwanted settings file . We can keep adding the unwanted file names in the git ignore file , if needed.

API details :-

1) 	[POST] http://localhost:7071/api/saveItem   : Service to create/update the item meta data . When id ==null, create new record. When id!= null , update record. API will return the record ID.

2) [POST] http://localhost:7071/api/uploadItem	: Service to upload the file into the azure storage for a given ID. The file will be stored inside the folder having the name kaviweb-<id> .

