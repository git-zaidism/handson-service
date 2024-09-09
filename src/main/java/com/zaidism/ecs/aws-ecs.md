
# How to Deploy a Spring Boot Application to Amazon ECS

This guide provides detailed steps to deploy a Spring Boot application using Docker to Amazon Elastic Container Service (ECS).

## Prerequisites

- A Spring Boot application ready for deployment
- Docker installed on your local machine
- AWS CLI configured with your AWS credentials
- Docker Hub account (optional but recommended)

## Steps

### 1. Build the Docker Image

Navigate to the root directory of your Spring Boot project and run the following Maven command to build the Docker image:

```bash
mvn spring-boot:build-image
```

After the build completes, note the image name from the console output. It will look something like this:

```
[INFO] Successfully built image 'docker.io/library/handson-service:0.0.1-SNAPSHOT'
```

### 2. Verify the Docker Image

To ensure the image was built correctly, list your Docker images with the following command:

```bash
docker image ls
```

### 3. Run the Docker Image Locally

To test the Docker image locally, use the following command:

```bash
docker run --tty --publish 8080:8080 <image_name>
```

Replace `<image_name>` with the actual name of the image you noted earlier. For example:

```bash
docker run --tty --publish 8080:8080 handson-service:0.0.1-SNAPSHOT
```

### 4. Test Your API

Once the container is running, you can test your API by accessing it through the default Docker IP or `localhost`. Open your web browser or use a tool like `curl` to make a request:

```bash
http://localhost:8080/api/fetch-users
```

### 5. Push the Docker Image to Docker Hub

#### Tag the Docker Image

Before pushing the image to Docker Hub, you need to tag it. Run the following command:

```bash
docker tag <imagename> <username>/<imagename>
```

For example:

```bash
docker tag handson-service:0.0.1-SNAPSHOT zaidism/handson-service:0.0.1-SNAPSHOT
```

It's recommended to use your Docker Hub username as the tag name to avoid errors.

#### Verify the Tag

Confirm the tag was created successfully:

```bash
docker image ls
```

You should see the tagged image, e.g., `zaidism/handson-service:0.0.1-SNAPSHOT`.

#### Push the Image to Docker Hub

Push the tagged image to Docker Hub using:

```bash
docker push zaidism/handson-service:0.0.1-SNAPSHOT
```

If you encounter an error similar to:

```
denied: requested access to the resource is denied
```

Login to Docker Hub with:

```bash
docker login -u "myusername" -p "mypassword"
```

Replace `"myusername"` and `"mypassword"` with your Docker Hub credentials.

For further help with this issue, refer to the following links:

- [Stack Overflow: Docker Push Error](https://stackoverflow.com/questions/41984399/docker-push-error-denied-requested-access-to-the-resource-is-denied)
- [Docker Forums: Access Denied](https://forums.docker.com/t/docker-push-error-requested-access-to-the-resource-is-denied/64468)

### 6. Deploy to Amazon ECS

For deploying your Docker image to Amazon ECS, follow the tutorial in this video:

- [Deploy to Amazon ECS](https://www.youtube.com/watch?v=z7_LdCVnCRU) (Start at 9:47 for ECS service creation)

In summary, instead of creating a new ECS task, you will create a service using the Docker image you pushed to Docker Hub.
