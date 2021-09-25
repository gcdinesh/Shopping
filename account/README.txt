Pre-requisites:
    1) consul - for service discovery
    2) mongodb - db cluster - Atlas uri

1) Go to consul.exe folder, open cmd terminal and execute "consul agent -dev"
2) Create a new application-secret.yml file and then add mongo.uri:<cluster_uri> and never ever upload that file
3) To start the service use the below command:
    -> docker run -p 4452:4452 -p 8890:8890 -e SPRING_PROFILES_ACTIVE=secret account:test1