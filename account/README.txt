Pre-requisites:
    1) consul - for service discovery
    2) mongodb - db

1) Go to consul.exe folder, open cmd terminal and execute "consul agent -dev"
2) Download mongo db docker(docker pull mongo:latest) and run(docker run -p 27017:27017 mongo:latest)
    i) create db called "login_db"
        a) create collection called "userDetails"
            - insert sample record: {
                                       "userName" : "testuser",
                                       "password" : "1234678",
                                       "passwordExpiryDate" : "1710370827000"
                                    }