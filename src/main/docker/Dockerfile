# Based on official WebSphere Liberty docker
FROM websphere-liberty:webProfile6

# Copy the war file into the container
ADD ./war/idmu-rest-4.0.1-SNAPSHOT.war /config/dropins/

# Copy templates into the container
ADD ./templates/* /opt/ibm/idmu/v4/packages/

# Make sure there is an default archives folder
ADD ./readme /opt/ibm/idmu/v4/archives/

