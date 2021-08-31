Required Frameworks
	# OpenJava (or Oracle) 11+ SDK
		Can be installed wiht APT:
			apt install openjdk-11-jdk-headless
			
	# Google Protocol Buffers - Open Source - For more information see: https://en.wikipedia.org/wiki/Protocol_Buffers
	
		As of 05/25/2021 can be downloaded from:
			https://github.com/protocolbuffers/protobuf/releases			
		or insalled with APT: 
			apt install command: apt install -y protobuf-compiler
			
	# Java GRPC Plugin
	Download the latest Java GRPC Plugin for your OS from the mavin repo and save it to your working directory.
			https://search.maven.org/search?q=g:io.grpc
		

Windows Instructions
--------------------
# Step 1) Generate java classes from proto file
  protoc --plugin=.\protoc-gen-grpc-java-your.version-filename.EXE --proto_path=. --java_out=.\ partner_api.proto

# Step 1 - Python) Generate python from proto file
  protoc --proto_path=. --python_out=.\python partner_api.proto

# Step 2 - java) Compile command:
  javac -cp lib\*;. -d . EmporiaEnergyApiClient.java

# Step 3 - java) Run client
  java -cp lib\*;. mains.EmporiaEnergyApiClient partner-api.emporiaenergy.com


MAC/Lunix Instructions
--------------------
# Step 1) Generate java classes from proto file
  protoc --plugin=./protoc-gen-grpc-java-your.version-filename-BINARY --proto_path=. --java_out=./ partner_api.proto

# Step 1 - Python) Generate python from proto file
  sudo protoc --proto_path=. --python_out=./python partner_api.proto

# Step 2 - java) Compile command:
  sudo javac -cp lib/\*:. -d . EmporiaEnergyApiClient.java

# Step 3 - java) Run client
  java -cp .:lib/\* mains.EmporiaEnergyApiClient partner-api.emporiaenergy.com



# CloudFormation Template

## To Create a deployment package

	# Step 1 - Run CloudFormation Package command
	aws cloudformation package --template-file cf-emporia-client-lambda.yml --output-template-file target/cf-emporia-client-lambda.yml --s3-bucket <YOUR_BUCKET_NAME> --force-upload

	# Step 2 - Run CloudFormation Deploy command
	aws cloudformation deploy --template-file target/cf-emporia-client-lambda.yml --stack-name emporia-client-lambda --capabilities CAPABILITY_IAM --parameter-overrides S3BucketName=<YOUR_BUCKET_NAME>
	



