# docker-maven-GUI-chrome
Docker image for Java automated GUI tests on windows.

### Includes:

* Open JDK 8
* Maven 3.3.9
* Google Chrome 87.0.4280.66-1
* Chrome Driver 87.0.4280.20

## Some context
This image was created from the necesity of display the google chrome browser from a container on the host machine.

At the begining I was trying to dockerize a maven test with the image in the [markhobson/docker-maven-chrome](https://github.com/markhobson/docker-maven-chrome) repository.
This image throws an error when trying to display the browser. You need to use the '--setHeadless' chrome option o avoid this error.

  ChromeOptions options = new ChromeOptions().setHeadless(true);
  driver = new ChromeDriver(option);
 
The chrome option above run the test in chrome without using the GUI.

## Creating the new image
To allow the container to display the browser on the host machine, I used VcXsrv. check this web page to see the details [Run GUI app in linux docker container on windows host](https://dev.to/darksmile92/run-gui-app-in-linux-docker-container-on-windows-host-4kde).

for the base of this image, I used the image in the [open JDK](https://github.com/docker-library/openjdk/blob/master/8/jdk/buster/Dockerfile) repository.

Then added Google Chrome and Chrome Driver. [markhobson/docker-maven-chrome](https://github.com/markhobson/docker-maven-chrome)

Finally added maven. [goyalzz/ubuntu-java-8-maven-docker-image](https://hub.docker.com/r/goyalzz/ubuntu-java-8-maven-docker-image/dockerfile)

## Build the image and container
you need to put the Dockerfike in the same folder you have your maven project.

```powershell
cd your-maven-project-path
docker build -t mavenImage .
set-variable -name DISPLAY -value your-ip-address:0.0
docker run -ti -v your-maven-project-path:/usr/src -e DISPLAY=$DISPLAY mavenImage
```
  
