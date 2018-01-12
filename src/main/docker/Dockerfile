# Based on official WebSphere Liberty docker
FROM websphere-liberty:webProfile6

# Copy the war file into the container
ADD ./war/idmu-rest-4.0.1-SNAPSHOT.war /config/dropins/

# Create templates and archives directories
RUN mkdir -p /opt/ibm/idmu/v4/packages/
RUN mkdir -p /opt/ibm/idmu/v4/archives/

# Add our own templates to the file
ADD ./templates/* /opt/ibm/idmu/v4/packages/

# Setup Environment Variables for Providers
ENV RestLocal_HOST=localhost
ENV RestLocal_PORT=9080

