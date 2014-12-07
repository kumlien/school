school
======


* boot2docker
https://docs.docker.com/installation/mac/
** First start the docker virutal machine (on mac...) with 
	boot2docker init
	boot2docker start
	
** Then export the variable as stated in the script
** If it's the first time you start the container then run 
		docker run  -p 5432:5432 --name school-postgres -d postgres -e POSTGRES_PASSWORD=school -e POSTGRES_USER=school
	If it's not the first time, then start the container with 
		docker start school-postgres
	To remove the postgres school container: docker rm school-postgres